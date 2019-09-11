/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.ejb.EmpleadoLogic;
import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.EmpleadoPersistence;
import javax.inject.Inject;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Daniel Reyes
 */

@RunWith(Arquillian.class)
public class EmpleadoLogicTest {
    
    @Inject
    private EmpleadoLogic logic;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EmpleadoEntity.class.getPackage())
                .addPackage(EmpleadoLogic.class.getPackage())
                .addPackage(EmpleadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmpleadoTest0() throws BusinessLogicException{
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        ee.setNombre("");
        ee.setTipo("HARDWARE");
        ee.setNumIncidentes(3);
        logic.createEmpleado(ee);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmpleadoTest1() throws BusinessLogicException{
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        ee.setNombre("Nombre");
        ee.setTipo("dd");
        ee.setNumIncidentes(3);
        logic.createEmpleado(ee);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmpleadoTest2() throws BusinessLogicException{
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        ee.setNombre("Nombre");
        ee.setTipo("HARDWARE");
        ee.setNumIncidentes(-2);
        logic.createEmpleado(ee);
    }
    
    @Test
    public void createEmpleadoTest3() throws BusinessLogicException{
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity ee = factory.manufacturePojo(EmpleadoEntity.class);
        ee.setNombre("Nombre");
        ee.setTipo("HARDWARE");
        ee.setNumIncidentes(6);
        logic.createEmpleado(ee);
    }
}
