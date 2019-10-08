/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.ejb.ActuacionLogic;
import co.edu.uniandes.csw.incidentes.ejb.IncidenteActuacionLogic;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "incidente/{id}/actuaciones".
 *
 * @author df.foreroc
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IncidenteActuacionResource {
    private static final Logger LOGGER = Logger.getLogger(IncidenteActuacionResource.class.getName());

    @Inject
    private ActuacionLogic actuacionLogic;

    @Inject
    private IncidenteActuacionLogic incidenteActuacionLogic;
    
}
