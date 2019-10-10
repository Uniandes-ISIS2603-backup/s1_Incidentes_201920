/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import java.util.List;
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
        if(incidente.getFechaHoraInicio()==null)
        {
            throw new BusinessLogicException("La fecha del incidente es nula");
        }
        if(incidente.getCategoria()==null)
        {
            throw new BusinessLogicException("La categoria del incidente es nula");
        }
        if(!incidente.getReabrir())
        {
            throw new BusinessLogicException("El incidente aun no se puede reabrir");
        }
        if(!incidente.getSolucionado())
        {
            throw new BusinessLogicException("El incidente aun no se puede solucionar");
        }
        if(incidente.getObservaciones()==null)
        {
            throw new BusinessLogicException("La observacion del incidente es nula");
        }
        if(incidente.getCalificacion()==null)
        {
            throw new BusinessLogicException("La calificacion del incidente es nula");
        }
        if(incidente.getFechaHoraFinal()==null)
        {
            throw new BusinessLogicException("La hora final es nula");
        }
        /**
        if(!(incidente.getCoordinador().getClass().getName().equals("CoordinadorEntity")) && incidente.getCoordinador()!=null)
        {
            throw new BusinessLogicException("El coordinador no es correcto");
        }
        if(!(incidente.getEmpleado().getClass().getName().equals("EmpleadoEntity")) && incidente.getEmpleado()!=null)
        {
            throw new BusinessLogicException("El empleado es incorrecto");
        }
        if(!(incidente.getTecnico().getClass().getName().equals("TecnicoEntity")) && incidente.getTecnico()!=null)
        {
            throw new BusinessLogicException("El tecnico es incorrecto");
        }
        */
        incidente = persistence.create(incidente);
        return incidente;
    }
    
    public List<IncidenteEntity> getIncidentes() {
        List<IncidenteEntity> lista = persistence.findAll();
        return lista;
    }

    
    public IncidenteEntity getIncidente(Long incidenteId) {
        IncidenteEntity incidenteEntity = persistence.find(incidenteId);
        return incidenteEntity;
    }

    
    public IncidenteEntity updateIncidente(Long incidenteId, IncidenteEntity incidenteEntity) {
        IncidenteEntity newIncidenteEntity = persistence.update(incidenteEntity);
        return newIncidenteEntity;
    }

    
    public void deleteIncidente(Long incidenteId){
        persistence.delete(incidenteId);
        }
    public void cerrarIncidente(IncidenteEntity incidente) throws BusinessLogicException
    {
        if(incidente == null)
            throw new BusinessLogicException("El incidente no existe");
        if(incidente.getSolucionado())
            throw new BusinessLogicException("El incidente ya estaba cerrado");
        incidente.setSolucionado(Boolean.TRUE);
        updateIncidente(incidente.getId(), incidente);
    }
    
}
