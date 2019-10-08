/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.IncidenteActuacionLogic;
import co.edu.uniandes.csw.incidentes.ejb.IncidenteLogic;
import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
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
 * Pruebas de logica de la relacion Incidente - Actuacion
 * @author df.foreroc
 */
@RunWith(Arquillian.class)
public class IncidenteActuacionLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IncidenteLogic incidenteLogic;
    @Inject
    private IncidenteActuacionLogic incidenteActuacionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<IncidenteEntity> data = new ArrayList<IncidenteEntity>();

    private List<ActuacionEntity> actuacionData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IncidenteEntity.class.getPackage())
                .addPackage(IncidenteLogic.class.getPackage())
                .addPackage(IncidentePersistence.class.getPackage())
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
        for (int i = 0; i < 3; i++) {
            ActuacionEntity actuaciones = factory.manufacturePojo(ActuacionEntity.class);
            em.persist(actuaciones);
            actuacionData.add(actuaciones);
        }
        for (int i = 0; i < 3; i++) {
            IncidenteEntity entity = factory.manufacturePojo(IncidenteEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                actuacionData.get(i).setIncidente(entity);
            }
        }
    }

    /**
     * Prueba para asociar una actuacion existente a un Incidente.
     */
    @Test
    public void addActuacionTest() {
        IncidenteEntity entity = data.get(0);
        ActuacionEntity actuacionEntity = actuacionData.get(1);
        ActuacionEntity response = incidenteActuacionLogic.addActuacion(actuacionEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(actuacionEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de Actuaciones asociadas a una
     * instancia Incidente.
     */
    @Test
    public void getActuacionesTest() {
        List<ActuacionEntity> list = incidenteActuacionLogic.getActuaciones(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para obtener una instancia de Actuacion asociada a una instancia
     * Incidente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getActuacionTest() throws BusinessLogicException {
        IncidenteEntity entity = data.get(0);
        ActuacionEntity actuacionEntity = actuacionData.get(0);
        ActuacionEntity response = incidenteActuacionLogic.getActuacion(entity.getId(), actuacionEntity.getId());

        Assert.assertEquals(actuacionEntity.getId(), response.getId());
        Assert.assertEquals(actuacionEntity.getDescripcion(), response.getDescripcion());
        Assert.assertEquals(actuacionEntity.getFechaHora(), response.getFechaHora());

    }

    /**
     * Prueba para obtener una instancia de Actuacion asociada a una instancia
     * Incidente que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getActuacionNoAsociadoTest() throws BusinessLogicException {
        IncidenteEntity entity = data.get(0);
        ActuacionEntity ActuacionEntity = actuacionData.get(1);
        incidenteActuacionLogic.getActuacion(entity.getId(), ActuacionEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Actuacion asociadas a una instancia
     * de Incidente.
     */
    @Test
    public void replaceActuacionesTest() {
        IncidenteEntity entity = data.get(0);
        List<ActuacionEntity> list = actuacionData.subList(1, 2);
        incidenteActuacionLogic.replaceActuaciones(entity.getId(), list);

        entity = incidenteLogic.getIncidente(entity.getId());
        Assert.assertTrue(entity.getActuaciones().contains(actuacionData.get(0)));
        Assert.assertFalse(entity.getActuaciones().contains(actuacionData.get(1)));
        Assert.assertFalse(entity.getActuaciones().contains(actuacionData.get(2)));
    }
    
}
