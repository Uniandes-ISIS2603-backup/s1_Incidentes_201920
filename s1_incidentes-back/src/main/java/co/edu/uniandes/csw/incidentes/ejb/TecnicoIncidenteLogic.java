/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import co.edu.uniandes.csw.incidentes.persistence.TecnicoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author df.foreroc
 */
@Stateless
public class TecnicoIncidenteLogic {
    private static final Logger LOGGER = Logger.getLogger(TecnicoIncidenteLogic.class.getName());

    @Inject
    private IncidentePersistence incidentePersistence;

    @Inject
    TecnicoPersistence tecnicoPersistence;

    /**
     * Agregar un incidente al coordinador.
     *
     * @param incidenteId El id del incidente a guardar
     * @param coordinadorId El id del coordinador en el cual se va a guardar el
     * incidente.
     * @return El incidente creado.
     */
    public IncidenteEntity addIncidente(Long incidenteId, Long tecnicoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un incidente al tecnico con id = {0}", tecnicoId);
        TecnicoEntity tecnicoEntity = tecnicoPersistence.find(tecnicoId);
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        incidenteEntity.setTecnico(tecnicoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un incidente al tecnico con id = {0}", tecnicoId);
        return incidenteEntity;
    }

    /**
     * Retorna todos los incidentes asociados a un coordinador.
     *
     * @param coordinadorId El ID del coordinador buscado.
     * @return La lista de incidentes del coordinador
     */
    public List<IncidenteEntity> getIncidentes(Long tecnicoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los incidentes asociados al tecnico con id = {0}", tecnicoId);
        return tecnicoPersistence.find(tecnicoId).getIncidentes();
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
    public IncidenteEntity getIncidente(Long tecnicoId, Long incidenteId) throws BusinessLogicException {
        String util="Inicia proceso de consultar el incidente con id = {0} del tecnico con id = " + tecnicoId;
        String util2="Termina proceso de consultar el incidente con id = {0} del tecnico con id = " + tecnicoId;
        LOGGER.log(Level.INFO,util , incidenteId);
        List<IncidenteEntity> incidentes = tecnicoPersistence.find(tecnicoId).getIncidentes();
        IncidenteEntity incidenteEntity = incidentePersistence.find(incidenteId);
        int index = incidentes.indexOf(incidenteEntity);
        LOGGER.log(Level.INFO,util2 , incidenteId);
        if (index >= 0) {
            return incidentes.get(index);
        }
        throw new BusinessLogicException("El incidente no está asociado al tecnico");
    }

    /**
     * Remplazar incidentes de un coordinador.
     *
     * @param incidentes Lista de incidentes que serán los del coordinador.
     * @param coordinadorId El id del coordinador que se quiere actualizar.
     * @return La lista de incidentes actualizada.
     */
    public List<IncidenteEntity> replaceIncidentes(Long tecnicoId, List<IncidenteEntity> incidentes) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el tecnico con id = {0}", tecnicoId);
        TecnicoEntity tecnicoEntity = tecnicoPersistence.find(tecnicoId);
        List<IncidenteEntity> incidenteList = incidentePersistence.findAll();
        for (IncidenteEntity incidente : incidenteList) {
            if (incidentes.contains(incidente)) {
                incidente.setTecnico(tecnicoEntity);
            } else if (incidente.getTecnico() != null && incidente.getTecnico().equals(tecnicoEntity)) {
                incidente.setTecnico(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el tecnico con id = {0}", tecnicoId);
        return incidentes;
    }
    
}
