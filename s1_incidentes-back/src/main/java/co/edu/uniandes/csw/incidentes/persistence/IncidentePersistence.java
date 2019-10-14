/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.persistence;

import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author df.foreroc
 */
@Stateless
public class IncidentePersistence {

    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;

    /**
     * Crea un incidente en la base de datos
     *
     * @param incidente objeto author que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public IncidenteEntity create(IncidenteEntity incidente) {
        em.persist(incidente);
        return incidente;
    }

    /**
     * Busca si hay algun incidente con el id que se envía de argumento
     *
     * @param incidenteId: id correspondiente al incidente buscado.
     * @return un incidente.
     */
    public IncidenteEntity find(Long incidenteId) {
        return em.find(IncidenteEntity.class, incidenteId);
    }

    /**
     * Devuelve todas los incidentes de la base de datos.
     *
     * @return una lista con todas los incidentes que encuentre en la base de
     * datos, "select u fromIncidenteEntity u" es como un "select * from
     * IncidenteEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<IncidenteEntity> findAll() {
        Query query = em.createQuery("select u from IncidenteEntity u");
        return query.getResultList();
    }

    /**
     * Actualiza un incidente.
     *
     * @param inciEntity: el incidente que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un incidente con los cambios aplicados.
     */
    public IncidenteEntity update(IncidenteEntity inciEntity) {
        return em.merge(inciEntity);
    }

    /**
     * Borra un incidente de la base de datos recibiendo como argumento el id
     * del incidente
     *
     * @param inciId: id correspondiente al incidente a borrar.
     */
    public void delete(Long inciId) {
        IncidenteEntity inciEntity = em.find(IncidenteEntity.class, inciId);
        em.remove(inciEntity);
    }
}
