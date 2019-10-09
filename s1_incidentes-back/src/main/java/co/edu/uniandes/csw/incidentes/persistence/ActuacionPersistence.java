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
        LOGGER.log(Level.INFO, "Creando una nueva actuación");
        em.persist(actuacion);
        LOGGER.log(Level.INFO, "Actuación creada");
        return actuacion;
    }
    
    public ActuacionEntity find(Long idIncidente, Long actuacionId) {
        LOGGER.log(Level.INFO,"Consultando la actuación con id = {0} del libro con id = " + idIncidente, actuacionId);
        TypedQuery<ActuacionEntity> q = em.createQuery("select p from ActuacionEntity p where (p.incidente.id = :idIncidente) "
                + " and (p.id = :actuacionId)", ActuacionEntity.class);
        q.setParameter("idIncidente", idIncidente);
        q.setParameter("actuacionId", actuacionId);
        List<ActuacionEntity> resultados = q.getResultList();
        ActuacionEntity actuacion = null;
        if (resultados == null || resultados.isEmpty()) {
            actuacion = null;
        } else {
            actuacion = resultados.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar la actuación con id = {0} del incidente con id = " + idIncidente, actuacionId);
        return actuacion;
    }
    
    public ActuacionEntity update(ActuacionEntity actuacion) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", actuacion.getId());
        return em.merge(actuacion);
    }
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando el author con id={0}", id);
        ActuacionEntity actuacionEntity = em.find(ActuacionEntity.class, id);
        em.remove(actuacionEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la actuación con id = {0}", id);
    }
}
