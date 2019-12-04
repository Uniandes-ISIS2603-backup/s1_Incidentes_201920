/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.IncidenteDTO;
import co.edu.uniandes.csw.incidentes.dtos.IncidenteDetailDTO;
import co.edu.uniandes.csw.incidentes.ejb.IncidenteLogic;
import co.edu.uniandes.csw.incidentes.ejb.TecnicoIncidenteLogic;
import co.edu.uniandes.csw.incidentes.ejb.TecnicoLogic;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author da.silvaa
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TecnicoIncidenteResource {
    
    private static final Logger LOGGER = Logger.getLogger(TecnicoIncidenteResource.class.getName());
    private static final String NOEXISTE="no existe";

    @Inject
    private IncidenteLogic incidenteLogic;

    @Inject
    private TecnicoIncidenteLogic tecnicoIncidenteLogic;

    /**
     * Guarda un incidente dentro de un coordinador con la informacion que recibe el
     * la URL. Se devuelve el incidente que se guarda en el coordinador.
     *
     * @param coordinadorId Identificador del coordinador que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param incidenteId Identificador del incidente que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El incidente guardado en el coordinador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el incidente.
     */
    @POST
    @Path("{incidenteId: \\d+}")
    public IncidenteDTO addIncidente(@PathParam("tecnicoId") Long tecnicoId, @PathParam("incidenteId") Long incidenteId) {
        LOGGER.log(Level.INFO, "tecnicoIncidenteResource addIncidente: input: tecnicoId: {0} , incidenteId: {1}", new Object[]{tecnicoId, incidenteId});
        if (incidenteLogic.getIncidente(incidenteId) == null) {
            throw new WebApplicationException("El recurso /incidente/" + incidenteId + NOEXISTE, 404);
        }
        
        IncidenteDTO incidenteDTO = new IncidenteDTO(tecnicoIncidenteLogic.addIncidente(incidenteId, tecnicoId));
        LOGGER.log(Level.INFO, "TecnicoIncidenteResource addIncidente: output: {0}", incidenteDTO);
        return incidenteDTO;
    }

    /**
     * Busca y devuelve todos los incidentes que existen en el coordinador.
     *
     * @param coordinadorId Identificador del coordinador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link IncidenteDetailDTO} - Los incidentes encontrados en el
     * coordinador. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<IncidenteDetailDTO> getIncidentes(@PathParam("tecnicoId") Long tecnicoId) {
        LOGGER.log(Level.INFO, "TecnicoIncidenteResource getIncidentes: input: {0}", tecnicoId);
        List<IncidenteDetailDTO> listaDetailDTOs = incidentesListEntity2DTO(tecnicoIncidenteLogic.getIncidentes(tecnicoId));
        LOGGER.log(Level.INFO, "TecnicoIncidenteResource getIncidentes: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el incidente con el id asociado dentro del coordinador con id asociado.
     *
     * @param coordinadorId Identificador del coordinador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param incidenteId Identificador del incidente que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link IncidenteDetailDTO} - El incidente buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el incidente.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el incidente en el 
     * coordinador.
     */
    @GET
    @Path("{incidenteId: \\d+}")
    public IncidenteDetailDTO getIncidente(@PathParam("tecnicoId") Long tecnicoId, @PathParam("incidenteId") Long incidenteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnicoIncidenteResource getIncidente: input: editorialsID: {0} , booksId: {1}", new Object[]{tecnicoId, incidenteId});
        if (incidenteLogic.getIncidente(incidenteId) == null) {
            throw new WebApplicationException("El recurso /tecnico/" + tecnicoId + "/incidente/" + incidenteId + NOEXISTE, 404);
        }
        IncidenteDetailDTO incidenteDetailDTO = new IncidenteDetailDTO(tecnicoIncidenteLogic.getIncidente(tecnicoId, incidenteId));
        LOGGER.log(Level.INFO, "TecnicoIncidenteResource getIncidente: output: {0}", incidenteDetailDTO);
        return incidenteDetailDTO;
    }

    /**
     * Remplaza las instancias de Incidente asociadas a una instancia de Coordinador
     *
     * @param coordinadorId Identificador del coordinador que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param incidentes JSONArray {@link IncidenteDetailDTO} El arreglo de incidentes nuevo para el
     * coordinador.
     * @return JSON {@link IncidenteDetailDTO} - El arreglo de incidentes guardado en el
     * coordinador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el incidente.
     */
    @PUT
    public List<IncidenteDetailDTO> replaceIncidentes(@PathParam("tecnicoId") Long tecnicoId, List<IncidenteDetailDTO> incidentes) {
        LOGGER.log(Level.INFO, "tecnicoIncidenteResource replaceIncidentes: input: tecnicoId: {0} , incidentes: {1}", new Object[]{tecnicoId, incidentes});
        for (IncidenteDetailDTO incidente : incidentes) {
            if (incidenteLogic.getIncidente(incidente.getId()) == null) {
                throw new WebApplicationException("El recurso /incidente/" + incidente.getId() + NOEXISTE, 404);
            }
        }
        List<IncidenteDetailDTO> listaDetailDTOs = incidentesListEntity2DTO(tecnicoIncidenteLogic.replaceIncidentes(tecnicoId, incidentesListDTO2Entity(incidentes)));
        LOGGER.log(Level.INFO, "TecnicoIncidenteResource replaceIncidentes: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de IncidenteEntity a una lista de IncidenteDetailDTO.
     *
     * @param incidentes Lista de IncidenteEntity a convertir.
     * @return Lista de IncidenteDTO convertida.
     */
    private List<IncidenteDetailDTO> incidentesListEntity2DTO(List<IncidenteEntity> incidentes) {
        List<IncidenteDetailDTO> list = new ArrayList();
        for (IncidenteEntity entity : incidentes) {
            list.add(new IncidenteDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de IncidenteDetailDTO a una lista de IncidenteEntity.
     *
     * @param dtos Lista de IncidenteDetailDTO a convertir.
     * @return Lista de IncidenteEntity convertida.
     */
    private List<IncidenteEntity> incidentesListDTO2Entity(List<IncidenteDetailDTO> dtos) {
        List<IncidenteEntity> list = new ArrayList<>();
        for (IncidenteDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
}
