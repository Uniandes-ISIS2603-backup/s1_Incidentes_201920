/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.entities;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author df.foreroc
 */
@Stateless
public class IncidentePersistence {
    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;
    public IncidenteEntity create(IncidenteEntity incidente)
    {
        em.persist(incidente);
        return incidente;
    }
    public IncidenteEntity find(Long incidenteId)
    {
        return em.find(IncidenteEntity.class, incidenteId);
        
    }
    public List<IncidenteEntity> findAll()
    {
        TypedQuery query=em.createQuery("select u from IncidenteEntity u", IncidenteEntity.class);
        return query.getResultList();
    }

    
    
    
}
