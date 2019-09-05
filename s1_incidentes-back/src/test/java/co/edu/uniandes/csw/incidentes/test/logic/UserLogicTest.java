/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.UserLogic;
import co.edu.uniandes.csw.incidentes.entities.UserEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.UserPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Camilo Castiblanco
 */
public class UserLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addPackage(UserEntity.class.getPackage())
                .addPackage(UserLogic.class.getPackage())
                .addPackage(UserPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
                
    }
    
    @PersistenceContext
    private EntityManager em;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UserLogic user;
    
    @Test
    public void createUser() throws BusinessLogicException{
        UserEntity newEntity = factory.manufacturePojo(UserEntity.class);
        UserEntity result = user.createUser(newEntity);
        Assert.assertNotNull(result);
        
        UserEntity entity = em.find(UserEntity.class, result.getId());
        Assert.assertEquals(entity.getUsername(), result.getUsername());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUserUsernameNull() throws BusinessLogicException{
        UserEntity newEntity = factory.manufacturePojo(UserEntity.class);
        newEntity.setUsername(null);
        UserEntity result = user.createUser(newEntity);
    }
}