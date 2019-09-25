/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.resources;

import co.edu.uniandes.csw.incidentes.dtos.UserDTO;
import co.edu.uniandes.csw.incidentes.ejb.UserLogic;
import co.edu.uniandes.csw.incidentes.entities.UserEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan Camilo Castiblanco
 */

@Path("user")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UserResource {
    
    @Inject
    private UserLogic logica;
    
    private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());
    
    @POST
    public UserDTO createUser(UserDTO user)throws BusinessLogicException{ 
       LOGGER.info("UserResource createUser: input: "+ user.toString());
       UserEntity userEntity = user.toEntity();
       UserEntity newUserEntity = logica.createUser(userEntity);
       UserDTO newUserDTO = new UserDTO(newUserEntity);
       LOGGER.info("UserResource createUser: output: "+ newUserDTO.toString());
       return newUserDTO;
    }
    
    @GET
    public List<UserDTO> getUsers() {
        return null;
    }
    
    @GET
    @Path("{userId: \\d+}")
    public UserDTO getUser(@PathParam("userId") Long userId) throws WebApplicationException {
        UserEntity userEntity = logica.getUser(userId);
        if (userEntity == null) {
            throw new WebApplicationException("El recurso /user/" + userId + " no existe.", 404);
        }
        UserDTO detailDTO = new UserDTO(userEntity);
        return detailDTO;
    }
}
