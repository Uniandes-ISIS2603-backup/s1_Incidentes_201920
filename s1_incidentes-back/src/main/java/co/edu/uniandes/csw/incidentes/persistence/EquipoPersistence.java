/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.persistence;

import co.edu.uniandes.csw.incidentes.entities.EquipoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Julian Jaimes
 */

@Stateless
public class EquipoPersistence {
    
    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;
    
    public EquipoEntity create(EquipoEntity equipoEntity) {
        em.persist(equipoEntity);
        return equipoEntity;
    }
    
    public EquipoEntity find(Long equiposId) {
        return em.find(EquipoEntity.class, equiposId);
    }
    
    public List<EquipoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from EquipoEntity u", EquipoEntity.class);
        return query.getResultList();
    }
    
    public EquipoEntity update(EquipoEntity equipoE) {
        return em.merge(equipoE);
    }
    
    public void delete(Long equipoId) {
        EquipoEntity equipo = em.find(EquipoEntity.class, equipoId);
        em.remove(equipo);
    }
}
