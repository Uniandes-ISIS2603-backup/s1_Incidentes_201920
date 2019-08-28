/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.persistence;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Camilo Castiblanco
 */
@Stateless
public class CoordinadorPersistence {
    
    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;
    
    public CoordinadorEntity create(CoordinadorEntity coordinador){
        em.persist(coordinador);
        //throw new java.lang.UnsupportedOperationException("Not suported yet");
        return coordinador;
    }
    
    public CoordinadorEntity search(CoordinadorEntity coordinador){
        throw new java.lang.UnsupportedOperationException("Not suported yet");
    }
    
    public CoordinadorEntity modify(CoordinadorEntity coordinador){
        throw new java.lang.UnsupportedOperationException("Not suported yet");
    }
    
    public CoordinadorEntity delete(CoordinadorEntity coordinador){
        throw new java.lang.UnsupportedOperationException("Not suported yet");
    }
}
