/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
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
 * @author Juan Camilo Castiblanco
 */
@RunWith(Arquillian.class)
public class CoordinadorLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CoordinadorLogic cl;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CoordinadorEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CoordinadorEntity.class.getPackage())
                .addPackage(CoordinadorLogic.class.getPackage())
                
                
                .addPackage(CoordinadorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("delete from CoordinadorEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CoordinadorEntity entity = factory.manufacturePojo(CoordinadorEntity.class);
            em.persist(entity);
            entity.setName(new String());
            data.add(entity);
        } 
    }

    @Test
    public void createCoordinadorTest() throws BusinessLogicException{
        CoordinadorEntity newEntity = factory.manufacturePojo(CoordinadorEntity.class);
        CoordinadorEntity result = cl.createCoordinador(newEntity);
        Assert.assertNotNull(result);
        CoordinadorEntity entity = em.find(CoordinadorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getUsername(), entity.getUsername());
        Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
    }

    @Test (expected = BusinessLogicException.class)
    public void createCoordinadorNameNull()throws BusinessLogicException{
        CoordinadorEntity newEntity=factory.manufacturePojo(CoordinadorEntity.class);
        newEntity.setName(null);
        CoordinadorEntity resultado= cl.createCoordinador(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createCoordinadorUsernameNull()throws BusinessLogicException{
        CoordinadorEntity newEntity=factory.manufacturePojo(CoordinadorEntity.class);
        newEntity.setUsername(null);
        CoordinadorEntity resultado= cl.createCoordinador(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createCoordinadorPasswordNull()throws BusinessLogicException{
        CoordinadorEntity newEntity=factory.manufacturePojo(CoordinadorEntity.class);
        newEntity.setPassword(null);
        CoordinadorEntity resultado= cl.createCoordinador(newEntity);
    }
 
    @Test (expected = BusinessLogicException.class)
    public void createCoordinadorConMismoNombre()throws BusinessLogicException{
        CoordinadorEntity newEntity=factory.manufacturePojo(CoordinadorEntity.class);
        newEntity.setUsername(data.get(0).getUsername());
        CoordinadorEntity resultado= cl.createCoordinador(newEntity);
    }
    
    @Test
    public void getCoordinadorTest() {
        CoordinadorEntity entity = data.get(0);
        CoordinadorEntity resultEntity = cl.getCoordinador(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }
    
    @Test
    public void getCoordinadoresTest() {
        List<CoordinadorEntity> list = cl.getCoordinadores();
        Assert.assertEquals(data.size(), list.size());
        for (CoordinadorEntity entity : list) {
            boolean found = false;
            for (CoordinadorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void updateCoordinadorTest() {
        CoordinadorEntity entity = data.get(0);
        CoordinadorEntity pojoEntity = factory.manufacturePojo(CoordinadorEntity.class);

        pojoEntity.setId(entity.getId());

        cl.updateCoordinador(pojoEntity.getId(), pojoEntity);

        CoordinadorEntity resp = em.find(CoordinadorEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
    
    @Test
    public void deleteCoordinadorTest() throws BusinessLogicException {
        CoordinadorEntity entity = data.get(0);
        cl.deleteCoordinador(entity.getId());
        CoordinadorEntity deleted = em.find(CoordinadorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
