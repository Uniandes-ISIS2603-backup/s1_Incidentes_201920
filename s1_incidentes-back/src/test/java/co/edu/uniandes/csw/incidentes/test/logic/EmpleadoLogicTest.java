/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.EmpleadoLogic;
import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.EmpleadoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
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
public class EmpleadoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private EmpleadoLogic logic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<EmpleadoEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EmpleadoEntity.class.getPackage())
                .addPackage(EmpleadoLogic.class.getPackage())
                .addPackage(EmpleadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from EmpleadoEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EmpleadoEntity entity = factory.manufacturePojo(EmpleadoEntity.class);
            em.persist(entity);
            entity.setNombre(new String());
            data.add(entity);
        } 
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmpleadoTest0() throws BusinessLogicException{
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        ee.setNombre("");
        ee.setTipo("HARDWARE");
        ee.setNumIncidentes(3);
        logic.createEmpleado(ee);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmpleadoTest1() throws BusinessLogicException{
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        ee.setNombre("Nombre");
        ee.setTipo("dd");
        ee.setNumIncidentes(3);
        logic.createEmpleado(ee);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmpleadoTest2() throws BusinessLogicException{
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        ee.setNombre("Nombre");
        ee.setTipo("HARDWARE");
        ee.setNumIncidentes(-2);
        logic.createEmpleado(ee);
    }
    
    @Test
    public void createEmpleadoTest3() throws BusinessLogicException{
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        ee.setNombre("Nombre");
        ee.setTipo("HARDWARE");
        ee.setNumIncidentes(6);
        logic.createEmpleado(ee);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createEmpleadoUsernameNull()throws BusinessLogicException{
        EmpleadoEntity newEntity=factory.manufacturePojo(EmpleadoEntity.class);
        newEntity.setUsername(null);
        EmpleadoEntity resultado= logic.createEmpleado(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createEmpleadoPasswordNull()throws BusinessLogicException{
        EmpleadoEntity newEntity=factory.manufacturePojo(EmpleadoEntity.class);
        newEntity.setPassword(null);
        EmpleadoEntity resultado= logic.createEmpleado(newEntity);
    }
 
    @Test (expected = BusinessLogicException.class)
    public void createEmpleadoConMismoUsername()throws BusinessLogicException{
        EmpleadoEntity newEntity=factory.manufacturePojo(EmpleadoEntity.class);
        newEntity.setUsername(data.get(0).getUsername());
        EmpleadoEntity resultado= logic.createEmpleado(newEntity);
    }
    
    @Test
    public void getEmpleadoTest() {
        EmpleadoEntity entity = data.get(0);
        EmpleadoEntity resultEntity = logic.getEmpleado(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void getEmpleadosTest() {
        List<EmpleadoEntity> list = logic.getEmpleados();
        Assert.assertEquals(data.size(), list.size());
        for (EmpleadoEntity entity : list) {
            boolean found = false;
            for (EmpleadoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void updateEmpleadoTest() {
        EmpleadoEntity entity = data.get(0);
        EmpleadoEntity pojoEntity = factory.manufacturePojo(EmpleadoEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateEmpleado(pojoEntity.getId(), pojoEntity);

        EmpleadoEntity resp = em.find(EmpleadoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
    @Test
    public void deleteEmpleadoTest() throws BusinessLogicException {
        EmpleadoEntity entity = data.get(0);
        logic.deleteEmpleado(entity.getId());
        EmpleadoEntity deleted = em.find(EmpleadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
