
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.Serializable;

/**
 *
 * @author da.silvaa
 */
public class TecnicoDTO implements Serializable {

    private Long id;

    private Integer numCasos;

    private String especialidad;

    private String username;

    private String password;
    
    private CoordinadorDTO coordinador;

    public TecnicoDTO() {
    }
    
    

    public TecnicoDTO(TecnicoEntity tecnicoEntity) {
        if (tecnicoEntity != null) {
            this.id = tecnicoEntity.getId();
            this.numCasos = tecnicoEntity.getNumCasos();
            this.especialidad = tecnicoEntity.getEspecialidad();
            this.username = tecnicoEntity.getUsername();
            this.password = tecnicoEntity.getPassword();
            if (tecnicoEntity.getCoordinador() != null) {
                this.coordinador = new CoordinadorDTO(tecnicoEntity.getCoordinador());
            } else {
                this.coordinador = null;
             }
        }
    }

    public TecnicoEntity toEntity() {
        TecnicoEntity tecnicoEntity = new TecnicoEntity();
        tecnicoEntity.setId(this.id);
        tecnicoEntity.setNumCasos(this.numCasos);
        tecnicoEntity.setEspecialidad(this.especialidad);
        tecnicoEntity.setUsername(this.username);
        tecnicoEntity.setPassword(this.password);
        if (this.getCoordinador() != null) {
            tecnicoEntity.setCoordinador(this.coordinador.toEntity());
        }
        return tecnicoEntity;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;

    }

    public Integer getNumCasos() {
        return numCasos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumCasos(Integer nombre) {
        this.numCasos = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public CoordinadorDTO getCoordinador() {
        return coordinador;
    }

    /**
     * @param coordinador the coordinador to set
     */
    public void setCoordinador(CoordinadorDTO coordinador) {
        this.coordinador = coordinador;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
