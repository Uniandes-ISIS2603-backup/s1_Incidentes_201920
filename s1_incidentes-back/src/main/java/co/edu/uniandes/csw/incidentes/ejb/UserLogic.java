/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.UserEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.UserPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Camilo Castiblanco
 */

@Stateless
public class UserLogic {
    
    @Inject
    private UserPersistence persistence;
    public UserEntity createUser(UserEntity user) throws BusinessLogicException
    {
        if(user.getUsername()==null)
        {
            throw new BusinessLogicException("El usuario es nulo");
        }
        if(user.getPassword()==null)
        {
            throw new BusinessLogicException("La contraseña es nula");
        }
        
        user = persistence.create(user);
        return user;
    }
}
