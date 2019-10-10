/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.TecnicoDTO;
import co.edu.uniandes.csw.incidentes.dtos.TecnicoDetailDTO;
import co.edu.uniandes.csw.incidentes.ejb.CoordinadorTecnicosLogic;
import co.edu.uniandes.csw.incidentes.ejb.TecnicoLogic;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
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
 * @author c.alcala
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CoordinadorTecnicosResource {
    private static final Logger LOGGER = Logger.getLogger(CoordinadorTecnicosResource.class.getName());

    @Inject
    private CoordinadorTecnicosLogic coordinadorTecnicosLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private TecnicoLogic tecnicoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una editorial con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la editorial.
     *
     * @param coordinadorId Identificador de la editorial que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param tecnicoId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El libro guardado en la editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{tecnicoId: \\d+}")
    public TecnicoDTO addTecnico(@PathParam("coordinadorId") Long coordinadorId, @PathParam("tecnicoId") Long tecnicoId) {
        LOGGER.log(Level.INFO, "CoordinadorTecnicosResource addTecnico: input: coordinadorId: {0} , tecnicoId: {1}", new Object[]{coordinadorId, tecnicoId});
        if (tecnicoLogic.getTecnico(tecnicoId) == null) {
            throw new WebApplicationException("El recurso /books/" + tecnicoId + " no existe.", 404);
        }
        TecnicoDTO tecnicoDTO = new TecnicoDTO(coordinadorTecnicosLogic.addTecnico(tecnicoId, coordinadorId));
        LOGGER.log(Level.INFO, "CoordinadorTecnicosResourse addTecnico: output: {0}", tecnicoDTO);
        return tecnicoDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la editorial.
     *
     * @param coordinadorId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * editorial. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TecnicoDetailDTO> getTecnicos(@PathParam("coordinadorId") Long coordinadorId) {
        LOGGER.log(Level.INFO, "CoordinadorTecnicosResource getTecnicos: input: {0}", coordinadorId);
        List<TecnicoDetailDTO> listaDetailDTOs = tecnicosListEntity2DTO(coordinadorTecnicosLogic.getTecnicos(coordinadorId));
        LOGGER.log(Level.INFO, "CoordinadorTecnicosResource getTecnicos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la editorial con id asociado.
     *
     * @param coordinadorId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param tecnicoId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * editorial.
     */
    @GET
    @Path("{tecnicoId: \\d+}")
    public TecnicoDetailDTO getBook(@PathParam("coordinadorId") Long coordinadorId, @PathParam("tecnicoId") Long tecnicoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CoordinadorTecnicosResource getTecnico: input: coordinadorId: {0} , tecnicoId: {1}", new Object[]{coordinadorId, tecnicoId});
        if (tecnicoLogic.getTecnico(tecnicoId) == null) {
            throw new WebApplicationException("El recurso /coordinador/" + coordinadorId + "/tecnicos/" + tecnicoId + " no existe.", 404);
        }
        TecnicoDetailDTO tecnicoDetailDTO = new TecnicoDetailDTO(coordinadorTecnicosLogic.getTecnico(coordinadorId, tecnicoId));
        LOGGER.log(Level.INFO, "CoordinadorTecnicosResource getTecnico: output: {0}", tecnicoDetailDTO);
        return tecnicoDetailDTO;
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Editorial
     *
     * @param coordinadorId Identificador de la editorial que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param tecnicos JSONArray {@link BookDTO} El arreglo de libros nuevo para la
     * editorial.
     * @return JSON {@link BookDTO} - El arreglo de libros guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<TecnicoDetailDTO> replaceTecnicos(@PathParam("coordinadorId") Long coordinadorId, List<TecnicoDetailDTO> tecnicos) {
        LOGGER.log(Level.INFO, "CoordinadorTecnicosResource replaceTecnicos: input: coordinadorId: {0} , tecnicos: {1}", new Object[]{coordinadorId, tecnicos});
        for (TecnicoDetailDTO tecnico : tecnicos) {
            if (tecnicoLogic.getTecnico(tecnico.getId()) == null) {
                throw new WebApplicationException("El recurso /tecnicos/" + tecnico.getId() + " no existe.", 404);
            }
        }
        List<TecnicoDetailDTO> listaDetailDTOs = tecnicosListEntity2DTO(coordinadorTecnicosLogic.replaceTecnicos(coordinadorId, tecnicosListDTO2Entity(tecnicos)));
        LOGGER.log(Level.INFO, "CoordinadorTecnicosResource replaceTecnicos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDTO convertida.
     */
    private List<TecnicoDetailDTO> tecnicosListEntity2DTO(List<TecnicoEntity> entityList) {
        List<TecnicoDetailDTO> list = new ArrayList();
        for (TecnicoEntity entity : entityList) {
            list.add(new TecnicoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     */
    private List<TecnicoEntity> tecnicosListDTO2Entity(List<TecnicoDetailDTO> dtos) {
        List<TecnicoEntity> list = new ArrayList<>();
        for (TecnicoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
