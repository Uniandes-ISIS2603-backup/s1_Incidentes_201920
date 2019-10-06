/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author df.foreroc
 */
public class IncidenteDTO implements Serializable{
    private Long id;
  
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
    
    public IncidenteDTO(){
        
        
    }
    public IncidenteDTO(IncidenteEntity iEntity) {
        if (iEntity != null) 
        {
            this.id = iEntity.getId();
            this.fechaHoraInicio= iEntity.getFechaHoraInicio();
            this.fechaHoraFinal= iEntity.getFechaHoraFinal();
            this.observaciones=iEntity.getObservaciones();
            this.descripcion = iEntity.getDescripcion();
            this.calificacion=iEntity.getCalificacion();
            this.categoria = iEntity.getCategoria();
            this.equipo = iEntity.getEquipo();
            this.prioridad = iEntity.getPrioridad();
            this.solucionado = iEntity.getSolucionado();
            this.reabrir = iEntity.getReabrir();
             
        }
    }
    public IncidenteEntity toEntity() {
        IncidenteEntity incidenteEntity = new IncidenteEntity();
        incidenteEntity.setId(this.getId());
        incidenteEntity.setDescripcion(this.getDescripcion());
        incidenteEntity.setObservaciones(this.getObservaciones());
        incidenteEntity.setFechaHoraInicio(this.getFechaHoraInicio());
        incidenteEntity.setFechaHoraFinal(this.getFechaHoraFinal());
        incidenteEntity.setCategoria(this.categoria);
        incidenteEntity.setCalificacion(this.calificacion);
        incidenteEntity.setEquipo(this.getEquipo());
        incidenteEntity.setPrioridad(this.getPrioridad());
        incidenteEntity.setSolucionado(this.getSolucionado());
        incidenteEntity.setReabrir(this.getReabrir());
        
        return incidenteEntity;
    }

  
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
