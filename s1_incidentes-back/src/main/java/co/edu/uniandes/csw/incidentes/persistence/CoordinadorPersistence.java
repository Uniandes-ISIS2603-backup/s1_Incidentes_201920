/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.persistence;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    public CoordinadorEntity find(Long coordinadorId){
        return em.find(CoordinadorEntity.class, coordinadorId);
    }
    
    public List<CoordinadorEntity> listAll(){
        TypedQuery<CoordinadorEntity>  query = em.createQuery("select u from EmpleadoEntity u", CoordinadorEntity.class);
        return query.getResultList();
    }
    
    public CoordinadorEntity modify(CoordinadorEntity coordinador){
        throw new java.lang.UnsupportedOperationException("Not suported yet");
    }
    
    public CoordinadorEntity delete(CoordinadorEntity coordinador){
        throw new java.lang.UnsupportedOperationException("Not suported yet");
    }
}
