/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.persistence.TecnicoPersistence;
import java.util.ArrayList;
import java.util.List;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Before;

/**
 *
 * @author Estudiante Diana Alejandra Silva Alvarez
 */
@RunWith(Arquillian.class)
public class TecnicoPersistanceTest {

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

    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TecnicoEntity.class.getPackage())
                .addPackage(TecnicoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    TecnicoPersistence tp;

    @Inject
    UserTransaction utx;

    private List<TecnicoEntity> data = new ArrayList<>();

    private void clearData() {
        em.createQuery("delete from TecnicoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TecnicoEntity entity = factory.manufacturePojo(TecnicoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TecnicoEntity tecnico = factory.manufacturePojo(TecnicoEntity.class);
        TecnicoEntity result = tp.create(tecnico);
        Assert.assertNotNull(result);
        TecnicoEntity entity = em.find(TecnicoEntity.class, result.getId());
        Assert.assertEquals(tecnico.getEspecialidad(), entity.getEspecialidad());
        Assert.assertEquals(tecnico.getPassword(), entity.getPassword());
        Assert.assertEquals(tecnico.getUsername(), entity.getUsername());
        Assert.assertEquals(tecnico.getNumCasos(), entity.getNumCasos());
    }

    @Test
    public void getTecnicosTest() {
        List<TecnicoEntity> list = tp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TecnicoEntity ent : list) {
            boolean found = false;
            for (TecnicoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity newEntity = tp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getEspecialidad(), entity.getEspecialidad());
        Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
        Assert.assertEquals(newEntity.getUsername(), entity.getUsername());
        Assert.assertEquals(newEntity.getNumCasos(), entity.getNumCasos());
    }

    @Test
    public void updateTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);

        newEntity.setId(entity.getId());

        tp.update(newEntity);

        TecnicoEntity resp = em.find(TecnicoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getEspecialidad(), resp.getEspecialidad());
        Assert.assertEquals(newEntity.getPassword(), resp.getPassword());
        Assert.assertEquals(newEntity.getUsername(), resp.getUsername());
        Assert.assertEquals(newEntity.getNumCasos(), resp.getNumCasos());
    }

    @Test
    public void deleteTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        tp.delete(entity.getId());
        TecnicoEntity deleted = em.find(TecnicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void getTecnicoByUsernameTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity newEntity = tp.findByUsername(entity.getUsername());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPassword(), newEntity.getPassword());
        Assert.assertEquals(entity.getUsername(), newEntity.getUsername());
    }
}
