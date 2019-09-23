/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.EquipoLogic;
import co.edu.uniandes.csw.incidentes.entities.EquipoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.EquipoPersistence;
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
 * @author Julian Jaimes
 */
@RunWith(Arquillian.class)
public class EquipoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private EquipoLogic equipo;
    
    @Inject
    UserTransaction utx;
    
    @PersistenceContext
    private EntityManager em;
    
    private List<EquipoEntity> data = new ArrayList<>();
    
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

    private void clearData() {
        em.createQuery("delete from EquipoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
              .addPackage(EquipoEntity.class.getPackage())
              .addPackage(EquipoLogic.class.getPackage())
              .addPackage(EquipoPersistence.class.getPackage())
              .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml", "beans.xml" );
    }
    
    @Test
    public void createEquipo() throws BusinessLogicException {
        EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
        entity.setIdEquipo(1);
        EquipoEntity prueba = equipo.createEquipo(entity);
        Assert.assertNotNull(prueba);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createEquipo0() throws BusinessLogicException {
        EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
        entity.setIdEquipo(-1);
        entity = equipo.createEquipo(entity);
    }
    
    @Test
    public void findEquipos() {
        EquipoEntity entity = data.get(0);
        EquipoEntity newEntity = equipo.findEquipo(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getIdEquipo(), newEntity.getIdEquipo());
    }
    
    @Test
    public void findAllEquipos() {
        List<EquipoEntity> list = equipo.findAllEquipos();
        Assert.assertEquals(data.size(), list.size());
        for (EquipoEntity ent : list) {
            boolean found = false;
            for (EquipoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void updateEquipo(){
        EquipoEntity entity=data.get(0);
        EquipoEntity ee = factory.manufacturePojo(EquipoEntity.class);
        ee.setId(entity.getId());
        equipo.updateEquipo(ee);
        EquipoEntity resp= em.find(EquipoEntity.class,entity.getId());
       Assert.assertEquals(ee.getIdEquipo(), resp.getIdEquipo());
    }
    
    @Test
    public void deleteEquipo() {
        EquipoEntity entity=data.get(0);
        equipo.deleteEquipo(entity.getId());
        EquipoEntity deleted=em.find(EquipoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
