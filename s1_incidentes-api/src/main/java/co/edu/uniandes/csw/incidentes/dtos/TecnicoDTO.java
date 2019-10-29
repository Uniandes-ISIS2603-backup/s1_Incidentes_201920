
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import java.io.Serializable;

/**
 *
 * @author da.silvaa
 */
public class TecnicoDTO implements Serializable {

    private Long id;

    private Integer numCasos;

    private String especialidad;

    /*
    * Atributo que representa el usuario.
     */
    private String username;

    /*
    * Atributo que representa la contraseña.
     */
    private String password;

    /*
    * constructor vacio para serializacion
    */
    public TecnicoDTO(){
        
    }
    
    public TecnicoDTO(TecnicoEntity coordinadorEntity) {

    }

    public TecnicoEntity toEntity() {
        TecnicoEntity tecnicoEntity = new TecnicoEntity();
        return tecnicoEntity;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
