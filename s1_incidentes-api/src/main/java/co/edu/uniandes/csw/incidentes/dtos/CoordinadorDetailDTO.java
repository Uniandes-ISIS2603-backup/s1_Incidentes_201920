/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que extiende de {@link EditorialDTO} para manejar las relaciones entre
 * los Editorial JSON y otros DTOs. Para conocer el contenido de la una
 * Editorial vaya a la documentacion de {@link EditorialDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string,
 *      "username": string,
 *      "password": string,
 *      "incidentes": [{@link IncidenteDTO}],
 *      "tecnicos": [{@link TecnicoDTO}]
 *   }
 * </pre>  <br>
 *
 * @author Juan Camilo Castiblanco
 */
public class CoordinadorDetailDTO extends CoordinadorDTO implements Serializable {

    /**
     * Lista de tecnicos.
     */
    private List<TecnicoDTO> tecnicos;

    /**
     * Lista de incidentes.
     */
    private List<IncidenteDTO> incidentes;

    /**
     * Constructor por defecto.
     */
    public CoordinadorDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param coordinadorEntity La entidad de la editorial para transformar a
     * DTO.
     */
    public CoordinadorDetailDTO(CoordinadorEntity coordinadorEntity) {
        super(coordinadorEntity);
        if (coordinadorEntity != null) {
            if (coordinadorEntity.getTecnicos() != null) {
                tecnicos = new ArrayList<>();
                for (TecnicoEntity entityTecnico : coordinadorEntity.getTecnicos()) {
                    tecnicos.add(new TecnicoDTO(entityTecnico));
                }
            }

            if (coordinadorEntity.getIncidentes() != null) {
                incidentes = new ArrayList<>();
                for (IncidenteEntity entityIncidente : coordinadorEntity.getIncidentes()) {
                    incidentes.add(new IncidenteDTO(entityIncidente));
                }
            }
        }

    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public CoordinadorEntity toEntity() {
        CoordinadorEntity coordinadorEntity = super.toEntity();

        if (tecnicos != null) {
            List<TecnicoEntity> tecnicoEntity = new ArrayList<>();
            for (TecnicoDTO dtoTecnico : tecnicos) {
                tecnicoEntity.add(dtoTecnico.toEntity());
            }
            coordinadorEntity.setTecnicos(tecnicoEntity);
        }

        if (incidentes != null) {
            List<IncidenteEntity> incidenteEntity = new ArrayList<>();
            for (IncidenteDTO dtoIncidente : incidentes) {
                incidenteEntity.add(dtoIncidente.toEntity());
            }
            coordinadorEntity.setIncidentes(incidenteEntity);
        }

        return coordinadorEntity;
    }

    /**
     * Devuelve la lista de tecnicos del coordinador.
     *
     * @return tecnicos
     */
    public List<TecnicoDTO> getTecnicos() {
        return tecnicos;
    }

    /**
     * Modifica la lista de tecnicos del coordinador.
     *
     * @param tecnicos
     */
    public void setTecnicos(List<TecnicoDTO> tecnicos) {
        this.tecnicos = tecnicos;
    }

    /**
     * Devuelve la lista de incidentes del coordinador.
     *
     * @return incidentes
     */
    public List<IncidenteDTO> getIncidentes() {
        return incidentes;
    }

    /**
     * Modifica la lista de incidentes del coordinador.
     *
     * @param tecnicos
     */
    public void setIncidentes(List<IncidenteDTO> incidentes) {
        this.incidentes = incidentes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
