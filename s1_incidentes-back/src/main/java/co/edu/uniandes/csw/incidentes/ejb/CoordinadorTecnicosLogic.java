/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import co.edu.uniandes.csw.incidentes.persistence.TecnicoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author c.alcala
 */
@Stateless
public class CoordinadorTecnicosLogic {

    private static final Logger LOGGER = Logger.getLogger(CoordinadorTecnicosLogic.class.getName());

    @Inject
    private TecnicoPersistence tecnicoPersistence;

    @Inject
    private CoordinadorPersistence coordinadorPersistence;

    /**
     * Agregar un book a la editorial
     *
     * @param tecnicoId El id libro a guardar
     * @param coordinadorId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return El tecnico creado.
     */
    public TecnicoEntity addTecnico(Long tecnicoId, Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la editorial con id = {0}", coordinadorId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(coordinadorId);
        TecnicoEntity tecnicoEntity = tecnicoPersistence.find(tecnicoId);
        tecnicoEntity.setCoordinador(coordinadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la editorial con id = {0}", coordinadorId);
        return tecnicoEntity;
    }

    /**
     * Retorna todos los books asociados a una editorial
     *
     * @param coordinadorId El ID de la editorial buscada
     * @return La lista de libros de la editorial
     */
    public List<TecnicoEntity> getTecnicos(Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la editorial con id = {0}", coordinadorId);
        return coordinadorPersistence.find(coordinadorId).getTecnicos();
    }

    /**
     * Retorna un book asociado a una editorial
     *
     * @param coordinadorId El id de la editorial a buscar.
     * @param tecnicoId El id del libro a buscar
     * @return El libro encontrado dentro de la editorial.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * editorial
     */
    public TecnicoEntity getTecnico(Long coordinadorId, Long tecnicoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la editorial con id = " + coordinadorId, tecnicoId);
        List<TecnicoEntity> tecnicos = coordinadorPersistence.find(coordinadorId).getTecnicos();
        TecnicoEntity tecnicoEntity = tecnicoPersistence.find(tecnicoId);
        int index = tecnicos.indexOf(tecnicoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la editorial con id = " + coordinadorId, tecnicoId);
        if (index >= 0) {
            return tecnicos.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la editorial");
    }

    /**
     * Remplazar books de una editorial
     *
     * @param tecnicos Lista de libros que serán los de la editorial.
     * @param coordinadorId El id de la editorial que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<TecnicoEntity> replaceTecnicos(Long coordinadorId, List<TecnicoEntity> tecnicos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la editorial con id = {0}", coordinadorId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(coordinadorId);
        List<TecnicoEntity> listaTecnicos = tecnicoPersistence.findAll();
        for (TecnicoEntity tecnico : listaTecnicos) {
            if (tecnicos.contains(tecnico)) {
                tecnico.setCoordinador(coordinadorEntity);
            } else if (tecnico.getCoordinador() != null && tecnico.getCoordinador().equals(coordinadorEntity)) {
                tecnico.setCoordinador(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", coordinadorId);
        return tecnicos;
    }
}
