/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.persistence.TecnicoPersistance;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith; 
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import javax.persistence.EntityManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Assert;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Estudiante Diana Alejandra Silva Alvarez
 */
@RunWith(Arquillian.class)
public class TecnicoPersistanceTest {
    
    @Inject
    private TecnicoPersistance tp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addClass(TecnicoEntity.class).addClass(TecnicoPersistance.class).addAsManifestResource("","");
    }
    
    @Test
    public void createTeccnicoTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        
        TecnicoEntity ee = tp.create(newEntity);
        
        Assert.assertNotNull(ee);
        TecnicoEntity entity = em.find(TecnicoEntity.class, ee.getId());
        
        Assert.assertEquals(newEntity.getIncidenteASignado(), entity.getIncidenteASignado());
        
    }
    
}
