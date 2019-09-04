/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author df.foreroc
 */
@Stateless
public class IncidenteLogic {
    @Inject
    private IncidentePersistence persistence;
    public IncidenteEntity createIncidente(IncidenteEntity incidente) throws BusinessLogicException
    {
        if(incidente.getDescripcion()==null)
        {
            throw new BusinessLogicException("La descripcion del incidente es nula");
        }
        if(incidente.getEquipo()==null)
        {
            throw new BusinessLogicException("El equipo del incidente es nula");
        }
        if(incidente.getPrioridad()==null)
        {
            throw new BusinessLogicException("La prioridad del incidente es nula");
        }
        if(incidente.getFecha()==null)
        {
            throw new BusinessLogicException("La fecha del incidente es nula");
        }
        incidente = persistence.create(incidente);
        return incidente;
    }
    
}
