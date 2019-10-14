/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.persistence.TecnicoPersistence;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Estudiante da.silvaa@uniandes.edu.co
 */
@Stateless
public class TecnicoLogic {

    private static final Logger LOGGER = Logger.getLogger(TecnicoLogic.class.getName());

    @Inject
    private TecnicoPersistence persistence;

    /**
     * Se encarga de crear un tecnico en la base de datos
     *
     * @param tecnicoEntity objeto de TecnicoEntity
     * @return Objeto de TecnicoEntity con los datos nuevos y su ID
     * @throws co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException
     */
    public TecnicoEntity createTecnico(TecnicoEntity tecnicoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del tecnico");
        if (tecnicoEntity.getEspecialidad() == null || tecnicoEntity.getEspecialidad().isEmpty()) {
            throw new BusinessLogicException("El tecnico debe tener una especialidad asignada.");
        }
        if (tecnicoEntity.getUsername() == null || tecnicoEntity.getUsername().isEmpty()) {
            throw new BusinessLogicException("El usuario no puede ser vacio");
        }
        if (persistence.findByUsername(tecnicoEntity.getUsername()) != null) {
            throw new BusinessLogicException("Ya existe un usuario con ese nombre.");
        }
        if (tecnicoEntity.getPassword() == null || tecnicoEntity.getPassword().isEmpty()) {
            throw new BusinessLogicException("La contraseña no puede ser vacia.");
        }
        TecnicoEntity newTecnicoEntity = persistence.create(tecnicoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del tecnico");
        return newTecnicoEntity;
    }

    /**
     * Obtiene la lista de los registros del tecnico
     *
     * @return Colección de Objetos de TecnicoEntity
     */
    public List<TecnicoEntity> getTecnicos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los tecnicos");
        List<TecnicoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "termina proceso de consultar todos los tecnicos ");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Tecnico a partir de su ID
     *
     * @param tecnicosID Identificador de la instancia a consultar.
     * @return Instancia de Tecnico con los datos del tecnico consultado.
     */
    public TecnicoEntity getTecnico(Long tecnicosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor con id = {0}", tecnicosId);
        TecnicoEntity tecnicoEntity = persistence.find(tecnicosId);
        if (tecnicoEntity == null) {
            LOGGER.log(Level.SEVERE, "El tecnico con el id={0} no existe", tecnicosId);
        }
        LOGGER.log(Level.INFO, "Termina el proceso de consultar el tecnico con id ={0}, tecnicosId", tecnicosId);
        return tecnicoEntity;
    }

    public TecnicoEntity updateTecnico(Long tecnicosId, TecnicoEntity tecnicoEntity) {
        LOGGER.log(Level.INFO, "inicia proceso de actualizar el autor con id = {0}");
        TecnicoEntity newTecnicoEntity = persistence.update(tecnicoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el autor con id={0}", tecnicosId);
        return newTecnicoEntity;
    }

    public void deleteTecnico(Long tecnicoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el tecnico con id = {0}", tecnicoId);
        List<IncidenteEntity> incidente = getTecnico(tecnicoId).getIncidentes();
        if (incidente != null && !incidente.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el tecnico con id = " + tecnicoId + " porque tiene incidentes asociados");
        }
        persistence.delete(tecnicoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el tecnico con id = {0}", tecnicoId);
    }
}
