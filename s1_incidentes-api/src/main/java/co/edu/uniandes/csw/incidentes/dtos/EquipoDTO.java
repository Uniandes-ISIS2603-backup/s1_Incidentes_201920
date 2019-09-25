/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import java.io.Serializable;

/**
 *
 * @author Julian Jaimes
 */
public class EquipoDTO implements Serializable {
    
    private Integer idEquipo;
    
    public EquipoDTO() {
        
    }

    /**
     * @return the idEquipo
     */
    public Integer getIdEquipo() {
        return idEquipo;
    }

    /**
     * @param idEquipo the idEquipo to set
     */
    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }
}
