/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Julian Jaimes
 */
public class EmpleadoDTO implements Serializable{

    private Long id;

    private String username;

    private String password;

    private String nombre;
    
    private Integer numIncidentes;

    public EmpleadoDTO() {

    }

    public EmpleadoDTO(EmpleadoEntity empleadoEntity) {
        if (empleadoEntity != null) {
            this.id = empleadoEntity.getId();
            this.nombre = empleadoEntity.getNombre();
            this.username = empleadoEntity.getUsername();
            this.password = empleadoEntity.getPassword();
            this.numIncidentes = empleadoEntity.getNumIncidentes();
        }
    }

    public EmpleadoEntity toEntity() {
        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setId(this.getId());
        empleadoEntity.setNombre(this.getNombre());
        empleadoEntity.setUsername(this.getUsername());
        empleadoEntity.setPassword(this.getPassword());
        empleadoEntity.setNumIncidentes(this.getNumIncidentes());
        return empleadoEntity;
    }

    

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

   

    /**
     * @param numIncidentes the numIncidentes to set
     */
    public void setNumIncidentes(Integer numIncidentes) {
        this.numIncidentes = numIncidentes;
    }
     /**
     * @return the numIncidentes
     */
    public Integer getNumIncidentes() {
        return numIncidentes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
