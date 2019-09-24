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
    public CoordinadorEntity createUser(CoordinadorEntity coordinador) throws BusinessLogicException
    {
        if(coordinador.getName()==null)
        {
            throw new BusinessLogicException("El nombre es nulo.");
        }
        
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
}
