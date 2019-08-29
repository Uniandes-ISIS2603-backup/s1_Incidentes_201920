/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.UserEntity;
import co.edu.uniandes.csw.incidentes.persistence.UserPersistence;
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
public class UserPersistenceTest {
    
    @Inject
    UserPersistence up;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addClass(UserEntity.class)
                .addClass(UserPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml");
                
    }
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        UserEntity user = factory.manufacturePojo(UserEntity.class);
        
        UserEntity result = up.create(user);
        Assert.assertNotNull(result);
        
        UserEntity entity= em.find(UserEntity.class, result.getId());                
    } 
   
    @Test
    public void findTest(){
        PodamFactory factory = new PodamFactoryImpl();
        UserEntity user = factory.manufacturePojo(UserEntity.class);
        
        UserEntity result = up.create(user);
        Assert.assertNotNull(result);
        
        UserEntity entity = em.find(UserEntity.class, result.getId());
        Assert.assertEquals(user.getId(), entity.getId());
    }
    
    @Test
    public void listAllTest(){
        PodamFactory factory = new PodamFactoryImpl();
        UserEntity user = factory.manufacturePojo(UserEntity.class);
        
        UserEntity result = up.create(user);
        Assert.assertNotNull(result);
        
        // COntinuar implementación luego ...
    }
 /*   
    @Test
    public void modifyTest(){
        PodamFactory factory = new PodamFactoryImpl();
        UserEntity user = factory.manufacturePojo(UserEntity.class);
        
        UserEntity result = up.create(user);
        Assert.assertNotNull(result);
        
        //UserEntity entity = em.modify(user);
        //Assert.assertNotNull(result);
    }
   */ 
    @Test
    public void removeTest(){
        PodamFactory factory = new PodamFactoryImpl();
        UserEntity user = factory.manufacturePojo(UserEntity.class);
        UserEntity result = up.create(user);
        Assert.assertNotNull(result);
        
        em.remove(result);
        Assert.assertNull(result);
    }
}
