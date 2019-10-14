/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un coordinador en la persistencia y permite su
 * serialización.
 *
 * @author Juan Camilo Castiblanco
 */
@Entity
public class CoordinadorEntity extends UserEntity implements Serializable {

    private String name;

    @PodamExclude
    @OneToMany(mappedBy = "coordinador", fetch = FetchType.LAZY)
    private List<TecnicoEntity> tecnicos = new ArrayList<TecnicoEntity>();

    @PodamExclude
    @OneToMany(mappedBy = "coordinador", fetch = FetchType.LAZY)
    private List<IncidenteEntity> incidentes = new ArrayList<IncidenteEntity>();

    /**
     * Devuelve el nombre del coordinador.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre del coordinador.
     *
     * @param pName the name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * Devuelve los tecnicos del coordinador.
     *
     * @return Lista de entidades de Tecnico.
     */
    public List<TecnicoEntity> getTecnicos() {
        return tecnicos;
    }

    /**
     * Modifica los tecnicos del coordinador.
     *
     * @param tecnicos lista de tecnicos
     */
    public void setTecnicos(List<TecnicoEntity> tecnicos) {
        this.tecnicos = tecnicos;
    }

    /**
     * Modifica los incidentes del coordinador.
     *
     * @param incidentes lista de incidentes
     */
    public void setIncidentes(List<IncidenteEntity> incidentes) {
        this.incidentes = incidentes;
    }

    /**
     * Devuelve los incidentes del coordinador.
     *
     * @return Lista de entidades de Incidentes.
     */
    public List<IncidenteEntity> getIncidentes() {
        return incidentes;
    }

}
