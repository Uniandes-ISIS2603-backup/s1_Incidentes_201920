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
 * Clase que implementa el recurso empleado
 * @author Julian Jaimes
 */
@Path("empleado")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EmpleadoResource {
    
    @Inject
    private EmpleadoLogic empleadoLogic;
        private static final String NOEXISTE = "no existe.";
        private static final String EMPLEADO =  "El recurso /empleado/";

    private static final Logger LOGGER = Logger.getLogger(EmpleadoResource.class.getName());

    /**
     * Crea un nuevo empleado con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * @param empleado {@link empleadoDTO} - El empleado que se desea
     * guardar.
     * @return JSON {@link empleadoDTO} - El empleado guardado con el
     * atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica generado cuando se imcumple una regla de negocio al crear
     * al nuevo empleado.
     */
    @POST
    public EmpleadoDTO createEmpleado(EmpleadoDTO empleado) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EmpleadoResource createEmpleado: input: {0}", empleado);
        EmpleadoEntity empleadoEntity = empleado.toEntity();
        EmpleadoEntity newEmpleadoEntity = empleadoLogic.createEmpleado(empleadoEntity);
        EmpleadoDTO newEmpleadoDTO = new EmpleadoDTO(newEmpleadoEntity);
        LOGGER.log(Level.INFO, "EmpleadoResource createEmpleado: output: '{'0'}'{0}", newEmpleadoDTO);
        return newEmpleadoDTO;
    }

    /**
     * Busca el empleado con el id asociado recibido en la URL y lo devuelve.
     *
     * @param empleadoId Identificador del empleado que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link empleadoDetailDTO} - El empleado buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el empleado.
     */
    @GET
    @Path("{empleadoId: \\d+}")
    public EmpleadoDetailDTO getEmpleado(@PathParam("empleadoId") Long empleadoId) {
        LOGGER.log(Level.INFO, "EmpleadoResource getEmpleado: input: {0}", empleadoId);
        EmpleadoEntity entidad = empleadoLogic.getEmpleado(empleadoId);
        if (entidad == null) {
            throw new WebApplicationException(EMPLEADO + empleadoId + NOEXISTE, 404);
        }
        EmpleadoDetailDTO empleadoDetailDTO = new EmpleadoDetailDTO(entidad);
        LOGGER.log(Level.INFO, "EmpleadoResource getEmpleado: output: {0}", empleadoDetailDTO);
        return empleadoDetailDTO;
    }

    /**
     * Busca y devuelve todos los empleadoes que existen en la aplicacion.
     *
     * @return JSONArray {@link empleadoDetailDTO} - Los empleadoes
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<EmpleadoDetailDTO> getEmpleados() {
        LOGGER.info("EmpleadoResource getEmpleadoes: input: void");
        List<EmpleadoDetailDTO> listaEmpleados;
        listaEmpleados = listEntity2DetailDTO(empleadoLogic.getEmpleados());
        LOGGER.log(Level.INFO, "EmpleadoResource getEmpleadoes: output: {0}", listaEmpleados);

        return listaEmpleados;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos empleadoEntity a una lista
     * de objetos empleadoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de empleadoes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de empleadoes en forma DTO (json)
     */
    private List<EmpleadoDetailDTO> listEntity2DetailDTO(List<EmpleadoEntity> entityList) {
        List<EmpleadoDetailDTO> list = new ArrayList<>();
        for (EmpleadoEntity entity : entityList) {
            list.add(new EmpleadoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Actualiza el empleado con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param empleadoId Identificador del empleado que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param empleado {@link empleadoDetailDTO} El empleado que se
     * desea guardar.
     * @return JSON {@link empleadoDetailDTO} - El empleado guardado.
     * @throws BusinessLogicException
     * Error de lógica que se genera cuando se incumple una regla de negocio.
     */
    @PUT
    @Path("{empleadoId: \\d+}")
    public EmpleadoDetailDTO updateEmpleado(@PathParam("empleadoId") Long empleadoId, EmpleadoDetailDTO empleado) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EmpleadoResource updateEmpleado: input: id: {0} , empleado: {1}", new Object[]{empleadoId, empleado});
        empleado.setId(empleadoId);
        if (empleadoLogic.getEmpleado(empleadoId) == null) {
            throw new WebApplicationException(EMPLEADO + empleadoId + NOEXISTE, 404);
        }
        EmpleadoDetailDTO detailDTO = new EmpleadoDetailDTO(empleadoLogic.updateEmpleado(empleadoId ,empleado.toEntity()));
        LOGGER.log(Level.INFO, "EmpleadoResource updateEmpleado: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el empleado con el id asociado recibido en la URL.
     *
     * @param empleadoId Identificador del empleado que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el empleado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando se incumple una regla de negocio.
     */
    @DELETE
    @Path("{empleadoId: \\d+}")
    public void deleteEmpleado(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EmpleadoResource deleteEmpleado: input: {0}", empleadoId);
        EmpleadoEntity entity = empleadoLogic.getEmpleado(empleadoId);
        if (entity == null) {
            throw new WebApplicationException(EMPLEADO + empleadoId + NOEXISTE, 404);
        }
        empleadoLogic.deleteEmpleado(empleadoId);
        LOGGER.info("EmpleadoResource deleteEmpleado: output: void");
    }

    /**
     * Conexión con el servicio de incidentes para un empleado.
     * {@link EmpleadoIncidenteResource}
     *
     * Este método conecta la ruta de /empleado con las rutas de /incidentes
     * que dependen del empleado, es una redirección al servicio que maneja
     * el segmento de la URL que se encarga de los incidentes de un empleado.
     *
     * @param empleadoId El ID del empleado con respecto al cual se accede
     * al servicio.
     * @return El servicio de incidentes para este empleado en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el empleado.
     */
    @Path("{empleadoId: \\d+}/incidentes")
    public Class<EmpleadoIncidenteResource> getEmpleadoIncidenteResource(@PathParam("empleadoId") Long empleadoId) {
        if (empleadoLogic.getEmpleado(empleadoId) == null) {
            throw new WebApplicationException(EMPLEADO + empleadoId + NOEXISTE, 404);
        }
        return EmpleadoIncidenteResource.class;
    }
}
