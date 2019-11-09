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
 * CoordinadorDTO Objeto de transferencia de datos de Coordinadores. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string,
 *      "username": string,
 *      "password": string
 *   }
 * </pre> Por ejemplo una editorial se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 * "nombre" : "Paula",
 * "username" : "Paula",
 * "password" : "clave"
 * }
 * </pre>
 *
 * @author Juan Camilo Castiblanco
 */
public class CoordinadorDTO implements Serializable {

    /*
    * Atributo que representa el id.
     */
    private Long id;

    /*
    * Atributo que representa el usuario.
     */
    private String username;

    /*
    * Atributo que representa la contraseña.
     */
    private String password;

    /*
    * Atributo que representa el nombre.
     */
    private String nombre;

    /**
     * Constructor por defecto
     */
    public CoordinadorDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param coordinadorEntity: Es la entidad que se va a convertir a DTO
     */
    public CoordinadorDTO(CoordinadorEntity coordinadorEntity) {
        if (coordinadorEntity != null) {
            this.id = coordinadorEntity.getId();
            this.nombre = coordinadorEntity.getName();
            this.username = coordinadorEntity.getUsername();
            this.password = coordinadorEntity.getPassword();
        }
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public CoordinadorEntity toEntity() {
        CoordinadorEntity coordinadorEntity = new CoordinadorEntity();
        coordinadorEntity.setId(this.id);
        coordinadorEntity.setName(this.nombre);
        coordinadorEntity.setUsername(this.username);
        coordinadorEntity.setPassword(this.password);
        return coordinadorEntity;
    }

    /**
     * Devuelve el nombre de usuario del coordinador.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Devuelve la contraseña de usuario del coordinador.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve el id del coordinador.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Devuelve el nombre del coordinador.
     *
     * @return the name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica la contraseña del coordinador.
     *
     * @param contrasena the password to set
     */
    public void setPassword(String contrasena) {
        this.password = contrasena;
    }

    /**
     * Modifica el nombre de usuario del coordinador.
     *
     * @param username the name to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Modifica el id del coordinador.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Modifica el nombre del coordinador.
     *
     * @param nombre the name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
