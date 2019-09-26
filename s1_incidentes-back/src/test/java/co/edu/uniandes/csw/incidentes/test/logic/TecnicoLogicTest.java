/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.TecnicoLogic;
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
    private TecnicoLogic authorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TecnicoEntity> data = new ArrayList<>();
    
    
    
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
        em.createQuery("delete from PrizeEntity").executeUpdate();
        em.createQuery("delete from BookEntity").executeUpdate();
        em.createQuery("delete from AuthorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            TecnicoEntity entity = factory.manufacturePojo(TecnicoEntity.class);
            em.persist(entity);
           // entity.setBooks(new ArrayList<>());
            data.add(entity);
        }
        TecnicoEntity author = data.get(2);
        //BookEntity entity = factory.manufacturePojo(BookEntity.class);
        //entity.getAuthors().add(author);
        //em.persist(entity);
        //author.getBooks().add(entity);

        //PrizeEntity prize = factory.manufacturePojo(PrizeEntity.class);
        //prize.setAuthor(data.get(1));
        //em.persist(prize);
        //data.get(1).getPrizes().add(prize);
    }

    /**
     * Prueba para crear un Author.
     */
    @Test
    public void createAuthorTest() {
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        TecnicoEntity result = authorLogic.createTecnico(newEntity);
        Assert.assertNotNull(result);
        TecnicoEntity entity = em.find(TecnicoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        //Assert.assertEquals(newEntity.getName(), entity.getName());
        //Assert.assertEquals(newEntity.getBirthDate(), entity.getBirthDate());
    }

    /**
     * Prueba para consultar la lista de Authors.
     */
    @Test
    public void getAuthorsTest() {
        List<TecnicoEntity> list = authorLogic.getTecnicos();
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

    /**
     * Prueba para consultar un Author.
     */
    @Test
    public void getAuthorTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity resultEntity = authorLogic.getTecnico(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        //Assert.assertEquals(entity.getName(), resultEntity.getName());
        //Assert.assertEquals(entity.getBirthDate(), resultEntity.getBirthDate());
    }

    /**
     * Prueba para actualizar un Author.
     */
    @Test
    public void updateAuthorTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity pojoEntity = factory.manufacturePojo(TecnicoEntity.class);

        pojoEntity.setId(entity.getId());

        authorLogic.updateTecnico(pojoEntity.getId(), pojoEntity);

        TecnicoEntity resp = em.find(TecnicoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        //Assert.assertEquals(pojoEntity.getName(), resp.getName());
        //Assert.assertEquals(pojoEntity.getBirthDate(), resp.getBirthDate());
    }

    /**
     * Prueba para eliminar un Author
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteAuthorTest() throws BusinessLogicException {
        TecnicoEntity entity = data.get(0);
        authorLogic.deleteAuthor(entity.getId());
        TecnicoEntity deleted = em.find(TecnicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
}
