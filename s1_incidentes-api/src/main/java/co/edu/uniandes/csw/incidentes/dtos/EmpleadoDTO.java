/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import java.io.Serializable;

/**
 *
 * @author xx
 */
public class EmpleadoDTO implements Serializable{

    public EmpleadoDTO() {
    
    }
    
    public EmpleadoDTO(EmpleadoEntity empleadoEntity) {
        if (empleadoEntity != null) {

        }
    }
    
    public EmpleadoEntity toEntity() {
        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        
        return empleadoEntity;
    }
}
