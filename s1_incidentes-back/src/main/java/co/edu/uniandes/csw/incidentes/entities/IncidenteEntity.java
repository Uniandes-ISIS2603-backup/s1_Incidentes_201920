/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.entities;

import co.edu.uniandes.csw.incidentes.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author df.foreroc
 */
@Entity
public class IncidenteEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @ManyToOne
    private CoordinadorEntity coordinador;
    @PodamExclude
    @ManyToOne
    private EmpleadoEntity empleado;
    private String tipo;
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    private String descripcion;
    private String equipo;
    private String prioridad;
    private Boolean solucionado;
    private Boolean reabrir;

    public IncidenteEntity() {
    }

    public CoordinadorEntity getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(CoordinadorEntity coordinador) {
        this.coordinador = coordinador;
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the equipo
     */
    public String getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    /**
     * @return the prioridad
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the solucionado
     */
    public Boolean getSolucionado() {
        return solucionado;
    }

    /**
     * @param solucionado the solucionado to set
     */
    public void setSolucionado(Boolean solucionado) {
        this.solucionado = solucionado;
    }

    /**
     * @return the reabrir
     */
    public Boolean getReabrir() {
        return reabrir;
    }

    /**
     * @param reabrir the reabrir to set
     */
    public void setReabrir(Boolean reabrir) {
        this.reabrir = reabrir;
    }

    /**
     * @return the empleado
     */
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }
}
