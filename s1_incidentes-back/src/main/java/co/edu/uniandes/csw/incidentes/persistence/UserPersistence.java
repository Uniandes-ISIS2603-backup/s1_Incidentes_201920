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
    
    public UserEntity create(UserEntity incidente)
    {
        em.persist(incidente);
        return incidente;
    }
    
    public UserEntity find(Long incidenteId)
    {
        return em.find(UserEntity.class, incidenteId);   
    }
    
    public List<UserEntity> findAll()
    {
        TypedQuery query=em.createQuery("select u from UserEntity u", UserEntity.class);
        return query.getResultList();
    }
    
    public UserEntity update(UserEntity userEntity) {
        return em.merge(userEntity);
    }
    
    public void delete(Long userId) {
        UserEntity userEntity = em.find(UserEntity.class, userId);
        em.remove(userEntity);
    }
    
    public UserEntity findByUsername(String nombre){
        UserEntity respuesta;
        TypedQuery<UserEntity> query= em.createQuery("select e from UserEntity e where e.username =:name", UserEntity.class);
        query = query.setParameter("name", nombre);
        List<UserEntity> lista = query.getResultList();
        if(lista == null){
            respuesta = null;
        } else if(lista.isEmpty()){
            respuesta= null;
        } else {
            respuesta = lista.get(0);
        }
        return respuesta; 
    }
}
