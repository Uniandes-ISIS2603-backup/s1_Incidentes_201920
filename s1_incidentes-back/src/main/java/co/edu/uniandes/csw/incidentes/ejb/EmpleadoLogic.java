/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.EmpleadoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Reyes
 */

@Stateless
public class EmpleadoLogic {
    
    @Inject
    private EmpleadoPersistence persistence;
    
    public EmpleadoEntity createEmpleado(EmpleadoEntity empleado) throws BusinessLogicException{
        
        if(empleado.getNombre() == null || empleado.getNombre() == ""){
            throw new BusinessLogicException("El nombre es nulo");
        }
        if(empleado.getTipo() == null){
            throw new BusinessLogicException("El tipo no puede ser nulo");
        }
        
        if(!(empleado.getTipo().equals("HARDWARE") || empleado.getTipo().equals("SW_SO") || 
                empleado.getTipo().equals("SW_AD"))){
            throw new BusinessLogicException("El tipo debe de ser HARDWARE, SW_SO ó SW_AD");
        }
        if(empleado.getNumIncidentes() < 0){
            throw new BusinessLogicException("El número de incidentes no puede ser menor a cero");
        }
        
        empleado = persistence.create(empleado);
        return empleado;
        
    }
    
}
