/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad
 * Coordinador.
 *
 * @author Juan Camilo Castiblanco
 */
@Stateless
public class CoordinadorLogic {

    private static final Logger LOGGER = Logger.getLogger(CoordinadorLogic.class.getName());

    @Inject
    private CoordinadorPersistence persistence;

    /**
     * Crea un coordinador en la persistencia.
     *
     * @param coordinador La entidad que representa el coordinador a persistir.
     * @return La entidad del coordinador luego de persistirla.
     * @throws BusinessLogicException Si se incumple una regla de negocio.
     */
    public CoordinadorEntity createCoordinador(CoordinadorEntity coordinador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del coordinador");
        if (coordinador.getName() == null) {
            throw new BusinessLogicException("El nombre es nulo.");
        }
        if (coordinador.getUsername() == null || coordinador.getUsername().isEmpty()) {
            throw new BusinessLogicException("El usuario no puede ser vacio");
        }
        if (persistence.findByUsername(coordinador.getUsername()) != null) {
            throw new BusinessLogicException("Ya existe un usuario con ese nombre.");
        }

        if (coordinador.getPassword() == null || coordinador.getPassword().isEmpty()) {
            throw new BusinessLogicException("La contraseña no puede ser vacia.");
        }

        persistence.create(coordinador);
        LOGGER.log(Level.INFO, "Termina proceso de creación del coordinador");
        return coordinador;
    }

    /**
     * Obtener todos los Coordinadores existentes en la base de datos.
     *
     * @return una lista de Coordinadores.
     */
    public List<CoordinadorEntity> getCoordinadores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los coordinadores");
        List<CoordinadorEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los coordinadores");
        return lista;
    }

    /**
     * Obtener un coordinador por medio de su id.
     *
     * @param coordinadorId: id del coordinador para ser buscado.
     * @return el coordinador solicitado por medio de su id.
     */
    public CoordinadorEntity getCoordinador(Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el coordinador con id = {0}", coordinadorId);
        CoordinadorEntity coordinadorEntity = persistence.find(coordinadorId);
        if (coordinadorEntity == null) {
            LOGGER.log(Level.SEVERE, "El coordinador con el id = {0} no existe", coordinadorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el coordinador con id = {0}", coordinadorId);
        return coordinadorEntity;
    }

    /**
     * Actualizar un coordinador.
     *
     * @param coordinadorId: id del coordinador para buscarlo en la base de
     * datos.
     * @param coordinador: coordinador con los cambios para ser actualizado, por
     * ejemplo el nombre.
     * @return el coordinador con los cambios actualizados en la base de datos.
     */
    public CoordinadorEntity updateCoordinador(Long coordinadorId, CoordinadorEntity coordinador) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el coordinador con id = {0}", coordinadorId);
        CoordinadorEntity newCoordinadorEntity = persistence.update(coordinador);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el coordinador con id = {0}", coordinador.getId());
        return newCoordinadorEntity;
    }

    /**
     * Borrar un coordinador
     *
     * @param coordinadorId: id del coordinador a borrar
     * @throws BusinessLogicException Si el coordinador a eliminar tiene
     * incidentes.
     */
    public void deleteCoordinador(Long coordinadorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el coordinador con id = {0}", coordinadorId);
        List<IncidenteEntity> incidente = getCoordinador(coordinadorId).getIncidentes();
        if (incidente != null && !incidente.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el coordinador con id = " + coordinadorId + " porque tiene incidentes asociados");
        }
        List<TecnicoEntity> tecnicos = getCoordinador(coordinadorId).getTecnicos();
        if (tecnicos != null && !tecnicos.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el coordinador con id = " + coordinadorId + " porque tiene tecnicos asociados");
        }
        persistence.delete(coordinadorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el coordinador con id = {0}", coordinadorId);
    }
}
