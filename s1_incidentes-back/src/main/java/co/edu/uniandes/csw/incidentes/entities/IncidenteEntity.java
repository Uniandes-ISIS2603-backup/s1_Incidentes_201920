/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.entities;

import co.edu.uniandes.csw.incidentes.podam.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaHoraInicio;
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaHoraFinal;
    private String descripcion;
    private String observaciones;
    private Integer calificacion;
    private String categoria;
    private String prioridad;
    private Boolean solucionado;
    private Boolean reabrir;
    private String equipo;
    
    @PodamExclude
    @OneToMany(mappedBy = "incidente",fetch=FetchType.LAZY)
    private List<ActuacionEntity> actuaciones = new ArrayList<ActuacionEntity>();


    public IncidenteEntity() {
    }
    
    public List<ActuacionEntity> getActuaciones() {
        return actuaciones;
    }

    public void setActuaciones(List<ActuacionEntity> actu) {
        this.actuaciones = actu;
    }
    public void addActuacion(ActuacionEntity actu) {
        this.actuaciones.add(actu);
    }
    public CoordinadorEntity getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(CoordinadorEntity coordinador) {
        this.coordinador = coordinador;
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

    /**
     * @return the fechaHoraInicio
     */
    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * @param fechaHoraInicio the fechaHoraInicio to set
     */
    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * @return the fechaHoraFinal
     */
    public Date getFechaHoraFinal() {
        return fechaHoraFinal;
    }

    /**
     * @param fechaHoraFinal the fechaHoraFinal to set
     */
    public void setFechaHoraFinal(Date fechaHoraFinal) {
        this.fechaHoraFinal = fechaHoraFinal;
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
}
