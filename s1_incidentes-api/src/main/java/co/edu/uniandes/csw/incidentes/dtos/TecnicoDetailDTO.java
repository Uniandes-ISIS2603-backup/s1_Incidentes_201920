/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author da.silvaa
 */
public class TecnicoDetailDTO extends TecnicoDTO implements Serializable {

    private List<IncidenteDTO> incidentes;

    public TecnicoDetailDTO() {
        super();
    }
    
    public TecnicoDetailDTO(TecnicoEntity tecnicoEntity) {
        super(tecnicoEntity);
        if (tecnicoEntity != null) {

            if (tecnicoEntity.getIncidentes() != null) {
                incidentes = new ArrayList<>();
                for (IncidenteEntity entityIncidente : tecnicoEntity.getIncidentes()) {
                    incidentes.add(new IncidenteDTO(entityIncidente));
                }
            }
        }
    }
    @Override
    public TecnicoEntity toEntity() {
        TecnicoEntity tecnicoEntity = super.toEntity();

        if (incidentes != null) {
            List<IncidenteEntity> incidenteEntity = new ArrayList<>();
            for (IncidenteDTO dtoIncidente : incidentes) {
                incidenteEntity.add(dtoIncidente.toEntity());
            }
            tecnicoEntity.setIncidentes(incidenteEntity);
        }

        return tecnicoEntity;
    }

    public void setIncidentes(List<IncidenteDTO> incidentes) {
        this.incidentes = incidentes;
    }

    public List<IncidenteDTO> getIncidentes() {
        return incidentes;
    }
 
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}



