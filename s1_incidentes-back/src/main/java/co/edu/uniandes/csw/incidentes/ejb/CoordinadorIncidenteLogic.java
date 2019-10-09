/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Juan Camilo Castiblanco
 */
public class CoordinadorIncidenteLogic {

    private static final Logger LOGGER = Logger.getLogger(CoordinadorIncidenteLogic.class.getName());

    @Inject
    private IncidentePersistence incidentePersistence;

    @Inject
    CoordinadorPersistence coordinadorPersistence;

    public IncidenteEntity addIncidente(Long incidenteId, Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un incidente al coordinador con id = {0}", coordinadorId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(coordinadorId);
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        incidenteEntity.setCoordinador(coordinadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un incidente al coordinador con id = {0}", coordinadorId);
        return incidenteEntity;
    }

    public List<IncidenteEntity> getIncidentes(Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los incidentes asociados al coordinador con id = {0}", coordinadorId);
        return coordinadorPersistence.find(coordinadorId).getIncidentes();
    }

    public IncidenteEntity getIncidente(Long coordinadorId, Long incidenteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el incidente con id = {0} del coordinador con id = " + coordinadorId, incidenteId);
        List<IncidenteEntity> incidentes = coordinadorPersistence.find(coordinadorId).getIncidentes();
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        int index = incidentes.indexOf(incidenteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el incidente con id = {0} del coordinador con id = " + coordinadorId, incidenteId);
        if (index >= 0) {
            return incidentes.get(index);
        }
        throw new BusinessLogicException("El incidente no está asociado al coordinador");
    }

    public List<IncidenteEntity> replaceIncidentes(Long coordinadorId, List<IncidenteEntity> incidentes) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el coordinador con id = {0}", coordinadorId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(coordinadorId);
        List<IncidenteEntity> incidenteList = incidentePersistence.findAll();
        for (IncidenteEntity incidente : incidenteList) {
            if (incidentes.contains(incidente)) {
                incidente.setCoordinador(coordinadorEntity);
            } else if (incidente.getCoordinador() != null && incidente.getCoordinador().equals(coordinadorEntity)) {
                incidente.setCoordinador(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el coordinador con id = {0}", coordinadorId);
        return incidentes;
    }
}
