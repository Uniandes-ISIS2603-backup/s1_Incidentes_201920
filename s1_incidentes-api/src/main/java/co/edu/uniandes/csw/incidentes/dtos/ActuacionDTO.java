/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import java.io.Serializable;

/**
 *
 * @author xx
 */
public class ActuacionDTO implements Serializable{
    
    public ActuacionDTO() {

    }

    public ActuacionDTO(ActuacionEntity actuacionEntity) {
        if (actuacionEntity != null) {

        }
    }

    public ActuacionEntity toEntity() {
        ActuacionEntity actuacionEntity = new ActuacionEntity();

        return actuacionEntity;
    }
    
}
