/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.adapters.DateAdapter;
import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author xx
 */
public class ActuacionDTO implements Serializable{
    
    private Long id;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaHora;
    
    private String descripcion;
    
    /**
     * Relación a un incidente
     * dado que esta tiene cardinalidad 1.
     */
    private IncidenteDTO incidente;
    
    /**
     * Constructor por defecto
     */
    public ActuacionDTO() {
    }

    /**
     * Constructor a partir de la entidad
     * @param actuacionEntity La entidad de la actuación
     */
    public ActuacionDTO(ActuacionEntity actuacionEntity) {
        if (actuacionEntity != null) {
            this.id = actuacionEntity.getId();
            this.fechaHora = actuacionEntity.getFechaHora();
            this.descripcion = actuacionEntity.getDescripcion();
            if (actuacionEntity.getIncidente() != null) {
                this.incidente = new IncidenteDTO(actuacionEntity.getIncidente());
            } else {
                actuacionEntity.setIncidente(null);
            }
        }
    }

    public ActuacionEntity toEntity() {
        ActuacionEntity actuacionEntity = new ActuacionEntity();
        actuacionEntity.setId(this.id);
        actuacionEntity.setFechaHora(this.fechaHora);
        actuacionEntity.setDescripcion(this.descripcion);
        if (incidente != null) {
            actuacionEntity.setIncidente(incidente.toEntity());
        }
        return actuacionEntity;
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
     * @return the fechaHora
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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
     * @return the incidente
     */
    public IncidenteDTO getIncidente() {
        return incidente;
    }

    /**
     * @param incidente the incidente to set
     */
    public void setIncidente(IncidenteDTO incidente) {
        this.incidente = incidente;
    }
    
    
}
