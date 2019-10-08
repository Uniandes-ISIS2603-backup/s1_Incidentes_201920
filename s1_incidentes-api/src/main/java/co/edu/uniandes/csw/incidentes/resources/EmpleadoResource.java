/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.EmpleadoDTO;
import co.edu.uniandes.csw.incidentes.dtos.EmpleadoDetailDTO;
import co.edu.uniandes.csw.incidentes.ejb.EmpleadoLogic;
import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
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
 * @author Julian Jaimes
 */
@Path("empleado")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EmpleadoResource {
    
    @Inject
    private EmpleadoLogic empleadoLogic;

    private static final Logger LOGGER = Logger.getLogger(EmpleadoResource.class.getName());

    @POST
    public EmpleadoDTO createEmpleado(EmpleadoDTO empleado) throws BusinessLogicException {
        LOGGER.info("EmpleadoResource createEmpleado: input: " + empleado.toString());
        EmpleadoEntity empleadoEntity = empleado.toEntity();
        EmpleadoEntity newEmpleadoEntity = empleadoLogic.createEmpleado(empleadoEntity);
        EmpleadoDTO newEmpleadoDTO = new EmpleadoDTO(newEmpleadoEntity);
        LOGGER.info("EmpleadoResource createEmpleado: output: {0}" + newEmpleadoDTO.toString());
        return newEmpleadoDTO;
    }

    @GET
    @Path("{empleadoId: \\d+}")
    public EmpleadoDetailDTO getEmpleado(@PathParam("empleadoId") Long empleadoId) {
        LOGGER.log(Level.INFO, "EmpleadoResource getEmpleado: input: {0}", empleadoId);
        EmpleadoEntity entidad = empleadoLogic.getEmpleado(empleadoId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /empleado/" + empleadoId + " no existe.", 404);
        }
        EmpleadoDetailDTO empleadoDetailDTO = new EmpleadoDetailDTO(entidad);
        LOGGER.log(Level.INFO, "EmpleadoResource getEmpleado: output: {0}", empleadoDetailDTO);
        return empleadoDetailDTO;
    }

    @GET
    public List<EmpleadoDetailDTO> getEmpleados() {
        LOGGER.info("EmpleadoResource getEmpleadoes: input: void");
        List<EmpleadoDetailDTO> listaEmpleados;
        listaEmpleados = listEntity2DetailDTO(empleadoLogic.getEmpleados());
        LOGGER.log(Level.INFO, "EmpleadoResource getEmpleadoes: output: {0}", listaEmpleados);

        return listaEmpleados;
    }

    private List<EmpleadoDetailDTO> listEntity2DetailDTO(List<EmpleadoEntity> entityList) {
        List<EmpleadoDetailDTO> list = new ArrayList<>();
        for (EmpleadoEntity entity : entityList) {
            list.add(new EmpleadoDetailDTO(entity));
        }
        return list;
    }

    @PUT
    @Path("{empleadoId: \\d+}")
    public EmpleadoDetailDTO updateEmpleado(@PathParam("empleadoId") Long empleadoId, EmpleadoDetailDTO empleado) throws WebApplicationException {
        LOGGER.log(Level.INFO, "EmpleadoResource updateEmpleado: input: id: {0} , empleado: {1}", new Object[]{empleadoId, empleado});
        empleado.setId(empleadoId);
        if (empleadoLogic.getEmpleado(empleadoId) == null) {
            throw new WebApplicationException("El recurso /empleado/" + empleadoId + " no existe.", 404);
        }
        EmpleadoDetailDTO detailDTO = new EmpleadoDetailDTO(empleadoLogic.updateEmpleado(empleadoId, empleado.toEntity()));
        LOGGER.log(Level.INFO, "EmpleadoResource updateEmpleado: output: {0}", detailDTO);
        return detailDTO;
    }

    @DELETE
    @Path("{empleadoId: \\d+}")
    public void deleteEmpleado(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EmpleadoResource deleteEmpleado: input: {0}", empleadoId);
        EmpleadoEntity entity = empleadoLogic.getEmpleado(empleadoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /empleado/" + empleadoId + " no existe.", 404);
        }
        empleadoLogic.deleteEmpleado(empleadoId);
        LOGGER.info("EmpleadoResource deleteEmpleado: output: void");
    }

    @Path("{empleadoId: \\d+}/incidentes")
    public Class<EmpleadoIncidenteResource> getEmpleadoIncidenteResource(@PathParam("empleadoId") Long empleadoId) {
        if (empleadoLogic.getEmpleado(empleadoId) == null) {
            throw new WebApplicationException("El recurso /empleado/" + empleadoId + " no existe.", 404);
        }
        return EmpleadoIncidenteResource.class;
    }
}
