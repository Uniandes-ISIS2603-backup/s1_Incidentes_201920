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
 * @author Julian Jaimes
 */

@Entity
public class EmpleadoEntity extends UserEntity implements Serializable  {
    private String nombre;
    private Integer numIncidentes;
    @PodamExclude
    @OneToMany(mappedBy = "empleado",fetch=FetchType.LAZY,orphanRemoval=true)
    private List<IncidenteEntity> incidentes = new ArrayList<>();

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
    @Override
    public boolean equals(Object ob){
        if(! super.equals(ob)){
            return false;
        }
        EmpleadoEntity aOb=(EmpleadoEntity) ob;
        String empleado=aOb.getNombre();
        return empleado.equals(aOb.getNombre());
    }
    @Override
    public int hashCode(){
        if(this.getNombre()!=null){
            return this.getNombre().hashCode();
        }
        return super.hashCode();
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
