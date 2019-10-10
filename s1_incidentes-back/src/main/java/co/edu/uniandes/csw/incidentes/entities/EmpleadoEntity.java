/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un empleado en la persistencia y permite su
 * serialización.
 * 
 * @author Daniel Reyes
 */

@Entity
public class EmpleadoEntity extends User2Entity implements Serializable  {
    private String nombre;
    private Integer numIncidentes;
    @PodamExclude
    @OneToMany(mappedBy = "empleado",fetch=FetchType.LAZY)
    private List<IncidenteEntity> incidentes = new ArrayList<IncidenteEntity>();

    /**
     * Devuelve el nombre del empleado
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del empleado
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el numero de incidentes del empleado
     * @return the numIncidentes
     */
    public Integer getNumIncidentes() {
        return numIncidentes;
    }

    /**
     * Modifica el numero de incidentes del empleado
     * @param numIncidentes the numIncidentes to set
     */
    public void setNumIncidentes(Integer numIncidentes) {
        this.numIncidentes = numIncidentes;
    }

    /**
     * Devuelve los incidentes del empleado
     * @return the incidentes
     */
    public List<IncidenteEntity> getIncidentes() {
        return incidentes;
    }

    /**
     * Modifica los incidentes del empleado
     * @param incidentes the incidentes to set
     */
    public void setIncidentes(List<IncidenteEntity> incidentes) {
        this.incidentes = incidentes;
    }
}
