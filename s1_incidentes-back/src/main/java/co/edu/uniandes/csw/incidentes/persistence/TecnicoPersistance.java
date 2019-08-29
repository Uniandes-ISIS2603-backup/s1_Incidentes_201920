/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.persistence;

import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Estudiante Diana Alejandra Silva Alvarez
 */
@Stateless
public class TecnicoPersistance {
    
    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;
    
    public TecnicoEntity create (TecnicoEntity tecnicoEntity){
        em.persist(tecnicoEntity);
        return tecnicoEntity;
    }
    
    public TecnicoEntity find (Long tecnicoID){
        return em.find(TecnicoEntity.class , tecnicoID);
    }
    
    public List<TecnicoEntity> findAll()
    {
       TypedQuery<TecnicoEntity> query = em.createQuery("", TecnicoEntity.class);
        return query.getResultList();
    }
}

