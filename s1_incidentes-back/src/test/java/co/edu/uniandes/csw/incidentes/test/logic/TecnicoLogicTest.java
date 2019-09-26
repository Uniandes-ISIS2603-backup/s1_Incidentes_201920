/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.test.logic;

import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.TecnicoPersistence;
import javax.ejb.Stateless;

/**
 *
 * @author Estudiante da.silvaa
 */
@Stateless
public class TecnicoLogicTest {
    
    private TecnicoPersistence persistence;
    public TecnicoEntity createTecnico(TecnicoEntity tecnico) throws BusinessLogicException{
        
        if(tecnico.getId()== null)
        {
            throw new BusinessLogicException("el ID del tecnico esta vacio");
        }
        
        tecnico = persistence.create(tecnico);
        return tecnico;
    }
    
    
    
    
}
