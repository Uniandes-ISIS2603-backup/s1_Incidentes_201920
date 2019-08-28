/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Camilo Castiblanco
 */

@RunWith(Arquillian.class)
public class CoordinadorPersistenceTest {
    
    @Inject
    CoordinadorPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addClass(CoordinadorEntity.class)
                .addClass(CoordinadorPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml");
                
    }
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        CoordinadorEntity coordinador = factory.manufacturePojo(CoordinadorEntity.class);
        
        CoordinadorEntity result = cp.create(coordinador);
        Assert.assertNotNull(result);
        
        CoordinadorEntity entity = em.find(CoordinadorEntity.class, result.getId());
                
        Assert.assertEquals(coordinador.getId(), entity.getId());
        
    }
}