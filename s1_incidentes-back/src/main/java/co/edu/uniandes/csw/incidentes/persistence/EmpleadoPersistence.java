/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.persistence;

import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Reyes
 */

@Stateless
public class EmpleadoPersistence {
    
    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;
    
    public EmpleadoEntity create(EmpleadoEntity empleado){
        em.persist(empleado);
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        return empleado;
    }
    
    public EmpleadoEntity find(Long empleadoId){
        
        return em.find(EmpleadoEntity.class,empleadoId);
    }
    
    public List<EmpleadoEntity> listAll(){
        TypedQuery<EmpleadoEntity>  query = em.createQuery("select u from EmpleadoEntity u", EmpleadoEntity.class);
        return query.getResultList();
    }
    
}
