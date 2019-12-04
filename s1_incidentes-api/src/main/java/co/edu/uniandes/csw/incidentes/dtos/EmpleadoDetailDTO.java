/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.ArrayList;

/**
 *
 * @author Julian Jaimes
 */
public class EmpleadoDetailDTO extends EmpleadoDTO implements Serializable{

    public EmpleadoDetailDTO() {
        super();
    }

    private List<IncidenteDTO> incidentes;

    public EmpleadoDetailDTO(EmpleadoEntity empleadoEntity) {
        super(empleadoEntity);
        if (empleadoEntity != null) {
            EmpleadoEntity entidad=empleadoEntity;
            if (entidad.getIncidentes() != null) {
                incidentes = new ArrayList<>();
                for (IncidenteEntity entityIncidente : empleadoEntity.getIncidentes()) {
                    incidentes.add(new IncidenteDTO(entityIncidente));
                }
            }
        }

    }

    @Override
    public EmpleadoEntity toEntity() {
        EmpleadoEntity empleadoEntity = super.toEntity();

        if (incidentes != null) {
            List<IncidenteEntity> incidenteEntity = new ArrayList<>();
            for (IncidenteDTO dtoIncidente : incidentes) {
                incidenteEntity.add(dtoIncidente.toEntity());
            }
            empleadoEntity.setIncidentes(incidenteEntity);
        }

        return empleadoEntity;
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
