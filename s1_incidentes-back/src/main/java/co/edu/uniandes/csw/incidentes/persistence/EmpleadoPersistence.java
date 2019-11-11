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
 * Clase que maneja la persistencia para Empleado. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * 
 * @author Julian Jaimes
 */

@Stateless
public class EmpleadoPersistence {
    
    @PersistenceContext(unitName = "incidentesPU")
    protected EntityManager em;
    
     /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param empleado objeto editorial que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EmpleadoEntity create(EmpleadoEntity empleado){
        em.persist(empleado);
        return empleado;
    }
    
    /**
     * Busca si hay algun empleado con el id que se envía de argumento
     *
     * @param empleadoId: id correspondiente al empleado buscado.
     * @return un empleado.
     */
    public EmpleadoEntity find(Long empleadoId){
        
        return em.find(EmpleadoEntity.class,empleadoId);
    }
    
    /**
     * Devuelve todos los empleados de la base de datos.
     *
     * @return una lista con todos los empleados que encuentre en la base de
     * datos, "select u from EmpleadoEntity u" es como un "select * from
     * EmpleadoEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<EmpleadoEntity> listAll(){
        TypedQuery<EmpleadoEntity>  query = em.createQuery("select u from EmpleadoEntity u", EmpleadoEntity.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza un empleado.
     *
     * @param inciEntity: el empleado que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un empleado con los cambios aplicados.
     */
    public EmpleadoEntity update(EmpleadoEntity inciEntity) {
        return em.merge(inciEntity);
    }

    /**
     * Borra un empleado de la base de datos recibiendo como argumento el id
     * del empleado.
     * @param inciId Id del empleado
     */
    public void delete(Long inciId) {
        EmpleadoEntity inciEntity = em.find(EmpleadoEntity.class, inciId);
        em.remove(inciEntity);
    }
    
    /**
     * Busca si hay algun empleado con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de usuario del empleado que se está buscando
     * @return null si no existe ningun empleado con el nombre del argumento.
     * Si existe alguno devuelve el primero.
     */
    public EmpleadoEntity findByUsername(String nombre){
        EmpleadoEntity respuesta;
        TypedQuery<EmpleadoEntity> query= em.createQuery("select e from EmpleadoEntity e where e.username =:name", EmpleadoEntity.class);
        query = query.setParameter("name", nombre);
        List<EmpleadoEntity> lista = query.getResultList();
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
