/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.persistence;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistance;
import javax.inject.Inject;
import junit.framework.Assert;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Camilo Castiblanco
 */
@RunWith(Arquillian.class)
public class CoordinadorPersistanceTest {

    @Inject
    CoordinadorPersistance cp;
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        CoordinadorEntity coordinador = factory.manufacturePojo(CoordinadorEntity.class);
        
        CoordinadorEntity result = cp.create(coordinador);
        Assert.assertNotNull(result);
    }   
}