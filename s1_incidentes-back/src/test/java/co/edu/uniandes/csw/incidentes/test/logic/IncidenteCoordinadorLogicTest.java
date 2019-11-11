/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.IncidenteCoordinadorLogic;
import co.edu.uniandes.csw.incidentes.ejb.IncidenteLogic;
import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
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
public class IncidenteCoordinadorLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IncidenteLogic incidenteLogic;
    @Inject
    private IncidenteCoordinadorLogic incidenteCoordinadorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CoordinadorEntity> data = new ArrayList<CoordinadorEntity>();

    private List<IncidenteEntity> incidentesData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CoordinadorEntity.class.getPackage())
                .addPackage(IncidenteLogic.class.getPackage())
                .addPackage(CoordinadorPersistence.class.getPackage())
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
            IncidenteEntity incidentes = factory.manufacturePojo(IncidenteEntity.class);
            em.persist(incidentes);
            incidentesData.add(incidentes);
        }
        for (int i = 0; i < 3; i++) {
            CoordinadorEntity entity = factory.manufacturePojo(CoordinadorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                incidentesData.get(i).setCoordinador(entity);
            }
        }
    }

    /**
     * Prueba para remplazar las instancias de incidentes asociadas a una instancia
     * de Coordinador.
     */
    @Test
    public void replaceEditorialTest() {
        IncidenteEntity entity = incidentesData.get(0);
        incidenteCoordinadorLogic.replaceCoordinador(entity.getId(), data.get(1).getId());
        entity = incidenteLogic.getIncidente(entity.getId());
        Assert.assertEquals(entity.getCoordinador(), data.get(1));
    }

    /**
     * Prueba para desasociar un incidente existente de un Coordinador existente
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeBooksTest() throws BusinessLogicException {
        incidenteCoordinadorLogic.removeCoordinador(incidentesData.get(0).getId());
        IncidenteEntity response = incidenteLogic.getIncidente(incidentesData.get(0).getId());
        Assert.assertNull(response.getCoordinador());
    }
}
