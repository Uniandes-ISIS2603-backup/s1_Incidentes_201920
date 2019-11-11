/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.incidentes.ejb.CoordinadorTecnicosLogic;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class CoordinadorTecnicosLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CoordinadorLogic coordinadorLogic;
    @Inject
    private CoordinadorTecnicosLogic coordinadorTecnicosLogic;
    
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
                .addPackage(CoordinadorLogic.class.getPackage())
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
    public void addTecnicosTest() {
        CoordinadorEntity entity = data.get(0);
        TecnicoEntity tecnicoEntity = tecnicosData.get(1);
        TecnicoEntity response = coordinadorTecnicosLogic.addTecnico(tecnicoEntity.getId(), 
                entity.getId());
        Assert.assertNotNull(response);
        Assert.assertEquals(tecnicoEntity.getId(), response.getId());
    }
    
    @Test
    public void getTecnicosTest() {
        List<TecnicoEntity> list = coordinadorTecnicosLogic.getTecnicos(data.get(0).getId());
        
        Assert.assertEquals(1, list.size());
    }
    
    @Test
    public void getTecnicoTest() throws BusinessLogicException {
        CoordinadorEntity entity = data.get(0);
        TecnicoEntity tecnicoEntity = tecnicosData.get(0);
        TecnicoEntity response = coordinadorTecnicosLogic.getTecnico(entity.getId(),
                tecnicoEntity.getId());
        
        Assert.assertEquals(tecnicoEntity.getId(), response.getId());
        Assert.assertEquals(tecnicoEntity.getEspecialidad(), response.getEspecialidad());
        Assert.assertEquals(tecnicoEntity.getNumCasos(), response.getNumCasos());
        Assert.assertEquals(tecnicoEntity.getUsername(), response.getUsername());
        Assert.assertEquals(tecnicoEntity.getPassword(), response.getPassword());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void getTecnicoNoAsociadoTest() throws BusinessLogicException {
        CoordinadorEntity entity = data.get(0);
        TecnicoEntity tecnicoEntity = tecnicosData.get(1);
        coordinadorTecnicosLogic.getTecnico(entity.getId(), tecnicoEntity.getId());
    }
    
    @Test
    public void replaceTecnicosTest() {
        CoordinadorEntity entity = data.get(0);
        List<TecnicoEntity> list = tecnicosData.subList(1,3);
        coordinadorTecnicosLogic.replaceTecnicos(entity.getId(), list);
        
        entity = coordinadorLogic.getCoordinador(entity.getId());
        Assert.assertFalse(entity.getTecnicos().contains(tecnicosData.get(0)));
        Assert.assertTrue(entity.getTecnicos().contains(tecnicosData.get(1)));
        Assert.assertTrue(entity.getTecnicos().contains(tecnicosData.get(2)));
    }
}
