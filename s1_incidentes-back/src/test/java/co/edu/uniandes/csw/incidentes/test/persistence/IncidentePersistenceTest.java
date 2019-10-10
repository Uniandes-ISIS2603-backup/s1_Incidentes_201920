/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;


import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.persistence.ActuacionPersistence;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import co.edu.uniandes.csw.incidentes.persistence.EmpleadoPersistence;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import co.edu.uniandes.csw.incidentes.persistence.TecnicoPersistence;
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
              .addPackage(TecnicoPersistence.class.getPackage())
              .addPackage(CoordinadorPersistence.class.getPackage())
              .addPackage(EmpleadoPersistence.class.getPackage())
              .addPackage(ActuacionPersistence.class.getPackage())
                .addPackage(TecnicoEntity.class.getPackage())
              .addPackage(CoordinadorEntity.class.getPackage())
              .addPackage(EmpleadoEntity.class.getPackage())
              .addPackage(ActuacionEntity.class.getPackage())
              .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml", "beans.xml" );
        
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
   public void createTest()
   {
       PodamFactory factory = new PodamFactoryImpl();
       IncidenteEntity incidente =factory.manufacturePojo(IncidenteEntity.class);
       IncidenteEntity result = ip.create(incidente);
       Assert.assertNotNull(result);
       IncidenteEntity entity = em.find(IncidenteEntity.class, result.getId());
        Assert.assertEquals(incidente.getDescripcion(), entity.getDescripcion());
        
   }
   /**
     * Prueba para consultar la lista de incidentes.
     */
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
    /**
     * Prueba para consultar un Incidente.
     */
    @Test
    public void getIncidenteTest() {
        IncidenteEntity entity = data.get(0);
        IncidenteEntity newEntity = ip.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
        Assert.assertEquals(entity.getFechaHoraInicio(), newEntity.getFechaHoraInicio());
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
