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
        if(user.getUsername()==null || user.getUsername().isEmpty())
        {
            throw new BusinessLogicException("El usuario no puede ser vacio");
        }
        
        if(persistence.findByUsername(user.getUsername()) != null){
            throw new BusinessLogicException("Ya existe un ususario con ese nombre.");
        }
        if(user.getPassword()==null || user.getPassword().isEmpty())
        {
            throw new BusinessLogicException("La contraseña no puede ser vacia.");
        }
        if(!checkString(user.getPassword())){
            throw new BusinessLogicException("La contraseña debe contener una mayuscula, una minuscula y un número.");
        }
        
        user = persistence.create(user);
        return user;
    }
    
    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }
        return false;
    }
}
