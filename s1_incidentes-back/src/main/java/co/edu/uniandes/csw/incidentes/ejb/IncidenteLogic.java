/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import co.edu.uniandes.csw.incidentes.persistence.IncidentePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author df.foreroc
 */
@Stateless
public class IncidenteLogic {

    @Inject
    private IncidentePersistence persistence;
    
    @Inject
    private CoordinadorPersistence coordinadorPersistence;

    /**
     * Crea un incidente en la base de datos
     *
     * @param incidente Objeto de IncidenteEntity con los datos nuevos
     * @return Objeto de IncidenteEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si la descripcion es null si el equipo es
     * null si la prioridad es null si la prioridad es null si la fecha de
     * inicio es null si la fecha final es null si la categoria es null si
     * solucionado es false si reabierto es false
     */
    public IncidenteEntity createIncidente(IncidenteEntity incidente) throws BusinessLogicException {
        if (incidente.getDescripcion() == null) {
            throw new BusinessLogicException("La descripcion del incidente es nula");
        }
        if (incidente.getEquipo() == null) {
            throw new BusinessLogicException("El equipo del incidente es nula");
        }
        if (incidente.getPrioridad() == null) {
            throw new BusinessLogicException("La prioridad del incidente es nula");
        }
        if (incidente.getFechaHoraInicio() == null) {
            throw new BusinessLogicException("La fecha del incidente es nula");
        }
        if (incidente.getCategoria() == null) {
            throw new BusinessLogicException("La categoria del incidente es nula");
        }


        if (incidente.getFechaHoraFinal() == null) {
            throw new BusinessLogicException("La hora final es nula");
        }
        /**
         * if(!(incidente.getCoordinador().getClass().getName().equals("CoordinadorEntity"))
         * && incidente.getCoordinador()!=null) { throw new
         * BusinessLogicException("El coordinador no es correcto"); }
         * if(!(incidente.getEmpleado().getClass().getName().equals("EmpleadoEntity"))
         * && incidente.getEmpleado()!=null) { throw new
         * BusinessLogicException("El empleado es incorrecto"); }
         * if(!(incidente.getTecnico().getClass().getName().equals("TecnicoEntity"))
         * && incidente.getTecnico()!=null) { throw new
         * BusinessLogicException("El tecnico es incorrecto"); }
         */
        incidente = persistence.create(incidente);
        return incidente;
    }

    /**
     * Obtiene la lista de los registros de Incidente.
     *
     * @return Colección de objetos de IncidenteEntity.
     */
    public List<IncidenteEntity> getIncidentes() {
        
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Incidente a partir de su ID.
     *
     * @param incidenteId Identificador de la instancia a consultar
     * @return Instancia de IncidenteEntity con los datos del Incidente
     * consultado.
     */
    public IncidenteEntity getIncidente(Long incidenteId) {
        return persistence.find(incidenteId);
        
    }

    /**
     * Actualiza la información de una instancia de Incidente.
     *
     * @param incidenteId Identificador de la instancia a actualizar
     * @param incidenteEntity Instancia de IncidenteEntity con los nuevos datos.
     * @return Instancia de IncidenteEntity con los datos actualizados.
     */
    public IncidenteEntity updateIncidente(Long incidenteId, IncidenteEntity incidenteEntity) {
        return persistence.update(incidenteEntity);
        
    }

    /**
     * Elimina una instancia de Incidente de la base de datos.
     *
     * @param incidenteId Identificador de la instancia a eliminar.
     */
    public void deleteIncidente(Long incidenteId) {
        persistence.delete(incidenteId);
    }

    /**
     * Método que cierra un incidente
     *
     * @param incidente Incidente que se quiere cerrar
     * @throws BusinessLogicException Si el Incidente no existe Si el incidente
     * ya se cerró
     */
    public void cerrarIncidente(IncidenteEntity incidente) throws BusinessLogicException {
        if (incidente == null) {
            throw new BusinessLogicException("El incidente no existe");
        }
        if (incidente.getSolucionado()) {
            throw new BusinessLogicException("El incidente ya estaba cerrado");
        }
        incidente.setSolucionado(Boolean.TRUE);
        updateIncidente(incidente.getId(), incidente);
    }

}
