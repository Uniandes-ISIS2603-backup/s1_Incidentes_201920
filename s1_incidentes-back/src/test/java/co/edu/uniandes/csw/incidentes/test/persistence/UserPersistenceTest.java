/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.UserEntity;
import co.edu.uniandes.csw.incidentes.persistence.UserPersistence;
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
public class UserPersistenceTest {
    
    @Inject
    UserPersistence up;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserEntity.class)
                .addClass(UserPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml");
                
    }
    @Inject
    UserTransaction utx;

    private List<UserEntity> data = new ArrayList<>();
   
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
        em.createQuery("delete from UserEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UserEntity entity = factory.manufacturePojo(UserEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
   public void createTest()
   {
       PodamFactory factory = new PodamFactoryImpl();
       UserEntity user =factory.manufacturePojo(UserEntity.class);
       UserEntity result = up.create(user);
       Assert.assertNotNull(result);
       UserEntity entity = em.find(UserEntity.class, result.getId());
       Assert.assertEquals(user.getPassword(), entity.getPassword());
       Assert.assertEquals(user.getUsername(), entity.getUsername());
        
   }
    @Test
    public void getUserTest() {
        List<UserEntity> list = up.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (UserEntity ent : list) {
            boolean found = false;
            for (UserEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getUsersTest() {
        UserEntity entity = data.get(0);
        UserEntity newEntity = up.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPassword(), newEntity.getPassword());
        
    }

    @Test
    public void updateUserTest() {
        UserEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UserEntity newEntity = factory.manufacturePojo(UserEntity.class);

        newEntity.setId(entity.getId());

        up.update(newEntity);

        UserEntity resp = em.find(UserEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getPassword(), resp.getPassword());
    }
    
    @Test
    public void deleteUserTest() {
        UserEntity entity = data.get(0);
        up.delete(entity.getId());
        UserEntity deleted = em.find(UserEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void getUserByUsernameTest() {
        UserEntity entity = data.get(0);
        UserEntity newEntity = up.findByUsername(entity.getUsername());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPassword(), newEntity.getPassword());
    }
}
