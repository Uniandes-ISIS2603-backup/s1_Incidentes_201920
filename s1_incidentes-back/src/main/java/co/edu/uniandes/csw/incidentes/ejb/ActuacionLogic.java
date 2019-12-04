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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Catalina Alcalá
 */
@Stateless
public class ActuacionLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ActuacionLogic.class.getName());
    
    @Inject
    private ActuacionPersistence persistence;
    
    @Inject
    private IncidentePersistence incidentePersistence;
    
    public ActuacionEntity createActuacion(Long idIncidente, ActuacionEntity actuacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la actuacion");
        IncidenteEntity incidente = incidentePersistence.find(idIncidente);
        if (!validarDescripcion(actuacion.getDescripcion())) {
            throw new BusinessLogicException("La descripción de la actuación está vacía");
        }
        actuacion.setIncidente(incidente);
        actuacion = persistence.create(actuacion);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la actuación");
        return actuacion;
    }
    
    public List<ActuacionEntity> getActuaciones(Long idIncidente) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las actuaciones");
        IncidenteEntity incidente = incidentePersistence.find(idIncidente);
        if (incidente == null) {
            LOGGER.log(Level.SEVERE, "El incidente con el id = {0} no existe", idIncidente);
            List<ActuacionEntity> respuesta=null;
            return respuesta;
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las actuaciones");
        return incidente.getActuaciones();
    }
    
    public ActuacionEntity getActuacion(Long idIncidente, Long actuacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0}", actuacionId);
        ActuacionEntity actuacion = persistence.find(idIncidente, actuacionId);
        if (actuacion == null) {
            LOGGER.log(Level.SEVERE, "La actuación con el id = {0} no existe", actuacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0}", actuacionId);
        return actuacion;
    }
    
    public ActuacionEntity updateActuacion(Long idIncidente, ActuacionEntity actuacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la actuación con id = {0} del incidente con id = " + idIncidente, actuacionEntity);
        if (!validarDescripcion(actuacionEntity.getDescripcion())) {
            throw new BusinessLogicException("La descripción es inválida");
        }
        IncidenteEntity incidente = incidentePersistence.find(idIncidente);
        actuacionEntity.setIncidente(incidente);
        ActuacionEntity nuevaActuacion = persistence.update(actuacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la actuacion con id = {0}", actuacionEntity.getId());
        return nuevaActuacion;
    }
    
    public void deleteActuacion(Long idIncidente, Long idActuacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar actuacion con id = {0} del libro con id = " + idIncidente, idActuacion);
        ActuacionEntity old = getActuacion(idIncidente, idActuacion);
        if (old == null) {
            throw new BusinessLogicException("La actuacion con id = " + idActuacion + " no está asociado al incidente con id = " + idIncidente);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de la actuación con id = {0} del libro con id = " + idIncidente, idActuacion);
    }
    
    private boolean validarDescripcion(String descripcion) {
        return !(descripcion == null || descripcion.isEmpty());
    }
}
