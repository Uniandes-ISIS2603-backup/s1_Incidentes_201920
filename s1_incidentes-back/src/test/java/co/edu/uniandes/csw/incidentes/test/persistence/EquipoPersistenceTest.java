/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.EquipoEntity;
import co.edu.uniandes.csw.incidentes.persistence.EquipoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Julian Jaimes
 */

@RunWith(Arquillian.class)
public class EquipoPersistenceTest {
    
    @Inject
    private EquipoPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(EquipoEntity.class).addClass(EquipoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createEquipoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        
        EquipoEntity ee = ep.create(newEntity);
        
        Assert.assertNotNull(ee);
        
        EquipoEntity entity = em.find(EquipoEntity.class, ee.getId());
        
        Assert.assertEquals(newEntity.getIdEquipo(), entity.getIdEquipo());
    }
    /**
    @Test
    public void findEquipoTest() {
        
    }
    
    @Test
    public void findAllEquipoTest() {
        
    }
    
    @Test
    public void updateEquipoTest() {
        
    }
    
    @Test
    public void deleteEquipoTest() {
        
    }*/
}
