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
    private Date fecha;
    private String tipo;
    private String descripcion;
    private String equipo;
    private String prioridad;
    private Boolean solucionado;
    private Boolean reabrir;
    
    public IncidenteDTO(){
        
        
    }
    public IncidenteDTO(IncidenteEntity iEntity) {
        if (iEntity != null) {
            this.id = iEntity.getId();
            this.descripcion = iEntity.getDescripcion();
            this.fecha= iEntity.getFecha();
            
            this.tipo = iEntity.getTipo();
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
        incidenteEntity.setFecha(this.getFecha());
        incidenteEntity.setTipo(this.tipo);
        incidenteEntity.setEquipo(this.equipo);
        incidenteEntity.setPrioridad(this.prioridad);
        incidenteEntity.setSolucionado(this.solucionado);
        incidenteEntity.setReabrir(this.reabrir);
        
        return incidenteEntity;
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
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
}
