/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.EquipoDTO;
import co.edu.uniandes.csw.incidentes.ejb.EquipoLogic;
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
 * @author Julian Jaimes
 */
@Path("Equipo")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EquipoResource {
    private static final Logger LOGGER = Logger.getLogger(EquipoResource.class.getName());
    @Inject
    EquipoLogic equipoLogic;
    
    @POST
    public EquipoDTO createEquipo(EquipoDTO equipo) throws BusinessLogicException {
        //LOGGER.log
        return equipo;
    }
}
