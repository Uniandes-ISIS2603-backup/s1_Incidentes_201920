/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.EmpleadoPersistence;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Empleado e Incidente.
 *
 * @author Julian Jaimes
 */
@Stateless
public class EmpleadoIncidenteLogic {

    private static final Logger LOGGER = Logger.getLogger(EmpleadoIncidenteLogic.class.getName());

    @Inject
    private IncidentePersistence incidentePersistence;

    @Inject
    EmpleadoPersistence empleadoPersistence;

    /**
     * Agregar un incidente al empleado.
     *
     * @param incidenteId El id del incidente a guardar
     * @param empleadoId El id del empleado en el cual se va a guardar el
     * incidente.
     * @return El incidente creado.
     */
    public IncidenteEntity addIncidente(Long incidenteId, Long empleadoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un incidente al empleado con id = {0}", empleadoId);
        EmpleadoEntity empleadoEntity = empleadoPersistence.find(empleadoId);
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        incidenteEntity.setEmpleado(empleadoEntity);
        List<IncidenteEntity> incidentes = empleadoEntity.getIncidentes();
        incidentes.add(incidenteEntity);
        LOGGER.log(Level.INFO, "lista de incidentes = {0} ", incidentes);
        empleadoEntity.setIncidentes(incidentes);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un incidente al empleado con id = {0}", empleadoId);
        return incidenteEntity;
    }

    /**
     * Retorna todos los incidentes asociados a un empleado.
     *
     * @param empleadoId El ID del empleado buscado.
     * @return La lista de incidentes del empleado
     */
    public List<IncidenteEntity> getIncidentes(Long empleadoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los incidentes asociados al empleado con id = {0}", empleadoId);
        return empleadoPersistence.find(empleadoId).getIncidentes();
    }

    /**
     * Retorna un incidente asociado a un empleado.
     *
     * @param empleadoId El id del empleado a buscar.
     * @param incidenteId El id del incidente a buscar
     * @return El incidente encontrado dentro del empleado.
     * @throws BusinessLogicException Si el incidente no se encuentra en el
     * empleado
     */
    public IncidenteEntity getIncidente(Long empleadoId, Long incidenteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el incidente con id = {0} del empleado con id = " + empleadoId, incidenteId);
        List<IncidenteEntity> incidentes = empleadoPersistence.find(empleadoId).getIncidentes();
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        int index = incidentes.indexOf(incidenteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el incidente con id = {0} del empleado con id = " + empleadoId, incidenteId);
        if (index >= 0) {
            return incidentes.get(index);
        }
        throw new BusinessLogicException("El incidente no está asociado al empleado");
    }

    /**
     * Remplazar incidentes de un empleado.
     *
     * @param incidentes Lista de incidentes que serán los del empleado.
     * @param empleadoId El id del empleado que se quiere actualizar.
     * @return La lista de incidentes actualizada.
     */
    public List<IncidenteEntity> replaceIncidentes(Long empleadoId, List<IncidenteEntity> incidentes) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el empleado con id = {0}", empleadoId);
        EmpleadoEntity empleadoEntity = empleadoPersistence.find(empleadoId);
        List<IncidenteEntity> incidenteList = incidentePersistence.findAll();
        for (IncidenteEntity incidente : incidenteList) {
            if (incidentes.contains(incidente)) {
                incidente.setEmpleado(empleadoEntity);
            } else if (incidente.getEmpleado() != null && incidente.getEmpleado().equals(empleadoEntity)) {
                incidente.setEmpleado(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el empleado con id = {0}", empleadoId);
        return incidentes;
    }
}
