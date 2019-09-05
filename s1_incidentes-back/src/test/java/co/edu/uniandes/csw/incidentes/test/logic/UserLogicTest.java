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
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Camilo Castiblanco
 */
public class UserLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UserLogic ul;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UserEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UserEntity.class.getPackage())
                .addPackage(UserLogic.class.getPackage())
                .addPackage(UserPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }


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

    private void clearData() {
        em.createQuery("delete from UserEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UserEntity entity = factory.manufacturePojo(UserEntity.class);
            em.persist(entity);
            entity.setUsername(new String());
            data.add(entity);
        }
        
    }

    /*
    @Test
    public void createIUserTest() throws BusinessLogicException{
        UserEntity newEntity = factory.manufacturePojo(UserEntity.class);
        UserEntity result = ul.createUser(newEntity);
        Assert.assertNotNull(result);
        UserEntity entity = em.find(UserEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getUsername(), entity.getUsername());
        Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
    }

    @Test (expected = BusinessLogicException.class)
    public void createUserUsernameNull()throws BusinessLogicException{
        UserEntity newEntity=factory.manufacturePojo(UserEntity.class);
        newEntity.setUsername(null);
        UserEntity resultado= ul.createUser(newEntity);
    }
    @Test (expected = BusinessLogicException.class)
    public void createUserPasswordNull()throws BusinessLogicException{
        UserEntity newEntity=factory.manufacturePojo(UserEntity.class);
        newEntity.setPassword(null);
        UserEntity resultado= ul.createUser(newEntity);
    }
*/
}