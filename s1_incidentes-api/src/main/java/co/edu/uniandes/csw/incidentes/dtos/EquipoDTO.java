/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.EquipoEntity;
import java.io.Serializable;

/**
 *
 * @author Julian Jaimes
 */
public class EquipoDTO implements Serializable {
    
    private String tipo;
    
    public EquipoDTO() {
        
    }
    
    public EquipoDTO(EquipoEntity entidad) {
        if(entidad != null)
            setTipo(entidad.getTipo());
    }

    public EquipoEntity toEntity() {
        EquipoEntity entidad = new EquipoEntity();
        entidad.setTipo(this.getTipo());
        return entidad;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
