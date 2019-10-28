/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import co.edu.uniandes.csw.incidentes.persistence.TecnicoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author c.alcala
 */
@Stateless
public class TecnicoCoordinadorLogic {

    private static final Logger LOGGER = Logger.getLogger(TecnicoCoordinadorLogic.class.getName());

    @Inject
    private TecnicoPersistence tecnicoPersistence;

    @Inject
    private CoordinadorPersistence coordinadorPersistence;

    /**
     * Remplazar la editorial de un book.
     *
     * @param tecnicoId id del libro que se quiere actualizar.
     * @param coordinadorId El id de la editorial que se será del libro.
     * @return el nuevo libro.
     */
    public TecnicoEntity replaceCoordinador(Long tecnicoId, Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id = {0}", tecnicoId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(coordinadorId);
        TecnicoEntity tecnicoEntity = tecnicoPersistence.find(tecnicoId);
        tecnicoEntity.setCoordinador(coordinadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar libro con id = {0}", tecnicoEntity.getId());
        return tecnicoEntity;
    }

    /**
     * Borrar un book de una editorial. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param tecnicoId El libro que se desea borrar de la editorial.
     */
    public void removeCoordinador(Long tecnicoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Editorial del libro con id = {0}", tecnicoId);
        TecnicoEntity tecnicoEntity = tecnicoPersistence.find(tecnicoId);
        CoordinadorEntity editorialEntity = coordinadorPersistence.find(tecnicoEntity.getCoordinador().getId());
        tecnicoEntity.setCoordinador(null);
        editorialEntity.getTecnicos().remove(tecnicoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Editorial del libro con id = {0}", tecnicoEntity.getId());
    }
}
