/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.ejb.TecnicoCoordinadorLogic;
import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Camilo Castiblanco
 */
@Stateless
public class IncidenteCoordinadorLogic {
 
    private static final Logger LOGGER = Logger.getLogger(TecnicoCoordinadorLogic.class.getName());

    @Inject
    private IncidentePersistence incidentePersistence;

    @Inject
    private CoordinadorPersistence coordinadorPersistence;

    /**
     * Remplazar el coordinador de un incidente.
     *
     * @param incidenteId id del incidente que se quiere actualizar.
     * @param coordinadorId El id del coordinador que se será del incidente.
     * @return el nuevo incidente.
     */
    public IncidenteEntity replaceCoordinador(Long incidenteId, Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar incidente con id = {0}", incidenteId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(coordinadorId);
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        incidenteEntity.setCoordinador(coordinadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar incidente con id = {0}", incidenteEntity.getId());
        return incidenteEntity;
    }

    /**
     * Borrar un incidente de un coordinador. Este metodo se utiliza para borrar la
     * relacion de un incidente.
     *
     * @param incidenteId El incidente que se desea borrar del coordinador.
     */
    public void removeCoordinador(Long incidenteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el coordinador del incidente con id = {0}", incidenteId);
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(incidenteEntity.getCoordinador().getId());
        incidenteEntity.setCoordinador(null);
        coordinadorEntity.getIncidentes().remove(incidenteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el coordinador del incidente con id = {0}", incidenteEntity.getId());
    }
}
