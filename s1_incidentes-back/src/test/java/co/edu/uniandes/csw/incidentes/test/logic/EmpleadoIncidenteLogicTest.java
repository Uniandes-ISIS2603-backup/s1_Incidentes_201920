/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.EmpleadoIncidenteLogic;
import co.edu.uniandes.csw.incidentes.ejb.EmpleadoLogic;
import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.EmpleadoPersistence;
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
 * Pruebas de logica de la relacion Empleado - Incidentes
 * @author Julian Jaimes
 */
@RunWith(Arquillian.class)
public class EmpleadoIncidenteLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    EmpleadoLogic empleadoLogic;

    @Inject
    EmpleadoIncidenteLogic empleadoIncidenteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<EmpleadoEntity> data = new ArrayList<EmpleadoEntity>();

    private List<IncidenteEntity> incidentesData = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EmpleadoEntity.class.getPackage())
                .addPackage(EmpleadoLogic.class.getPackage())
                .addPackage(EmpleadoPersistence.class.getPackage())
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
        em.createQuery("delete from EmpleadoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            IncidenteEntity incidentes = factory.manufacturePojo(IncidenteEntity.class);
            em.persist(incidentes);
            incidentesData.add(incidentes);
        }
        for (int i = 0; i < 3; i++) {
            EmpleadoEntity entity = factory.manufacturePojo(EmpleadoEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                incidentesData.get(i).setEmpleado(entity);
            }
        }
    }
    
    /**
     * Prueba para asociar un Incidente existente a un Empleado.
     */
    @Test
    public void addIncidenteTest() {
        EmpleadoEntity entity = data.get(0);
        IncidenteEntity incidenteEntity = incidentesData.get(1);
        IncidenteEntity response = empleadoIncidenteLogic.addIncidente(incidenteEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(incidenteEntity.getId(), response.getId());
    }
    
    /**
     * Prueba para obtener una colección de instancias de Incidentes asociadas a una
     * instancia Empleado.
     */
    @Test
    public void getIncidentesTest() {
        List<IncidenteEntity> list = empleadoIncidenteLogic.getIncidentes(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }
    
     /**
     * Prueba para obtener una instancia de Incidente asociada a una instancia
     * Empleado.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void getIncidenteTest() throws BusinessLogicException {
        EmpleadoEntity entity = data.get(0);
        IncidenteEntity incidenteEntity = incidentesData.get(0);
        IncidenteEntity response = empleadoIncidenteLogic.getIncidente(entity.getId(), incidenteEntity.getId());

        Assert.assertEquals(incidenteEntity.getId(), response.getId());
        Assert.assertEquals(incidenteEntity.getDescripcion(), response.getDescripcion());
        Assert.assertEquals(incidenteEntity.getFechaHoraFinal(), response.getFechaHoraFinal());
        Assert.assertEquals(incidenteEntity.getFechaHoraInicio(), response.getFechaHoraInicio());
        Assert.assertEquals(incidenteEntity.getCategoria(), response.getCategoria());
    }
    
     /**
     * Prueba para obtener una instancia de Incidente asociada a una instancia
     * Empleado que no le pertenece.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getIncidenteNoAsociadoTest() throws BusinessLogicException {
        EmpleadoEntity entity = data.get(0);
        IncidenteEntity incidenteEntity = incidentesData.get(1);
        empleadoIncidenteLogic.getIncidente(entity.getId(), incidenteEntity.getId());
    }
    
}
