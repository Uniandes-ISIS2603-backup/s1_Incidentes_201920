/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.IncidenteLogic;
import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
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
 *
 * @author df.foreroc
 */
@RunWith(Arquillian.class)
public class IncidenteLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IncidenteLogic iL;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<IncidenteEntity> data = new ArrayList<IncidenteEntity>();
    
    private List<CoordinadorEntity> coordinadorData = new ArrayList();

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
        em.createQuery("delete from IncidenteEntity").executeUpdate();
        em.createQuery("delete from CoordinadorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CoordinadorEntity entity = factory.manufacturePojo(CoordinadorEntity.class);
            em.persist(entity);
            coordinadorData.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            IncidenteEntity entity = factory.manufacturePojo(IncidenteEntity.class);
            entity.setCoordinador(coordinadorData.get(0));
            em.persist(entity);
            data.add(entity);
        }
        
    }

    /**
     * Prueba para crear un Incidente.
     */
    @Test
    public void createIncidenteTest() throws BusinessLogicException {
        IncidenteEntity newEntity = factory.manufacturePojo(IncidenteEntity.class);
        newEntity.setCoordinador(coordinadorData.get(0));
        IncidenteEntity result = iL.createIncidente(newEntity);
        Assert.assertNotNull(result);
        IncidenteEntity entity = em.find(IncidenteEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());

    }

    /**
     * Crear un incidente con descripcion null
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createIncidenteDescripcionNull() throws BusinessLogicException {
        IncidenteEntity newEntity = factory.manufacturePojo(IncidenteEntity.class);
        newEntity.setDescripcion(null);
        IncidenteEntity resultado = iL.createIncidente(newEntity);
    }

    /**
     * Crear un incidente con equipo null
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createIncidenteEquipoNull() throws BusinessLogicException {
        IncidenteEntity newEntity = factory.manufacturePojo(IncidenteEntity.class);
        newEntity.setEquipo(null);
        IncidenteEntity resultado = iL.createIncidente(newEntity);
    }

    /**
     * Crear un incidente con prioridad null
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createIncidentePrioridadNull() throws BusinessLogicException {
        IncidenteEntity newEntity = factory.manufacturePojo(IncidenteEntity.class);
        newEntity.setPrioridad(null);
        IncidenteEntity resultado = iL.createIncidente(newEntity);
    }

    


    /**
     * Crear un incidente con categoria null
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createIncidenteTipoNull() throws BusinessLogicException {
        IncidenteEntity newEntity = factory.manufacturePojo(IncidenteEntity.class);
        newEntity.setCategoria(null);
        IncidenteEntity resultado = iL.createIncidente(newEntity);
    }

    

    

    /**
     * Prueba para consultar la lista de Incidentes.
     */
    @Test
    public void getIncidentesTest() {
        List<IncidenteEntity> list = iL.getIncidentes();
        Assert.assertEquals(data.size(), list.size());
        for (IncidenteEntity entity : list) {
            boolean found = false;
            for (IncidenteEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        IncidenteEntity resultEntity = iL.getIncidente(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getFechaHoraInicio(), resultEntity.getFechaHoraInicio());
    }

    /**
     * Prueba para actualizar un Incidente.
     */
    @Test
    public void updateIncidenteTest() {
        IncidenteEntity entity = data.get(0);
        IncidenteEntity pojoEntity = factory.manufacturePojo(IncidenteEntity.class);
        
        pojoEntity.setId(entity.getId());
        
        iL.updateIncidente(pojoEntity.getId(), pojoEntity);
        
        IncidenteEntity resp = em.find(IncidenteEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(pojoEntity.getFechaHoraInicio(), resp.getFechaHoraInicio());
    }

    /**
     * Prueba para eliminar un Incidente.
     */
    @Test
    public void deleteIncidenteTest() {
        IncidenteEntity entity = data.get(0);
        iL.deleteIncidente(entity.getId());
        IncidenteEntity deleted = em.find(IncidenteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Cerrar un incidente
     *
     * @throws BusinessLogicException
     */
    @Test
    public void cerrarIncidente() throws BusinessLogicException {
        IncidenteEntity newEntity = factory.manufacturePojo(IncidenteEntity.class);
        IncidenteEntity result = iL.createIncidente(newEntity);
        result.setSolucionado(Boolean.FALSE);
        iL.cerrarIncidente(result);
        Assert.assertTrue(result.getSolucionado());
    }

    /**
     * Cerrar un incidente que es null
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void cerrarIncidenteNull() throws BusinessLogicException {
        IncidenteEntity newEntity = null;
        iL.cerrarIncidente(newEntity);
    }

    /**
     * Cerrar un incidente ya solucionado
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void cerrarIncidenteSolucionado() throws BusinessLogicException {
        IncidenteEntity newEntity = factory.manufacturePojo(IncidenteEntity.class);
        newEntity.setSolucionado(true);
        iL.cerrarIncidente(newEntity);
    }
}
