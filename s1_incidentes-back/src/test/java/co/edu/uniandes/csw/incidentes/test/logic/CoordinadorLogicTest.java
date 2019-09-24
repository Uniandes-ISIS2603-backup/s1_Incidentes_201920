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
        em.createQuery("delete from IncidenteEntity").executeUpdate();
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
    public void createUserTest() throws BusinessLogicException{
        CoordinadorEntity newEntity = factory.manufacturePojo(CoordinadorEntity.class);
        CoordinadorEntity result = cl.createUser(newEntity);
        Assert.assertNotNull(result);
        CoordinadorEntity entity = em.find(CoordinadorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    @Test (expected = BusinessLogicException.class)
    public void createCoordinadorNameNull()throws BusinessLogicException{
        CoordinadorEntity newEntity=factory.manufacturePojo(CoordinadorEntity.class);
        newEntity.setName(null);
        CoordinadorEntity resultado= cl.createUser(newEntity);
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
