/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.ActuacionPersistence;
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
    
    public ActuacionEntity createActuacion(ActuacionEntity actuacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la actuacion");
        if (actuacion.getDescripcion() == null) {
            throw new BusinessLogicException("La descripción de la actuación está vacía");
        }
        actuacion = persistence.create(actuacion);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la actuación");
        return actuacion;
    }
    
    public List<ActuacionEntity> getActuaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las actuaciones");
        List<ActuacionEntity> books = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las actuaciones");
        return books;
    }
    
    public ActuacionEntity getActuacion(Long actuacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0}", actuacionId);
        ActuacionEntity actuacion = persistence.find(actuacionId);
        if (actuacion == null) {
            LOGGER.log(Level.SEVERE, "La actuación con el id = {0} no existe", actuacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0}", actuacionId);
        return actuacion;
    }
    
    public ActuacionEntity updateActuacion(Long actuacionId, ActuacionEntity actuacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la actuación con id = {0}", actuacionId);
        if (!validarDescripcion(actuacionEntity.getDescripcion())) {
            throw new BusinessLogicException("La descripción es inválida");
        }
        ActuacionEntity nuevaActuacion = persistence.update(actuacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la actuacion con id = {0}", actuacionEntity.getId());
        return nuevaActuacion;
    }
    
    public void deleteActuacion(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar actuacion con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar premio con id = {0}", id);
    }
    
    private boolean validarDescripcion(String descripcion) {
        return !(descripcion == null || descripcion.isEmpty());
    }
}
