/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.EquipoEntity;
import co.edu.uniandes.csw.incidentes.persistence.EquipoPersistence;
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
 * @author Julian Jaimes
 */
@RunWith(Arquillian.class)
public class EquipoPersistenceTest {
    
    @Inject
    private EquipoPersistence ep;
    
    @Inject
    UserTransaction utx;
    
    private List<EquipoEntity> data = new ArrayList<>();
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
              .addClass(EquipoEntity.class)
              .addClass(EquipoPersistence.class)
              .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml", "beans.xml" );
    }
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createEquipoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity ee = ep.create(newEntity);
        
        Assert.assertNotNull(ee);
        
        EquipoEntity entity = em.find(EquipoEntity.class, ee.getId());
        
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getIdEquipo(), entity.getIdEquipo());
    }
    /**
    @Test
    public void findEquipoEntity() {
        EquipoEntity entity = data.get(0);
        EquipoEntity newEntity = ep.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getIdEquipo(), newEntity.getIdEquipo());
    }
    
    @Test
    public void findAllEquipoEntity() {
        List<EquipoEntity> list = ep.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EquipoEntity ent : list) {
            boolean found = false;
            for (EquipoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }*/
    
    /**@Test
    public void updateEquipoEntity() {
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity ee = ep.create(newEntity);
        EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
        
        ep.update(newEntity);

       // EquipoEntity resp = em.find(EquipoEntity.class, entity.getId());

       // Assert.assertEquals(newEntity.getIdEquipo(), resp.getIdEquipo());
    }*/
    
    @Test
    public void deleteEquipoEntity() {
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity ee = ep.create(newEntity);
        
        Assert.assertNotNull(ee);
        
        EquipoEntity entity = em.find(EquipoEntity.class, ee.getId());
        
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getIdEquipo(), entity.getIdEquipo());
        
        ep.delete(ee.getId());
        Assert.assertNull(em.find(EquipoEntity.class, ee.getId()));
    }
}
