/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Camilo Castiblanco
 */
public class CoordinadorDetailDTO extends CoordinadorDTO implements Serializable {

    private List<TecnicoDTO> tecnicos;

    private List<IncidenteDTO> incidentes;

    public CoordinadorDetailDTO() {
        super();
    }

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

    public List<TecnicoDTO> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(List<TecnicoDTO> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public List<IncidenteDTO> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<IncidenteDTO> incidentes) {
        this.incidentes = incidentes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
