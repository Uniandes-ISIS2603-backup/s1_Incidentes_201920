/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.CoordinadorDTO;
import co.edu.uniandes.csw.incidentes.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
    public CoordinadorDTO createCoordinador(CoordinadorDTO coordinador)throws BusinessLogicException{ 
       LOGGER.info("CoordinadorResource createCoordinador: input: "+ coordinador.toString());
       CoordinadorEntity coordinadorEntity = coordinador.toEntity();
       CoordinadorEntity newCoordinadorEntity = coordinadorLogic.createCoordinador(coordinadorEntity);
       CoordinadorDTO newCoordinadorDTO = new CoordinadorDTO(newCoordinadorEntity);
       LOGGER.info("CoordinadorResource createCoordinador: output: "+ newCoordinadorDTO.toString());
       return newCoordinadorDTO;
    }
    
}
