/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.persistence;

import co.edu.uniandes.csw.incidentes.entities.ActuacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Catalina Alcalá
 */
@Stateless
public class ActuacionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ActuacionPersistence.class.getName());
    
    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;
    
    public ActuacionEntity create(ActuacionEntity actuacion) {
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        em.persist(actuacion);
        return actuacion;
    }
    
    public ActuacionEntity find(Long actuacionId) {
        return em.find(ActuacionEntity.class, actuacionId);
    }
    
    public List<ActuacionEntity> findAll() {
        TypedQuery<ActuacionEntity> query = em.createQuery("select u from ActuacionEntity u", ActuacionEntity.class);
        return query.getResultList();
    }
    
    public ActuacionEntity update(ActuacionEntity actuacion) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", actuacion.getId());
        return em.merge(actuacion);
    }
    
    public void delete(Long id) {

        LOGGER.log(Level.INFO, "Borrando el author con id={0}", id);
        ActuacionEntity actuacionEntity = em.find(ActuacionEntity.class, id);
        em.remove(actuacionEntity);
    }
}
