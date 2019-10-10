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
 * Clase que implementa el recurso "coordinador".
 *
 * @author Juan Camilo Castiblanco.
 */
@Path("coordinador")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CoordinadorResource {

    @Inject
    private CoordinadorLogic coordinadorLogic;

    private static final Logger LOGGER = Logger.getLogger(CoordinadorResource.class.getName());

    /**
     * Crea un nuevo coordinador con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * @param coordinador {@link CoordinadorDTO} - El coordinador que se desea
     * guardar.
     * @return JSON {@link CoordinadorDTO} - El coordinador guardado con el
     * atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica generado cuando se imcumple una regla de negocio al crear
     * al nuevo coordinador.
     */
    @POST
    public CoordinadorDTO createCoordinador(CoordinadorDTO coordinador) throws BusinessLogicException {
        LOGGER.info("CoordinadorResource createCoordinador: input: " + coordinador.toString());
        CoordinadorEntity coordinadorEntity = coordinador.toEntity();
        CoordinadorEntity newCoordinadorEntity = coordinadorLogic.createCoordinador(coordinadorEntity);
        CoordinadorDTO newCoordinadorDTO = new CoordinadorDTO(newCoordinadorEntity);
        LOGGER.info("CoordinadorResource createCoordinador: output: {0}" + newCoordinadorDTO.toString());
        return newCoordinadorDTO;
    }

    /**
     * Busca el coordinador con el id asociado recibido en la URL y lo devuelve.
     *
     * @param coordinadorId Identificador del coordinador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link CoordinadorDetailDTO} - El coordinador buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el coordinador.
     */
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

    /**
     * Busca y devuelve todos los coordinadores que existen en la aplicacion.
     *
     * @return JSONArray {@link CoordinadorDetailDTO} - Los coordinadores
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CoordinadorDetailDTO> getCoordinadores() {
        LOGGER.info("CoordinadorResource getCoordinadores: input: void");
        List<CoordinadorDetailDTO> listaCoordinadores;
        listaCoordinadores = listEntity2DetailDTO(coordinadorLogic.getCoordinadores());
        LOGGER.log(Level.INFO, "CoordinadorResource getCoordinadores: output: {0}", listaCoordinadores);

        return listaCoordinadores;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos CoordinadorEntity a una lista
     * de objetos CoordinadorDetailDTO (json)
     *
     * @param entityList corresponde a la lista de coordinadores de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de coordinadores en forma DTO (json)
     */
    private List<CoordinadorDetailDTO> listEntity2DetailDTO(List<CoordinadorEntity> entityList) {
        List<CoordinadorDetailDTO> list = new ArrayList<>();
        for (CoordinadorEntity entity : entityList) {
            list.add(new CoordinadorDetailDTO(entity));
        }
        return list;
    }

    /**
     * Actualiza el coordinador con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param coordinadorId Identificador del coordinador que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param coordinador {@link CoordinadorDetailDTO} El coordinador que se
     * desea guardar.
     * @return JSON {@link CoordinadorDetailDTO} - El coordinador guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando se incumple una regla de negocio.
     */
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

    /**
     * Borra el coordinador con el id asociado recibido en la URL.
     *
     * @param coordinadorId Identificador del coordinador que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el coordinador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando se incumple una regla de negocio.
     */
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

    /**
     * Conexión con el servicio de incidentes para un coordinador.
     * {@link CoordinadorIncidenteResource}
     *
     * Este método conecta la ruta de /coordinador con las rutas de /incidentes
     * que dependen del coordinador, es una redirección al servicio que maneja
     * el segmento de la URL que se encarga de los incidentes de un coordinador.
     *
     * @param coordinadorId El ID del coordinador con respecto al cual se accede
     * al servicio.
     * @return El servicio de incidentes para este coordinador en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el coordinador.
     */
    @Path("{coordinadorId: \\d+}/incidentes")
    public Class<CoordinadorIncidenteResource> getCoordinadorIncidenteResource(@PathParam("coordinadorId") Long coordinadorId) {
        if (coordinadorLogic.getCoordinador(coordinadorId) == null) {
            throw new WebApplicationException("El recurso /coordinador/" + coordinadorId + " no existe.", 404);
        }
        return CoordinadorIncidenteResource.class;
    }

    /**
     * Conexión con el servicio de tecnicos para un coordinador.
     * {@link CoordinadorTecnicosResource}
     *
     * Este método conecta la ruta de /coordinador con las rutas de /tecnicos
     * que dependen del coordinador, es una redirección al servicio que maneja
     * el segmento de la URL que se encarga de los tecnicos de un coordinador.
     *
     * @param coordinadorId El ID del coordinador con respecto al cual se accede
     * al servicio.
     * @return El servicio de tecnico para este coordinador en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el coordinador.
     */
    @Path("{coordinadorId: \\d+}/tecnicos")
    public Class<CoordinadorTecnicosResource> getCoordinadorTecnicoResource(@PathParam("coordinadorId") Long coordinadorId) {
        if (coordinadorLogic.getCoordinador(coordinadorId) == null) {
            throw new WebApplicationException("El recurso /coordinador/" + coordinadorId + " no existe.", 404);
        }
        return CoordinadorTecnicosResource.class;
    }

}
