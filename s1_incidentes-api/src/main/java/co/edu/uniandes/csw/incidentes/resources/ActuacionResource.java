/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.ActuacionDTO;
import co.edu.uniandes.csw.incidentes.ejb.ActuacionLogic;
import co.edu.uniandes.csw.incidentes.ejb.IncidenteLogic;
import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author c.alcala
 */

@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ActuacionResource {
    
    private static final Logger LOGGER = Logger.getLogger(ActuacionResource.class.getName());
    private static final String NOEXISTE="no existe";
    private static final String ACTU="/actuacion/";
    @Inject
    private ActuacionLogic actuacionLogic;
    
    @Inject
    private IncidenteLogic incidenteLogic;
    
    @POST
    public ActuacionDTO createActuacion(@PathParam("incidentesId") Long idIncidente, ActuacionDTO actuacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActuacionResource createActuacion: input: {0}", actuacion);
        ActuacionDTO newActuacionDTO = new ActuacionDTO(actuacionLogic.createActuacion(idIncidente, actuacion.toEntity()));
        LOGGER.log(Level.INFO, "ActuacionResource createActuacion: output:{0}", newActuacionDTO);
        return newActuacionDTO;
    }
    
    @GET
    public List<ActuacionDTO> getActuaciones(@PathParam("incidentesId") Long incidenteId) {
        LOGGER.log(Level.INFO, "ActuacionResource getActuaciones: input: {0}", incidenteId);
        List<ActuacionDTO> actuaciones = listEntity2DTO(actuacionLogic.getActuaciones(incidenteId));
        LOGGER.log(Level.INFO, "ActuaciResource getActuaciones: output:{0}", actuaciones);
        return actuaciones;
    }
    
    @GET
    @Path("{idActuacion: \\d+}")
    public ActuacionDTO getActuacion(@PathParam("incidentesId") Long idIncidente, @PathParam("idActuacion") Long idActuacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActuacionResource getAcuacion: input: {0}", idActuacion);
        ActuacionEntity entity = actuacionLogic.getActuacion(idIncidente, idActuacion);
        if (entity == null) {
            throw new WebApplicationException("El recurso /incidente/" + idIncidente + ACTU+ idActuacion + NOEXISTE, 404);
        }
        ActuacionDTO actuacionDTO = new ActuacionDTO(entity);
        LOGGER.log(Level.INFO, "ActuacionResource getActuacion: output: {0}", actuacionDTO);
        return actuacionDTO;
    }
    
    @PUT
    @Path("{idActuacion: \\d+}")
    public ActuacionDTO updateActuacion(@PathParam("incidentesId") Long idIncidente, @PathParam("idActuacion") Long idActuacion, ActuacionDTO actuacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActuacionResource updateActuacion: input: idIncidente: {0}, idActuacion: {1}, review:{2}", new Object[]{idIncidente, idActuacion, actuacion});
        actuacion.setId(idActuacion);
        ActuacionEntity entity = actuacionLogic.getActuacion(idIncidente, idActuacion);
        if (entity == null) {
            throw new WebApplicationException("El recurso /incidentes/" + idIncidente + ACTU + idActuacion + NOEXISTE, 404);
        }
        ActuacionDTO actuacionDTO = new ActuacionDTO(actuacionLogic.updateActuacion(idIncidente, actuacion.toEntity()));
        LOGGER.log(Level.INFO, "ActuacionResource updateActuacion: output:{0}", actuacionDTO);
        return actuacionDTO;
    }
    
    @DELETE
    @Path("{idActuacion: \\d+}")
    public void deleteActuacion(@PathParam("incidentesId") Long idIncidente, @PathParam("idActuacion") Long idActuacion) throws BusinessLogicException {
        ActuacionEntity entity = actuacionLogic.getActuacion(idIncidente, idActuacion);
        if (entity == null) {
            throw new WebApplicationException("El recurso /incidentes/" + idIncidente + ACTU + idActuacion + NOEXISTE, 404);
        }
        actuacionLogic.deleteActuacion(idIncidente, idActuacion);
    }
    
    private List<ActuacionDTO> listEntity2DTO(List<ActuacionEntity> actuaciones) {
        List<ActuacionDTO> lista = new ArrayList<>();
        for (ActuacionEntity actuacion : actuaciones) {
            lista.add(new ActuacionDTO(actuacion));
        }
        return lista;
    }
}
