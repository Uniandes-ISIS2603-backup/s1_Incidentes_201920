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
 * Clase que maneja la persistencia para Coordinador. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author Juan Camilo Castiblanco
 */
@Stateless
public class CoordinadorPersistence {
    
    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param coordinador objeto editorial que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CoordinadorEntity create(CoordinadorEntity coordinador)
    {
        em.persist(coordinador);
        return coordinador;
    }
    
    /**
     * Busca si hay algun coordinador con el id que se envía de argumento
     *
     * @param coordinadorId: id correspondiente al coordinador buscado.
     * @return un coordinador.
     */
    public CoordinadorEntity find(Long coordinadorId)
    {
        return em.find(CoordinadorEntity.class, coordinadorId);
    }
    
    /**
     * Devuelve todos los coordinadores de la base de datos.
     *
     * @return una lista con todos los coordinadores que encuentre en la base de
     * datos, "select u from CoordinadorEntity u" es como un "select * from
     * CoordinadorEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CoordinadorEntity> findAll()
    {
        TypedQuery query=em.createQuery("select u from CoordinadorEntity u", CoordinadorEntity.class);
        return query.getResultList();
    }
    
     /**
     * Actualiza un coordinador.
     *
     * @param coordinadorEntity: el coordinador que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un coordinador con los cambios aplicados.
     */
    public CoordinadorEntity update(CoordinadorEntity coordinadorEntity) {
        return em.merge(coordinadorEntity);
    }

    /**
     * Borra un coordinador de la base de datos recibiendo como argumento el id
     * del coordinador.
     * @param coordinadorId
     */
    public void delete(Long coordinadorId) {
        CoordinadorEntity coordinadorEntity = em.find(CoordinadorEntity.class, coordinadorId);
        em.remove(coordinadorEntity);
    }
    
    /**
     * Busca si hay algun coordinador con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de usuario del coordinador que se está buscando
     * @return null si no existe ningun coordinador con el nombre del argumento.
     * Si existe alguno devuelve el primero.
     */
    public CoordinadorEntity findByUsername(String nombre){
        CoordinadorEntity respuesta;
        TypedQuery<CoordinadorEntity> query= em.createQuery("select e from CoordinadorEntity e where e.username =:name", CoordinadorEntity.class);
        query = query.setParameter("name", nombre);
        List<CoordinadorEntity> lista = query.getResultList();
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
