/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;


import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidentePersistence;

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
 * @author df.foreroc
 */
@RunWith(Arquillian.class)
public class IncidentePersistenceTest {
    @PersistenceContext
    private EntityManager em;
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
              .addClass(IncidenteEntity.class)
              .addClass(IncidentePersistence.class)
              .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml", "beans.xml" );
    }
   @Inject
   IncidentePersistence ip;
   @Test
   public void createTest()
   {
       PodamFactory factory = new PodamFactoryImpl();
       IncidenteEntity incidente =factory.manufacturePojo(IncidenteEntity.class);
       IncidenteEntity result = ip.create(incidente);
       Assert.assertNotNull(result);
       IncidenteEntity entity = em.find(IncidenteEntity.class, result.getId());
        Assert.assertEquals(incidente.getDescripcion(), entity.getDescripcion());     
   }
}
