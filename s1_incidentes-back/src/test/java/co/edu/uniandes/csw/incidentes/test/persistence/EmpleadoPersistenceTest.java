/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.persistence.EmpleadoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Daniel Reyes
 */

@RunWith(Arquillian.class)
public class EmpleadoPersistenceTest {
    
    @Inject
    private EmpleadoPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addClass(EmpleadoEntity.class)
                .addClass(EmpleadoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml");
                
    }
    
    @Inject
    UserTransaction utx;
    
    private List<EmpleadoEntity> data = new ArrayList<>();
    
    @Before
    public void configTest(){
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData(){
        em.createQuery("delete from EmpleadoEntity").executeUpdate();
    }
    
    private void insertData(){
            PodamFactory factory = new PodamFactoryImpl();
            for(int i = 0 ; i < 3 ; i++){
                EmpleadoEntity entity = factory.manufacturePojo(EmpleadoEntity.class);
                
                em.persist(entity);
                data.add(entity);
            }
    }
    
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        EmpleadoEntity result = ep.create(ee);
        Assert.assertNotNull(result);
        
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, result.getId());
        Assert.assertEquals(ee.getNombre(), entity.getNombre());
    }
    
    @Test
    public void getEmpleadosTest(){
        List<EmpleadoEntity> list = ep.listAll();
        Assert.assertEquals(data.size(),list.size());
        for(EmpleadoEntity ent : list){
            boolean found = false;
            for(EmpleadoEntity entity : data){
                if(ent.getId().equals(entity.getId())){
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getEmpleadoTest(){
        EmpleadoEntity entity = data.get(0);
        EmpleadoEntity newEntity = ep.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());
    }
    
    
    @Test
    public void updateEmpleadoTest(){
        EmpleadoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity newEntity = factory.manufacturePojo(EmpleadoEntity.class);
        
        newEntity.setId(entity.getId());
        
        ep.update(newEntity);
        
        EmpleadoEntity resp = em.find(EmpleadoEntity.class,entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(),resp.getNombre());
    }
    
    @Test
    public void deleteEmpleadoTest(){
        EmpleadoEntity entity = data.get(0);
        ep.delete(entity.getId());
        EmpleadoEntity deleted = em.find(EmpleadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
