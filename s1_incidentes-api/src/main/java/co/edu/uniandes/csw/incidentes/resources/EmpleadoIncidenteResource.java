/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.IncidenteDTO;
import co.edu.uniandes.csw.incidentes.dtos.IncidenteDetailDTO;
import co.edu.uniandes.csw.incidentes.ejb.CoordinadorIncidenteLogic;
import co.edu.uniandes.csw.incidentes.ejb.IncidenteLogic;
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
 * @author Julian Jaimes
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmpleadoIncidenteResource {
    
    private static final Logger LOGGER = Logger.getLogger(CoordinadorIncidenteResource.class.getName());

    @Inject
    private IncidenteLogic incidenteLogic;

    @Inject
    private CoordinadorIncidenteLogic empleadoIncidenteLogic;

    @POST
    @Path("{incidenteId: \\d+}")
    public IncidenteDTO addIncidente(@PathParam("empleadoId") Long empleadoId, @PathParam("incidenteId") Long incidenteId) {
        LOGGER.log(Level.INFO, "CoordinadorIncidenteResource addIncidente: input: empleadoId: {0} , incidenteId: {1}", new Object[]{empleadoId, incidenteId});
        if (incidenteLogic.getIncidente(incidenteId) == null) {
            throw new WebApplicationException("El recurso /incidente/" + incidenteId + " no existe.", 404);
        }
        
        IncidenteDTO incidenteDTO = new IncidenteDTO(empleadoIncidenteLogic.addIncidente(incidenteId, empleadoId));
        LOGGER.log(Level.INFO, "CoordinadorIncidenteResource addIncidente: output: {0}", incidenteDTO);
        return incidenteDTO;
    }

    @GET
    public List<IncidenteDetailDTO> getIncidentes(@PathParam("empleadoId") Long empleadoId) {
        LOGGER.log(Level.INFO, "CoordinadorIncidenteResource getIncidentes: input: {0}", empleadoId);
        List<IncidenteDetailDTO> listaDetailDTOs = incidentesListEntity2DTO(empleadoIncidenteLogic.getIncidentes(empleadoId));
        LOGGER.log(Level.INFO, "CoordinadorIncidenteResource getIncidentes: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    @GET
    @Path("{incidenteId: \\d+}")
    public IncidenteDetailDTO getIncidente(@PathParam("empleadoId") Long empleadoId, @PathParam("incidenteId") Long incidenteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CoordinadorIncidenteResource getIncidente: input: editorialsID: {0} , booksId: {1}", new Object[]{empleadoId, incidenteId});
        if (incidenteLogic.getIncidente(incidenteId) == null) {
            throw new WebApplicationException("El recurso /empleado/" + empleadoId + "/incidente/" + incidenteId + " no existe.", 404);
        }
        IncidenteDetailDTO incidenteDetailDTO = new IncidenteDetailDTO(empleadoIncidenteLogic.getIncidente(empleadoId, incidenteId));
        LOGGER.log(Level.INFO, "CoordinadorIncidenteResource getIncidente: output: {0}", incidenteDetailDTO);
        return incidenteDetailDTO;
    }

    @PUT
    public List<IncidenteDetailDTO> replaceIncidentes(@PathParam("empleadoId") Long empleadoId, List<IncidenteDetailDTO> incidentes) {
        LOGGER.log(Level.INFO, "CoordinadorIncidenteResource replaceIncidentes: input: empleadoId: {0} , incidentes: {1}", new Object[]{empleadoId, incidentes});
        for (IncidenteDetailDTO incidente : incidentes) {
            if (incidenteLogic.getIncidente(incidente.getId()) == null) {
                throw new WebApplicationException("El recurso /incidente/" + incidente.getId() + " no existe.", 404);
            }
        }
        List<IncidenteDetailDTO> listaDetailDTOs = incidentesListEntity2DTO(empleadoIncidenteLogic.replaceIncidentes(empleadoId, incidentesListDTO2Entity(incidentes)));
        LOGGER.log(Level.INFO, "EmpleadoIncidenteResource replaceIncidentes: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    private List<IncidenteDetailDTO> incidentesListEntity2DTO(List<IncidenteEntity> incidentes) {
        List<IncidenteDetailDTO> list = new ArrayList();
        for (IncidenteEntity entity : incidentes) {
            list.add(new IncidenteDetailDTO(entity));
        }
        return list;
    }

    private List<IncidenteEntity> incidentesListDTO2Entity(List<IncidenteDetailDTO> dtos) {
        List<IncidenteEntity> list = new ArrayList<>();
        for (IncidenteDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
