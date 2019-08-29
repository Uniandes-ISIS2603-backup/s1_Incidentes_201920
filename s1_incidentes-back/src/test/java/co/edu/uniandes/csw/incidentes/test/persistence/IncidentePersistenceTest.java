/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;


import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
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
 * @author df.foreroc
 */
@RunWith(Arquillian.class)
public class IncidentePersistenceTest {
    @PersistenceContext
    private EntityManager em;
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
              .addClass(IncidenteEntity.class)
              .addClass(IncidentePersistence.class)
              .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml", "beans.xml" );
    }
   @Inject
   IncidentePersistence ip;
   
   @Inject
    UserTransaction utx;

    private List<IncidenteEntity> data = new ArrayList<>();
   
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
            IncidenteEntity entity = factory.manufacturePojo(IncidenteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
   @Test
   public void createTest()
   {
       PodamFactory factory = new PodamFactoryImpl();
       IncidenteEntity incidente =factory.manufacturePojo(IncidenteEntity.class);
       IncidenteEntity result = ip.create(incidente);
       Assert.assertNotNull(result);
       IncidenteEntity entity = em.find(IncidenteEntity.class, result.getId());
        Assert.assertEquals(incidente.getDescripcion(), entity.getDescripcion());
        
   }
    @Test
    public void getIncidnetesTest() {
        List<IncidenteEntity> list = ip.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (IncidenteEntity ent : list) {
            boolean found = false;
            for (IncidenteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getIncidenteTest() {
        IncidenteEntity entity = data.get(0);
        IncidenteEntity newEntity = ip.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }

    @Test
    public void updateIncidenteTest() {
        IncidenteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        IncidenteEntity newEntity = factory.manufacturePojo(IncidenteEntity.class);

        newEntity.setId(entity.getId());

        ip.update(newEntity);

        IncidenteEntity resp = em.find(IncidenteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
    }
    
    @Test
    public void deleteIncidenteTest() {
        IncidenteEntity entity = data.get(0);
        ip.delete(entity.getId());
        IncidenteEntity deleted = em.find(IncidenteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
