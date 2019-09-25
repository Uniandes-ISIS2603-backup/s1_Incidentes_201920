/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Camilo Castiblanco
 */
public class CoordinadorDTO implements Serializable{
    
    private Long id;
    
    private String nombre; 
    
    public CoordinadorDTO(){
    
    }
    
     public CoordinadorDTO(CoordinadorEntity coordinadorEntity) {
        if (coordinadorEntity != null) {
           this.id = coordinadorEntity.getId();
           this.nombre = coordinadorEntity.getName();
        }
    }
    
    public CoordinadorEntity toEntity() {
        CoordinadorEntity coordinadorEntity = new CoordinadorEntity();
        coordinadorEntity.setId(this.id);
        coordinadorEntity.setName(this.nombre);
        return coordinadorEntity;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
