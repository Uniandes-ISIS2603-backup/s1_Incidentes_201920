/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
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
        if(incidente.getTipo()==null)
        {
            throw new BusinessLogicException("El tipo del incidente es nula");
        }
        if(!incidente.getReabrir())
        {
            throw new BusinessLogicException("El incidente aun no se puede reabrir");
        }
        if(!incidente.getSolucionado())
        {
            throw new BusinessLogicException("El incidente aun no se puede solucionar");
        }
        incidente = persistence.create(incidente);
        return incidente;
    }
    
    public List<IncidenteEntity> getIncidentes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los incidentes");
        List<IncidenteEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los incidentes");
        return lista;
    }

    
    public IncidenteEntity getIncidente(Long incidenteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el incidente con id = {0}", incidenteId);
        IncidenteEntity incidenteEntity = persistence.find(incidenteId);
        if (incidenteEntity == null) {
            LOGGER.log(Level.SEVERE, "El incidente con el id = {0} no existe", incidenteId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el incidente con id = {0}", incidenteId);
        return incidenteEntity;
    }

    
    public IncidenteEntity updateIncidente(Long incidenteId, IncidenteEntity incidenteEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el incidente con id = {0}", incidenteId);
        IncidenteEntity newIncidenteEntity = persistence.update(incidenteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el incidente con id = {0}", incidenteId);
        return newIncidenteEntity;
    }

    
    public void deleteIncidente(Long incidenteId){
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el incidente con id = {0}", incidenteId);
        persistence.delete(incidenteId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", incidenteId);
    }
    
}
