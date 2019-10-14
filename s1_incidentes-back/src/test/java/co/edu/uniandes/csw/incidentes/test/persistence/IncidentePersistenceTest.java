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

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IncidenteEntity.class.getPackage())
                .addPackage(IncidentePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }
    @Inject
    IncidentePersistence ip;

    @Inject
    UserTransaction utx;

    private List<IncidenteEntity> data = new ArrayList<>();

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
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Incidente.
     */
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        IncidenteEntity incidente = factory.manufacturePojo(IncidenteEntity.class);
        IncidenteEntity result = ip.create(incidente);
        Assert.assertNotNull(result);
        IncidenteEntity entity = em.find(IncidenteEntity.class, result.getId());

        Assert.assertEquals(incidente.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(incidente.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(incidente.getCategoria(), entity.getCategoria());
        Assert.assertEquals(incidente.getEquipo(), entity.getEquipo());
        Assert.assertEquals(incidente.getFechaHoraFinal(), entity.getFechaHoraFinal());
        Assert.assertEquals(incidente.getFechaHoraInicio(), entity.getFechaHoraInicio());
        Assert.assertEquals(incidente.getId(), entity.getId());
        Assert.assertEquals(incidente.getObservaciones(), entity.getObservaciones());
        Assert.assertEquals(incidente.getPrioridad(), entity.getPrioridad());
        Assert.assertEquals(incidente.getReabrir(), entity.getReabrir());
        Assert.assertEquals(incidente.getSolucionado(), entity.getSolucionado());
    }

    /**
     * Prueba para consultar la lista de incidentes.
     */
    @Test
    public void getIncidentesTest() {
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

    /**
     * Prueba para consultar un Incidente.
     */
    @Test
    public void getIncidenteTest() {
        IncidenteEntity entity = data.get(0);
        IncidenteEntity newEntity = ip.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(newEntity.getCategoria(), entity.getCategoria());
        Assert.assertEquals(newEntity.getEquipo(), entity.getEquipo());
        Assert.assertEquals(newEntity.getFechaHoraFinal(), entity.getFechaHoraFinal());
        Assert.assertEquals(newEntity.getFechaHoraInicio(), entity.getFechaHoraInicio());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getObservaciones(), entity.getObservaciones());
        Assert.assertEquals(newEntity.getPrioridad(), entity.getPrioridad());
        Assert.assertEquals(newEntity.getReabrir(), entity.getReabrir());
        Assert.assertEquals(newEntity.getSolucionado(), entity.getSolucionado());
    }

    /**
     * Prueba para actualizar un Incidente.
     */
    @Test
    public void updateIncidenteTest() {
        IncidenteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        IncidenteEntity newEntity = factory.manufacturePojo(IncidenteEntity.class);

        newEntity.setId(entity.getId());

        ip.update(newEntity);

        IncidenteEntity resp = em.find(IncidenteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(newEntity.getCalificacion(), resp.getCalificacion());
        Assert.assertEquals(newEntity.getCategoria(), resp.getCategoria());
        Assert.assertEquals(newEntity.getEquipo(), resp.getEquipo());
        Assert.assertEquals(newEntity.getFechaHoraFinal(), resp.getFechaHoraFinal());
        Assert.assertEquals(newEntity.getFechaHoraInicio(), resp.getFechaHoraInicio());
        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getObservaciones(), resp.getObservaciones());
        Assert.assertEquals(newEntity.getPrioridad(), resp.getPrioridad());
        Assert.assertEquals(newEntity.getReabrir(), resp.getReabrir());
        Assert.assertEquals(newEntity.getSolucionado(), resp.getSolucionado());
    }

    /**
     * Prueba para eliminar un Incidente.
     */
    @Test
    public void deleteIncidenteTest() {
        IncidenteEntity entity = data.get(0);
        ip.delete(entity.getId());
        IncidenteEntity deleted = em.find(IncidenteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
