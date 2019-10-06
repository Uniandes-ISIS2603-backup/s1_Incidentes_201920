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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Daniel Reyes
 */

@Entity
public class EmpleadoEntity extends User2Entity implements Serializable  {
    private String nombre;
    private Integer numIncidentes;
    @PodamExclude
    @OneToMany(mappedBy = "empleado")
    private List<IncidenteEntity> incidentes = new ArrayList<IncidenteEntity>();

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

    /**
     * @return the incidentes
     */
    public List<IncidenteEntity> getIncidentes() {
        return incidentes;
    }

    /**
     * @param incidentes the incidentes to set
     */
    public void setIncidentes(List<IncidenteEntity> incidentes) {
        this.incidentes = incidentes;
    }
}
