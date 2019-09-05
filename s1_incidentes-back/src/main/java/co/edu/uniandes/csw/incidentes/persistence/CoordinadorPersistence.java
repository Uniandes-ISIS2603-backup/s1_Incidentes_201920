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
    public CoordinadorEntity create(CoordinadorEntity coordinador)
    {
        em.persist(coordinador);
        return coordinador;
    }
    public CoordinadorEntity find(Long coordinadorId)
    {
        return em.find(CoordinadorEntity.class, coordinadorId);
    }
    public List<CoordinadorEntity> findAll()
    {
        TypedQuery query=em.createQuery("select u from CoordinadorEntity u", CoordinadorEntity.class);
        return query.getResultList();
    }
    public CoordinadorEntity update(CoordinadorEntity coordinadorEntity) {
        return em.merge(coordinadorEntity);
    }

    public void delete(Long coordinadorId) {
        CoordinadorEntity coordinadorEntity = em.find(CoordinadorEntity.class, coordinadorId);
        em.remove(coordinadorEntity);
    }
}
