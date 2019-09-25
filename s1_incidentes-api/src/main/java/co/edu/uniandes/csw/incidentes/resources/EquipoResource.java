/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.EquipoDTO;
import co.edu.uniandes.csw.incidentes.ejb.EquipoLogic;
import co.edu.uniandes.csw.incidentes.entities.EquipoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
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
 * @author Julian Jaimes
 */
@Path("equipos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EquipoResource {
    private static final Logger LOGGER = Logger.getLogger(EquipoResource.class.getName());
    @Inject
    private EquipoLogic equipoLogic;
    
    @POST
    public EquipoDTO createEquipo(EquipoDTO equipo) throws BusinessLogicException {
        EquipoEntity equipoentity = equipo.toEntity();
        equipoentity = equipoLogic.createEquipo(equipoentity);
        return new EquipoDTO (equipoentity);
    }
    
     @GET
    @Path("{equiposId: \\d+}")
    public EquipoDTO getEquipo(@PathParam("equiposId") Long equiposId) throws WebApplicationException {
        EquipoEntity equipoEntity = equipoLogic.findEquipo(equiposId);
        if (equipoEntity == null) {
            throw new WebApplicationException("El recurso /equipos/" + equiposId + " no existe.", 404);
        }
        EquipoDTO detailDTO = new EquipoDTO(equipoEntity);
        return detailDTO;
    }

    @PUT
    @Path("{equiposId: \\d+}")
    public EquipoDTO updateEquipo(@PathParam("equiposId") Long equiposId, String tipo, EquipoDTO equipo) throws WebApplicationException {
        equipo.setTipo(tipo);
        if (equipoLogic.findEquipo(equiposId) == null) {
            throw new WebApplicationException("El recurso /equipos/" + equiposId + " no existe.", 404);
        }
        EquipoDTO DTO = new EquipoDTO(equipoLogic.updateEquipo(equipo.toEntity()));
        return DTO;

    }

    @DELETE
    @Path("{equiposId: \\d+}")
    public void deleteEquipo(@PathParam("equiposId") Long equiposId) throws BusinessLogicException {
        if (equipoLogic.findEquipo(equiposId) == null) {
            throw new WebApplicationException("El recurso /equipos/" + equiposId + " no existe.", 404);
        }
        equipoLogic.deleteEquipo(equiposId);
    }
}
