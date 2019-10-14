/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.TecnicoLogic;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.TecnicoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Estudiante da.silvaa
 */
@RunWith(Arquillian.class)
public class TecnicoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TecnicoLogic tL;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TecnicoEntity> data = new ArrayList<TecnicoEntity>();

    private List<IncidenteEntity> incidenteData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TecnicoEntity.class.getPackage())
                .addPackage(TecnicoLogic.class.getPackage())
                .addPackage(TecnicoPersistence.class.getPackage())
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
        em.createQuery("delete from IncidenteEntity ").executeUpdate();
        em.createQuery("delete from TecnicoEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            IncidenteEntity incidentes = factory.manufacturePojo(IncidenteEntity.class);
            em.persist(incidentes);
            incidenteData.add(incidentes);
        }
        for (int i = 0; i < 3; i++) {
            TecnicoEntity entity = factory.manufacturePojo(TecnicoEntity.class);
            em.persist(entity);
            entity.setEspecialidad(new String());
            data.add(entity);
            if (i == 0) {
                incidenteData.get(i).setTecnico(entity);
            }
        }

    }

    @Test
    public void createTecnicoTest() throws BusinessLogicException {
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        TecnicoEntity result = tL.createTecnico(newEntity);
        Assert.assertNotNull(result);
        TecnicoEntity entity = em.find(TecnicoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getEspecialidad(), entity.getEspecialidad());
        Assert.assertEquals(newEntity.getNumCasos(), entity.getNumCasos());
        Assert.assertEquals(newEntity.getUsername(), entity.getUsername());
        Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
    }

    @Test(expected = BusinessLogicException.class)
    public void createTecnicoEspecialidadNull() throws BusinessLogicException {
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        newEntity.setEspecialidad(null);
        tL.createTecnico(newEntity);
    }

    @Test(expected = BusinessLogicException.class)
    public void createTecnicoUsernameNull() throws BusinessLogicException {
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        newEntity.setUsername(null);
        tL.createTecnico(newEntity);
    }

    @Test(expected = BusinessLogicException.class)
    public void createTecnicoPasswordNull() throws BusinessLogicException {
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        newEntity.setPassword(null);
        tL.createTecnico(newEntity);
    }

    @Test(expected = BusinessLogicException.class)
    public void createTecnicoConMismoNombre() throws BusinessLogicException {
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        newEntity.setUsername(data.get(0).getUsername());
        tL.createTecnico(newEntity);
    }

    @Test
    public void getTecnicosTest() {
        List<TecnicoEntity> list = tL.getTecnicos();
        Assert.assertEquals(data.size(), list.size());
        for (TecnicoEntity entity : list) {
            boolean found = false;
            for (TecnicoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity resultEntity = tL.getTecnico(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getEspecialidad(), resultEntity.getEspecialidad());
    }

    @Test
    public void updateTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity pojoEntity = factory.manufacturePojo(TecnicoEntity.class);

        pojoEntity.setId(entity.getId());

        tL.updateTecnico(entity.getId(), pojoEntity);

        TecnicoEntity resp = em.find(TecnicoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());

    }

    @Test
    public void deleteTecnicoTest() throws BusinessLogicException {
        TecnicoEntity entity = data.get(1);
        tL.deleteTecnico(entity.getId());
        TecnicoEntity deleted = em.find(TecnicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test(expected = BusinessLogicException.class)
    public void deleteTecnicoConIncidentesAsociadosTest() throws BusinessLogicException {
        TecnicoEntity entity = data.get(0);
        tL.deleteTecnico(entity.getId());
    }
}
