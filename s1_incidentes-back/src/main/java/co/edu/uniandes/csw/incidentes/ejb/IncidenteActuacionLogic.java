/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.ActuacionPersistence;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author df.foreroc
 */
public class IncidenteActuacionLogic {
    private static final Logger LOGGER = Logger.getLogger(IncidenteActuacionLogic.class.getName());

    @Inject
    private ActuacionPersistence actuacionPersistence;

    @Inject
    IncidentePersistence incidentePersistence;
    public ActuacionEntity addActuacion(Long actuacionId, Long incidenteId) {

        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        ActuacionEntity actuacionEntity = actuacionPersistence.find(actuacionId);
        actuacionEntity.setIncidente(incidenteEntity);
        List<ActuacionEntity> actuaciones = incidenteEntity.getActuaciones();
        actuaciones.add(actuacionEntity);

        incidenteEntity.setActuaciones(actuaciones);
        return actuacionEntity;
    }

    public List<ActuacionEntity> getActuaciones(Long incidenteId) {
        return incidentePersistence.find(incidenteId).getActuaciones();
    }

    public ActuacionEntity getActuacion(Long incidenteId, Long actuacionId) throws BusinessLogicException {
        List<ActuacionEntity> actuaciones = incidentePersistence.find(incidenteId).getActuaciones();
        ActuacionEntity actuacionEntity = actuacionPersistence.find(actuacionId);
        int index = actuaciones.indexOf(actuacionEntity);
        if (index >= 0) {
            return actuaciones.get(index);
        }
        throw new BusinessLogicException("La actuación no está asociada al Incidente");
    }

    public List<ActuacionEntity> replaceActuaciones(Long incidenteId, List<ActuacionEntity>actuaciones) {
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        List<ActuacionEntity> actuacionList = actuacionPersistence.findAll();
        for (ActuacionEntity actuacion : actuacionList) {
            if (actuaciones.contains(actuacion)) {
                actuacion.setIncidente(incidenteEntity);
            } else if (actuacion.getIncidente() != null && actuacion.getIncidente().equals(incidenteEntity)) {
                actuacion.setIncidente(null);
            }
        }
        return actuaciones;
    }
    
}
