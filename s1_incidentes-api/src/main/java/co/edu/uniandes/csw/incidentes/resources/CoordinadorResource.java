/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.CoordinadorDTO;
import co.edu.uniandes.csw.incidentes.dtos.CoordinadorDetailDTO;
import co.edu.uniandes.csw.incidentes.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
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
 * @author Juan Camilo Castiblanco
 */
@Path("coordinador")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CoordinadorResource {

    @Inject
    private CoordinadorLogic coordinadorLogic;

    private static final Logger LOGGER = Logger.getLogger(CoordinadorResource.class.getName());

    @POST
    public CoordinadorDTO createCoordinador(CoordinadorDTO coordinador) throws BusinessLogicException {
        LOGGER.info("CoordinadorResource createCoordinador: input: " + coordinador.toString());
        CoordinadorEntity coordinadorEntity = coordinador.toEntity();
        CoordinadorEntity newCoordinadorEntity = coordinadorLogic.createCoordinador(coordinadorEntity);
        CoordinadorDTO newCoordinadorDTO = new CoordinadorDTO(newCoordinadorEntity);
        LOGGER.info("CoordinadorResource createCoordinador: output: {0}" + newCoordinadorDTO.toString());
        return newCoordinadorDTO;
    }

    @GET
    @Path("{coordinadorId: \\d+}")
    public CoordinadorDetailDTO getCoordinador(@PathParam("coordinadorId") Long coordinadorId) {
        LOGGER.log(Level.INFO, "CoordinadorResource getCoordinador: input: {0}", coordinadorId);
        CoordinadorEntity entidad = coordinadorLogic.getCoordinador(coordinadorId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /coordinador/" + coordinadorId + " no existe.", 404);
        }
        CoordinadorDetailDTO coordinadorDetailDTO = new CoordinadorDetailDTO(entidad);
        LOGGER.log(Level.INFO, "CoordinadorResource getCoordinador: output: {0}", coordinadorDetailDTO);
        return coordinadorDetailDTO;
    }

    @GET
    public List<CoordinadorDetailDTO> getCoordinadores() {
        LOGGER.info("CoordinadorResource getCoordinadores: input: void");
        List<CoordinadorDetailDTO> listaCoordinadores;
        listaCoordinadores = listEntity2DetailDTO(coordinadorLogic.getCoordinadores());
        LOGGER.log(Level.INFO, "CoordinadorResource getCoordinadores: output: {0}", listaCoordinadores);

        return listaCoordinadores;
    }

    private List<CoordinadorDetailDTO> listEntity2DetailDTO(List<CoordinadorEntity> entityList) {
        List<CoordinadorDetailDTO> list = new ArrayList<>();
        for (CoordinadorEntity entity : entityList) {
            list.add(new CoordinadorDetailDTO(entity));
        }
        return list;
    }

    @PUT
    @Path("{coordinadorId: \\d+}")
    public CoordinadorDetailDTO updateCoordinador(@PathParam("coordinadorId") Long coordinadorId, CoordinadorDetailDTO coordinador) throws WebApplicationException {
        LOGGER.log(Level.INFO, "CoordinadorResource updateCoordinador: input: id: {0} , coordinador: {1}", new Object[]{coordinadorId, coordinador});
        coordinador.setId(coordinadorId);
        if (coordinadorLogic.getCoordinador(coordinadorId) == null) {
            throw new WebApplicationException("El recurso /coordinador/" + coordinadorId + " no existe.", 404);
        }
        CoordinadorDetailDTO detailDTO = new CoordinadorDetailDTO(coordinadorLogic.updateCoordinador(coordinadorId, coordinador.toEntity()));
        LOGGER.log(Level.INFO, "CoordinadorResource updateCoordinador: output: {0}", detailDTO);
        return detailDTO;
    }

    @DELETE
    @Path("{coordinadorId: \\d+}")
    public void deleteCoordinador(@PathParam("coordinadorId") Long coordinadorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CoordinadorResource deleteCoordinador: input: {0}", coordinadorId);
        CoordinadorEntity entity = coordinadorLogic.getCoordinador(coordinadorId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /coordinador/" + coordinadorId + " no existe.", 404);
        }
        coordinadorLogic.deleteCoordinador(coordinadorId);
        LOGGER.info("CoordinadorResource deleteCoordinador: output: void");
    }

    @Path("{coordinadorId: \\d+}/incidentes")
    public Class<CoordinadorIncidenteResource> getCoordinadorIncidenteResource(@PathParam("coordinadorId") Long coordinadorId) {
        if (coordinadorLogic.getCoordinador(coordinadorId) == null) {
            throw new WebApplicationException("El recurso /coordinador/" + coordinadorId + " no existe.", 404);
        }
        return CoordinadorIncidenteResource.class;
    }
    
    @Path("{coordinadorId: \\d+}/tecnicos")
    public Class<CoordinadorTecnicoResource> getCoordinadorTecnicoResource(@PathParam("coordinadorId") Long coordinadorId) {
        if (coordinadorLogic.getCoordinador(coordinadorId) == null) {
            throw new WebApplicationException("El recurso /coordinador/" + coordinadorId + " no existe.", 404);
        }
        return CoordinadorTecnicoResource.class;
    }

}
