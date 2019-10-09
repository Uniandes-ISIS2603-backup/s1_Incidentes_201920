/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.persistence.ActuacionPersistence;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
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
 * @author Catalina Alcalá
 */
@RunWith(Arquillian.class)
public class ActuacionPersistenceTest {
    
    @Inject
    private ActuacionPersistence actuacionPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;

    private List<ActuacionEntity> data = new ArrayList<>();
    
    private List<IncidenteEntity> dataIncidentes  = new ArrayList<IncidenteEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActuacionEntity.class.getPackage())
                .addPackage(ActuacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
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
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ActuacionEntity").executeUpdate();
        em.createQuery("delete from IncidenteEntity").executeUpdate();
    }
   /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            IncidenteEntity entity = factory.manufacturePojo(IncidenteEntity.class);
            em.persist(entity);
            dataIncidentes.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            ActuacionEntity entity = factory.manufacturePojo(ActuacionEntity.class);
            if(i==0) {
                entity.setIncidente(dataIncidentes.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ActuacionEntity actuacion = factory.manufacturePojo(ActuacionEntity.class);
        ActuacionEntity result = actuacionPersistence.create(actuacion);
        Assert.assertNotNull(result);
        
        ActuacionEntity entity = em.find(ActuacionEntity.class, result.getId());
        Assert.assertEquals(actuacion.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(actuacion.getFechaHora(), entity.getFechaHora());
    }
    
    /**
     * Prueba para consultar una actuación.
     */
    @Test
    public void findTest() {
        ActuacionEntity entity = data.get(0);
        ActuacionEntity newEntity = actuacionPersistence.find(dataIncidentes.get(0).getId(),entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
        Assert.assertEquals(entity.getFechaHora(), newEntity.getFechaHora());
    }
    
    /**
     * Prueba para actualizar una actuación.
     */
    @Test
    public void updateAuthorTest() {
        ActuacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ActuacionEntity newEntity = factory.manufacturePojo(ActuacionEntity.class);

        newEntity.setId(entity.getId());

        actuacionPersistence.update(newEntity);

        ActuacionEntity resp = em.find(ActuacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(newEntity.getFechaHora(), resp.getFechaHora());
    }

    /**
     * Prueba para eliminar un Author.
     */
    @Test
    public void deleteAuthorTest() {
        ActuacionEntity entity = data.get(0);
        actuacionPersistence.delete(entity.getId());
        ActuacionEntity deleted = em.find(ActuacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
