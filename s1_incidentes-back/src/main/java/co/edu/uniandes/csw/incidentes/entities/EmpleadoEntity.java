/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Daniel Reyes
 */

@Entity
public class EmpleadoEntity extends User2Entity implements Serializable  {
    private String nombre;
    private String tipo;
    private Boolean incidenteAbierto;
    private Integer numIncidentes;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the incidenteAbierto
     */
    public Boolean getIncidenteAbierto() {
        return incidenteAbierto;
    }

    /**
     * @param incidenteAbierto the incidenteAbierto to set
     */
    public void setIncidenteAbierto(Boolean incidenteAbierto) {
        this.incidenteAbierto = incidenteAbierto;
    }

    /**
     * @return the numIncidentes
     */
    public Integer getNumIncidentes() {
        return numIncidentes;
    }

    /**
     * @param numIncidentes the numIncidentes to set
     */
    public void setNumIncidentes(Integer numIncidentes) {
        this.numIncidentes = numIncidentes;
    }
}
