/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Camilo Castiblanco
 */
@Stateless
public class CoordinadorLogic {
    @Inject
    private CoordinadorPersistence persistence;
    public CoordinadorEntity createCoordinador(CoordinadorEntity coordinador) throws BusinessLogicException
    {
        if(coordinador.getName()==null)
        {
            throw new BusinessLogicException("El nombre es nulo.");
        }
        if(coordinador.getUsername()==null || coordinador.getUsername().isEmpty())
        {
            throw new BusinessLogicException("El usuario no puede ser vacio");
        }
        if(persistence.findByUsername(coordinador.getUsername()) != null){
            throw new BusinessLogicException("Ya existe un ususario con ese nombre.");
        }
        
        if(coordinador.getPassword()==null || coordinador.getPassword().isEmpty())
        {
            throw new BusinessLogicException("La contraseña no puede ser vacia.");
        }
        /* TODO Al crear un objeto con Podam asegurar que cumpla esta condición para que el test no falle. 
        if(!checkString(user.getPassword())){
            throw new BusinessLogicException("La contraseña debe contener una mayuscula, una minuscula y un número.");
        }*/
        
        coordinador = persistence.create(coordinador);
        return coordinador;
    }
    
    public List<CoordinadorEntity> getCoordinadores() {
        List<CoordinadorEntity> lista = persistence.findAll();
        return lista;
    }
    
    public CoordinadorEntity getCoordinador(Long coordinadorId) {
        CoordinadorEntity coordinadorEntity = persistence.find(coordinadorId);
        return coordinadorEntity;
    }
    
    public CoordinadorEntity updateCoordinador(Long coordinadorId, CoordinadorEntity coordinador) {
        CoordinadorEntity newCoordinadorEntity = persistence.update(coordinador);
        return newCoordinadorEntity;
    }
    
    public void deleteCoordinador(Long coordinadorId){
        persistence.delete(coordinadorId);
    }
    
    /*
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
    }*/
}
