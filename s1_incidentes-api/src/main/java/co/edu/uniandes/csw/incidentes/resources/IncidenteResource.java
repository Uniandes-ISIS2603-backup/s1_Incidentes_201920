/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.IncidenteDTO;
import co.edu.uniandes.csw.incidentes.dtos.IncidenteDetailDTO;

import co.edu.uniandes.csw.incidentes.ejb.IncidenteLogic;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
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
 * @author df.foreroc
 */
@Path("incidentes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
        
public class IncidenteResource {
    private static final Logger LOGGER = Logger.getLogger(IncidenteResource.class.getName());
    private static final String NOEXISTE = "no existe.";
        private static final String INCI =  "El recurso /incidentes/";
     @Inject
    private IncidenteLogic incidenteLogic;
     
    @POST
    public IncidenteDTO createIncidente(IncidenteDTO incidente) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", incidente);
        
        IncidenteEntity incidenteEntity = incidente.toEntity();
        // Invoca la lógica para crear la editorial nueva
        IncidenteEntity nuevoInciEntity = incidenteLogic.createIncidente(incidenteEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        IncidenteDTO nuevoIncidenteDTO = new IncidenteDTO(nuevoInciEntity);
        LOGGER.log(Level.INFO, "IncidenteResource createIncidente: output: {0}", nuevoIncidenteDTO);
        return nuevoIncidenteDTO;
    }
    
    @GET
    @Path("{incidentesId: \\d+}")
    public IncidenteDetailDTO getIncidente(@PathParam("incidentesId") Long incidenteId){
        IncidenteEntity entidad=incidenteLogic.getIncidente(incidenteId);
        if(entidad==null)
        {
            throw new WebApplicationException(INCI+incidenteId+NOEXISTE,404);
        }
        return new IncidenteDetailDTO(entidad);        
    }
     @GET
    public List<IncidenteDetailDTO> getIncidentes() {
       
        List<IncidenteDetailDTO> listaIncidentes;
        listaIncidentes = listEntity2DetailDTO(incidenteLogic.getIncidentes());
        
        return listaIncidentes;
    }
     private List<IncidenteDetailDTO> listEntity2DetailDTO(List<IncidenteEntity> entityList) {
        List<IncidenteDetailDTO> list = new ArrayList<>();
        for (IncidenteEntity entity : entityList) {
            list.add(new IncidenteDetailDTO(entity));
        }
        return list;
    }
    @PUT
    @Path("{incidentesId: \\d+}")
    public IncidenteDetailDTO updateIncidente(@PathParam("incidentesId") Long incidentesId, IncidenteDetailDTO incidente) throws BusinessLogicException {
        incidente.setId(incidentesId);
        if (incidenteLogic.getIncidente(incidentesId) == null) {
            throw new WebApplicationException(INCI + incidentesId + NOEXISTE, 404);
        }
        return new IncidenteDetailDTO(incidenteLogic.updateIncidente(incidentesId, incidente.toEntity()));
        

    }
    @DELETE
    @Path("{incidentesId: \\d+}")
     public void deleteIncidente(@PathParam("incidentesId") Long incidenteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CoordinadorResource deleteCoordinador: input: {0}", incidenteId);
        IncidenteEntity entity = incidenteLogic.getIncidente(incidenteId);
        if (entity == null) {
            throw new WebApplicationException(INCI + incidenteId + NOEXISTE, 404);
        }
        else{
        incidenteLogic.deleteIncidente(incidenteId);
        LOGGER.info("IncidenteResource deleteIncidente: output: void");}
    }
    @Path("{incidentesId: \\d+}/actuacion")
    public Class<ActuacionResource> getActuacionResource(@PathParam("incidentesId") Long incidentesId) {
        if (incidenteLogic.getIncidente(incidentesId) == null) {
            throw new WebApplicationException(INCI + incidentesId + "/actuacion no existe.", 404);
        }
        return ActuacionResource.class;
    }

}