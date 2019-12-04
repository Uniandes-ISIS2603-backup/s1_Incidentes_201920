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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Coordinador e Incidente.
 *
 * @author Juan Camilo Castiblanco
 */
@Stateless
public class CoordinadorIncidenteLogic {

    private static final Logger LOGGER = Logger.getLogger(CoordinadorIncidenteLogic.class.getName());

    @Inject
    private IncidentePersistence incidentePersistence;

    @Inject
    CoordinadorPersistence coordinadorPersistence;

    /**
     * Agregar un incidente al coordinador.
     *
     * @param incidenteId El id del incidente a guardar
     * @param coordinadorId El id del coordinador en el cual se va a guardar el
     * incidente.
     * @return El incidente creado.
     */
    public IncidenteEntity addIncidente(Long incidenteId, Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un incidente al coordinador con id = {0}", coordinadorId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(coordinadorId);
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        incidenteEntity.setCoordinador(coordinadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un incidente al coordinador con id = {0}", coordinadorId);
        return incidenteEntity;
    }

    /**
     * Retorna todos los incidentes asociados a un coordinador.
     *
     * @param coordinadorId El ID del coordinador buscado.
     * @return La lista de incidentes del coordinador
     */
    public List<IncidenteEntity> getIncidentes(Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los incidentes asociados al coordinador con id = {0}", coordinadorId);
        return coordinadorPersistence.find(coordinadorId).getIncidentes();
    }

    /**
     * Retorna un incidente asociado a un coordinador.
     *
     * @param coordinadorId El id del coordinador a buscar.
     * @param incidenteId El id del incidente a buscar
     * @return El incidente encontrado dentro del coordinador.
     * @throws BusinessLogicException Si el incidente no se encuentra en el
     * coordinador
     */
    public IncidenteEntity getIncidente(Long coordinadorId, Long incidenteId) throws BusinessLogicException {
        String util="Inicia proceso de consultar el incidente con id = {0} del coordinador con id = " + coordinadorId;
        String util2="Termina proceso de consultar el incidente con id = {0} del coordinador con id = " + coordinadorId;
        LOGGER.log(Level.INFO, util, incidenteId);
        List<IncidenteEntity> incidentes = coordinadorPersistence.find(coordinadorId).getIncidentes();
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        int index = incidentes.indexOf(incidenteEntity);
        LOGGER.log(Level.INFO, util2, incidenteId);
        if (index >= 0) {
            return incidentes.get(index);
        }
        throw new BusinessLogicException("El incidente no está asociado al coordinador");
    }

    /**
     * Remplazar incidentes de un coordinador.
     *
     * @param incidentes Lista de incidentes que serán los del coordinador.
     * @param coordinadorId El id del coordinador que se quiere actualizar.
     * @return La lista de incidentes actualizada.
     */
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
