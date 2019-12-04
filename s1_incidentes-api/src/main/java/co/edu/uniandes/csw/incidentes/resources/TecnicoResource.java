/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;


import co.edu.uniandes.csw.incidentes.dtos.TecnicoDTO;
import co.edu.uniandes.csw.incidentes.dtos.TecnicoDetailDTO;
import co.edu.uniandes.csw.incidentes.ejb.TecnicoCoordinadorLogic;
import co.edu.uniandes.csw.incidentes.ejb.TecnicoLogic;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
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
 * @tecnico da.silvaa
 */
@Path("tecnico")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TecnicoResource {
    private static final Logger LOGGER= Logger.getLogger(TecnicoResource.class.getName());
    private static final String NOEXISTE= "no existe.";
    @Inject
    private TecnicoLogic tecnicoLogic;

    @POST
    public TecnicoDTO createTecnico(TecnicoDTO tecnico) throws BusinessLogicException
    {
         LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", tecnico);
        // Invoca la lógica para crear la editorial nueva
         
        TecnicoEntity tecnicoEntity = tecnico.toEntity();
        TecnicoEntity newTecnicoEntity = tecnicoLogic.createTecnico(tecnicoEntity);
        TecnicoDTO newTecnicoDTO = new TecnicoDTO(newTecnicoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        LOGGER.log(Level.INFO, "IncidenteResource createIncidente: output: {0}", newTecnicoDTO.toString());
        return newTecnicoDTO; 
    }
    
    /**
     * Busca y devuelve todos los tecnicos que existen en la aplicacion.
     *
     * @return JSONArray {@link TecnicoDetailDTO} - Los tecnicos encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TecnicoDetailDTO> getTecnicos() {
        LOGGER.info("AuthorResource getAuthors: input: void");
        
        List<TecnicoDetailDTO> listaTecnicos;
        listaTecnicos = listEntity2DetailDTO(tecnicoLogic.getTecnicos());
        LOGGER.log(Level.INFO, "AuthorResource getAuthors: output: {0}", listaTecnicos);
        return listaTecnicos;
    }
    
    
    
    /**
     * Convierte una lista de TecnicoEntity a una lista de AuthorDetailDTO.
     *
     * @param entityList Lista de AuthorEntity a convertir.
     * @return Lista de AuthorDetailDTO convertida.
     */
    private List<TecnicoDetailDTO> listEntity2DetailDTO(List<TecnicoEntity> entityList) {
        List<TecnicoDetailDTO> list = new ArrayList<>();
        for (TecnicoEntity entity : entityList) {
            list.add(new TecnicoDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param authorsId Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{tecnicosId: \\d+}")
    public TecnicoDetailDTO getTecnico(@PathParam("tecnicosId") Long tecnicoId) {
        LOGGER.log(Level.INFO, "TecnicoResource getTecnico: input: {0}", tecnicoId);
        TecnicoEntity entidad = tecnicoLogic.getTecnico(tecnicoId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /tecnicos/" + tecnicoId + NOEXISTE, 404);
        }
        TecnicoDetailDTO tecnicoDetailDTO = new TecnicoDetailDTO(entidad);
        LOGGER.log(Level.INFO, "TecnicoResource getTecnico: output: {0}", tecnicoDetailDTO);
        return tecnicoDetailDTO;
    }
    
    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param authorsId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param author {@link AuthorDetailDTO} El autor que se desea guardar.
     * @return JSON {@link AuthorDetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    
    @PUT
    @Path("{tecnicosId: \\d+}")
    public TecnicoDetailDTO updateTecnico(@PathParam("tecnicosId") Long tecnicoId, TecnicoDetailDTO tecnico) {
        LOGGER.log(Level.INFO, "TecnicoResource updateTecnico: input: tecnicoId: {0} , tecnico: {1}", new Object[]{tecnicoId, tecnico});
        tecnico.setId(tecnicoId);
        if (tecnicoLogic.getTecnico(tecnicoId) == null) {
            throw new WebApplicationException("El recurso /tecnicos/" + tecnicoId + NOEXISTE, 404);
        }
        TecnicoDetailDTO detailDTO = new TecnicoDetailDTO(tecnicoLogic.updateTecnico(tecnicoId, tecnico.toEntity()));
        LOGGER.log(Level.INFO, "TecnicoResource updateTecnico: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Borra el autor con el id asociado recibido en la URL.
     *
     * @param authorsId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{tecnicosId: \\d+}")
    public void deleteTecnico(@PathParam("tecnicosId") Long tecnicoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnicoResource deleteTecnico: input: {0}", tecnicoId);
        if (tecnicoLogic.getTecnico(tecnicoId) == null) {
            throw new WebApplicationException("El recurso /tecnicos/" + tecnicoId + NOEXISTE, 404);
        }
        tecnicoLogic.deleteTecnico(tecnicoId);
        LOGGER.info("TecnicoResource deleteTecnico: output: void");
    }
    
    @Path("{tecnicosId: \\d+}/incidentes")
    public Class<TecnicoIncidenteResource> getIncidenteTecnicoResource(@PathParam("tecnicosId") Long tecnicoId) {
        if (tecnicoLogic.getTecnico(tecnicoId) == null) {
            throw new WebApplicationException("El recurso /tecnicos/" + tecnicoId + NOEXISTE, 404);
        }
        return TecnicoIncidenteResource.class;
    }
    
    }
