/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.incidentes.ejb.TecnicoCoordinadorLogic;
import co.edu.uniandes.csw.incidentes.ejb.TecnicoLogic;
import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
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
 * @author c.alcala
 */
@RunWith(Arquillian.class)
public class TecnicoCoordinadorLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TecnicoLogic tecnicoLogic;
    @Inject
    private TecnicoCoordinadorLogic tecnicoCoordinadorLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<CoordinadorEntity> data = new ArrayList<CoordinadorEntity>();
    
    private List<TecnicoEntity> tecnicosData = new ArrayList();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CoordinadorEntity.class.getPackage())
                .addPackage(TecnicoLogic.class.getPackage())
                .addPackage(CoordinadorPersistence.class.getPackage())
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
            e.printStackTrace();;
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() {
        em.createQuery("delete from TecnicoEntity").executeUpdate();
        em.createQuery("delete from CoordinadorEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
           TecnicoEntity tecnicos = factory.manufacturePojo(TecnicoEntity.class);
           em.persist(tecnicos);
           tecnicosData.add(tecnicos);
        }
        for (int i = 0; i < 3; i++) {
            CoordinadorEntity entity = factory.manufacturePojo(CoordinadorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                tecnicosData.get(i).setCoordinador(entity);
            }
        }
    }
    
    @Test
    public void replaceCoordinadorTest() {
        TecnicoEntity entity = tecnicosData.get(0);
        tecnicoCoordinadorLogic.replaceCoordinador(entity.getId(), data.get(1).getId());
        entity = tecnicoLogic.getTecnico(entity.getId());
        Assert.assertEquals(entity.getCoordinador(), data.get(1));
    }
    
    @Test
    public void removeTecnicosTest() throws BusinessLogicException {
        tecnicoCoordinadorLogic.removeCoordinador(tecnicosData.get(0).getId());
        TecnicoEntity response = tecnicoLogic.getTecnico(tecnicosData.get(0).getId());
        Assert.assertNull(response.getCoordinador());
    }
}
