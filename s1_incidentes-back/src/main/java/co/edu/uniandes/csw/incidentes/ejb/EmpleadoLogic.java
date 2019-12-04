/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.EmpleadoPersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *Clase que implementa la conexion con la persistencia para la entidad
 * empleado.
 * 
 * @author Julian Jaimes
 */

@Stateless
public class EmpleadoLogic {
    
    @Inject
    private EmpleadoPersistence persistence;
    
    /**
     * Crea un empleado en la persistencia.
     *
     * @param empleado La entidad que representa el empleado a persistir.
     * @return La entidad del empleado luego de persistirla.
     * @throws BusinessLogicException Si se incumple una regla de negocio.
     */
    public EmpleadoEntity createEmpleado(EmpleadoEntity empleado) throws BusinessLogicException{
        
        if(empleado.getNombre() == null || empleado.getNombre().equals("")){
            throw new BusinessLogicException("El nombre es nulo o vacio");
        }
        if(empleado.getNumIncidentes() < 0){
            throw new BusinessLogicException("El número de incidentes no puede ser menor a cero");
        }
        if(empleado.getUsername()==null || empleado.getUsername().isEmpty())
        {
            throw new BusinessLogicException("El usuario no puede ser nulo");
        }
        if(persistence.findByUsername(empleado.getUsername()) != null){
            throw new BusinessLogicException("Ya existe un ususario con ese nombre.");
        }
        if(empleado.getPassword()==null || empleado.getPassword().isEmpty())
        {
            throw new BusinessLogicException("La contraseña no puede ser vacia.");
        }
        
        
        empleado = persistence.create(empleado);
        return empleado;
    }
    
    /**
     * Obtener todos los Empleados existentes en la base de datos.
     *
     * @return una lista de Empleados.
     */
    public List<EmpleadoEntity> getEmpleados() {
        return persistence.listAll();
        
    }
    
    /**
     * Obtener un empleado por medio de su id.
     *
     * @param empleadoId: id del empleado para ser buscado.
     * @return el empleado solicitado por medio de su id.
     */
    public EmpleadoEntity getEmpleado(Long empleadoId) {
        return persistence.find(empleadoId);
       
    }
    
    /**
     * Actualizar un empleado.
     *
     * @param empleadoId: id del empleado para buscarlo en la base de
     * datos.
     * @param empleado: empleado con los cambios para ser actualizado,
     * por ejemplo el nombre.
     * @return el empleado con los cambios actualizados en la base de datos.
     */
    public EmpleadoEntity updateEmpleado( long empleadoId,EmpleadoEntity empleado) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el empleado con id = {0}", empleadoId);
        EmpleadoEntity newEmpleadoEntity = persistence.update(empleado);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el empleado con id = {0}", empleado.getId());
        return newEmpleadoEntity;
    }
    
    /**
     * Borrar un empleado
     *
     * @param empleadoId: id del emleado a borrar
     */
    public void deleteEmpleado(Long empleadoId){
        persistence.delete(empleadoId);
    }
    
   
    
}
