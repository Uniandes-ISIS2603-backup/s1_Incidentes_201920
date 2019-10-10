/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.CoordinadorDTO;
import co.edu.uniandes.csw.incidentes.dtos.TecnicoDetailDTO;
import co.edu.uniandes.csw.incidentes.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.incidentes.ejb.TecnicoCoordinadorLogic;
import co.edu.uniandes.csw.incidentes.ejb.TecnicoLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
@Path("tecnico/{tecnicoId: \\d+}/coordinador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TecnicoCoordinadorResource {
    
    private static final Logger LOGGER = Logger.getLogger(TecnicoCoordinadorResource.class.getName());

    @Inject
    private TecnicoLogic tecnicoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private TecnicoCoordinadorLogic tecnicoCoordinadorLogic ;// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CoordinadorLogic coordinadorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Editorial asociada a un Book.
     *
     * @param tecnicoId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param coordinador La editorial que se será del libro.
     * @return JSON {@link BookDetailDTO} - El arreglo de libros guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial o el
     * libro.
     */
    @PUT
    public TecnicoDetailDTO replaceCoordinador(@PathParam("tecnicoId") Long tecnicoId, CoordinadorDTO coordinador) {
        LOGGER.log(Level.INFO, "TecnicoCoordinadorResource replaceCoordinador: input: tecnicoId{0} , coordinador:{1}", new Object[]{tecnicoId, coordinador});
        if (tecnicoLogic.getTecnico(tecnicoId) == null) {
            throw new WebApplicationException("El recurso /tecnico/" + tecnicoId + " no existe.", 404);
        }
        if (coordinadorLogic.getCoordinador(coordinador.getId()) == null) {
            throw new WebApplicationException("El recurso /coordinador/" + coordinador.getId() + " no existe.", 404);
        }
        TecnicoDetailDTO tecnicoDetailDTO = new TecnicoDetailDTO(tecnicoCoordinadorLogic.replaceCoordinador(tecnicoId, coordinador.getId()));
        LOGGER.log(Level.INFO, "TecnicoCoordinadorResource replaceCoordinador: output: {0}", tecnicoDetailDTO);
        return tecnicoDetailDTO;
    }
}
