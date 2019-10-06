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
public class CoordinadorDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String nombre;

    public CoordinadorDTO() {

    }

    public CoordinadorDTO(CoordinadorEntity coordinadorEntity) {
        if (coordinadorEntity != null) {
            this.id = coordinadorEntity.getId();
            this.nombre = coordinadorEntity.getName();
            this.username = coordinadorEntity.getUsername();
            this.password = coordinadorEntity.getPassword();
        }
    }

    public CoordinadorEntity toEntity() {
        CoordinadorEntity coordinadorEntity = new CoordinadorEntity();
        coordinadorEntity.setId(this.id);
        coordinadorEntity.setName(this.nombre);
        coordinadorEntity.setUsername(this.username);
        coordinadorEntity.setPassword(this.password);
        return coordinadorEntity;
    }

    public String getUsername() {
        return username;
    }

    public String getContraseña() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setContraseña(String contraseña) {
        this.password = contraseña;
    }

    public void setUsername(String username) {
        this.username = username;
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
