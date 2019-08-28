/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.persistence.EmpleadoPersistence;
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
 * @author Daniel Reyes
 */

@RunWith(Arquillian.class)
public class EmpleadoPersistenceTest {
    
    @Inject
    private EmpleadoPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addClass(EmpleadoEntity.class)
                .addClass(EmpleadoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml");
                
    }
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        EmpleadoEntity result = ep.create(ee);
        Assert.assertNotNull(result);
        
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, result.getId());
        Assert.assertEquals(ee.getNombre(), entity.getNombre());
    }
}
