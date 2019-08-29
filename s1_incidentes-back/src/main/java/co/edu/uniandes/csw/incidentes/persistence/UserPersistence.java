/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.persistence;

import co.edu.uniandes.csw.incidentes.entities.UserEntity;
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
public class UserPersistence {
    
    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;
     
    public UserEntity create(UserEntity user){
        em.persist(user);
        //throw new java.lang.UnsupportedOperationException("Not suported yet");
        return user;
    }
    
    public UserEntity find(Long userId){
        return em.find(UserEntity.class, userId);
    }
    
    public List<UserEntity> listAll(){
        TypedQuery<UserEntity>  query = em.createQuery("select u from EmpleadoEntity u", UserEntity.class);
        return query.getResultList();
    }
    
    public UserEntity modify(UserEntity user){
        throw new java.lang.UnsupportedOperationException("Not suported yet");
    }
    
    public UserEntity delete(UserEntity user){
        throw new java.lang.UnsupportedOperationException("Not suported yet");
    }
    
}
