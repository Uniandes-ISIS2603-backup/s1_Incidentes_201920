/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.ActuacionLogic;
import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.ActuacionPersistence;
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
import static org.junit.Assert.assertNull;
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
public class ActuacionLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActuacionEntity.class.getPackage())
                .addPackage(ActuacionPersistence.class.getPackage())
                .addPackage(ActuacionLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ActuacionLogic actuacionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<ActuacionEntity> data = new ArrayList<>();
    
    private List<IncidenteEntity> dataIncidentes = new ArrayList<IncidenteEntity>();
    
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
        em.createQuery("delete from ActuacionEntity").executeUpdate();
        em.createQuery("delete from IncidenteEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            IncidenteEntity incidente = factory.manufacturePojo(IncidenteEntity.class);
            em.persist(incidente);
            dataIncidentes.add(incidente);
        }
        for (int i = 0; i < 3; i++) {
            ActuacionEntity actuacion = factory.manufacturePojo(ActuacionEntity.class);
            
            actuacion.setIncidente(dataIncidentes.get(1));
            em.persist(actuacion);
            data.add(actuacion);
        }
    }
    
    @Test
    public void createActuacion() throws BusinessLogicException{
        ActuacionEntity newEntity = factory.manufacturePojo(ActuacionEntity.class);
        newEntity.setIncidente(dataIncidentes.get(1));
        ActuacionEntity result = actuacionLogic.createActuacion(dataIncidentes.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        ActuacionEntity entity = em.find(ActuacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getFechaHora(), entity.getFechaHora());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createActuacionDescripcionNull() throws BusinessLogicException {
        ActuacionEntity newEntity = factory.manufacturePojo(ActuacionEntity.class);
        newEntity.setIncidente(dataIncidentes.get(1));
        newEntity.setDescripcion(null);
        ActuacionEntity result = actuacionLogic.createActuacion(dataIncidentes.get(1).getId(), newEntity);
    }
    
    @Test
    public void getActuacionesTest() {
        List<ActuacionEntity> list = actuacionLogic.getActuaciones(dataIncidentes.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (ActuacionEntity entity : list) {
            boolean found = false;
            for (ActuacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getActuacionTest() {
        ActuacionEntity entity = data.get(0);
        ActuacionEntity resultEntity = actuacionLogic.getActuacion(dataIncidentes.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getFechaHora(), resultEntity.getFechaHora());
    }
    
    @Test
    public void updateActuacionTest() throws BusinessLogicException {
        ActuacionEntity entity = data.get(0);
        ActuacionEntity pojoEntity = factory.manufacturePojo(ActuacionEntity.class);

        pojoEntity.setId(entity.getId());

        actuacionLogic.updateActuacion(pojoEntity.getId(), pojoEntity);

        ActuacionEntity resp = em.find(ActuacionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(pojoEntity.getFechaHora(), resp.getFechaHora());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateActuacionDescripcionNull() throws BusinessLogicException {
        ActuacionEntity entity = data.get(0);
        ActuacionEntity pojoEntity = factory.manufacturePojo(ActuacionEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setDescripcion(null);
        actuacionLogic.updateActuacion(dataIncidentes.get(1).getId(), pojoEntity);
    }
    
    @Test
    public void deleteActuacionTest() throws BusinessLogicException {
        ActuacionEntity entity = data.get(0);
        actuacionLogic.deleteActuacion(dataIncidentes.get(1).getId(), entity.getId());
        ActuacionEntity resp = em.find(ActuacionEntity.class, entity.getId());
        assertNull(resp);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void deteleActuacionConIncidenteNoAsociadoTest() throws BusinessLogicException {
        ActuacionEntity entity = data.get(0);
        actuacionLogic.deleteActuacion(dataIncidentes.get(0).getId(), entity.getId());
    }
}
