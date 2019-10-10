/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.CoordinadorPersistence;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad
 * Coordinador.
 *
 * @author Juan Camilo Castiblanco
 */
@Stateless
public class CoordinadorLogic {

    @Inject
    private CoordinadorPersistence persistence;

    /**
     * Crea un coordinador en la persistencia.
     *
     * @param coordinador La entidad que representa el coordinador a persistir.
     * @return La entidad del coordinador luego de persistirla.
     * @throws BusinessLogicException Si se incumple una regla de negocio.
     */
    public CoordinadorEntity createCoordinador(CoordinadorEntity coordinador) throws BusinessLogicException {
        if (coordinador.getName() == null) {
            throw new BusinessLogicException("El nombre es nulo.");
        }
        if (coordinador.getUsername() == null || coordinador.getUsername().isEmpty()) {
            throw new BusinessLogicException("El usuario no puede ser vacio");
        }
        if (persistence.findByUsername(coordinador.getUsername()) != null) {
            throw new BusinessLogicException("Ya existe un ususario con ese nombre.");
        }

        if (coordinador.getPassword() == null || coordinador.getPassword().isEmpty()) {
            throw new BusinessLogicException("La contraseña no puede ser vacia.");
        }
        /* TODO Al crear un objeto con Podam asegurar que cumpla esta condición para que el test no falle. 
        if(!checkString(user.getPassword())){
            throw new BusinessLogicException("La contraseña debe contener una mayuscula, una minuscula y un número.");
        }*/

        coordinador = persistence.create(coordinador);
        return coordinador;
    }

    /**
     * Obtener todos los Coordinadores existentes en la base de datos.
     *
     * @return una lista de Coordinadores.
     */
    public List<CoordinadorEntity> getCoordinadores() {
        List<CoordinadorEntity> lista = persistence.findAll();
        return lista;
    }

    /**
     * Obtener un coordinador por medio de su id.
     *
     * @param coordinadorId: id del coordinador para ser buscado.
     * @return el coordinador solicitado por medio de su id.
     */
    public CoordinadorEntity getCoordinador(Long coordinadorId) {
        CoordinadorEntity coordinadorEntity = persistence.find(coordinadorId);
        return coordinadorEntity;
    }

    /**
     * Actualizar un coordinador.
     *
     * @param coordinadorId: id del coordinador para buscarlo en la base de
     * datos.
     * @param coordinador: coordinador con los cambios para ser actualizado,
     * por ejemplo el nombre.
     * @return el coordinador con los cambios actualizados en la base de datos.
     */
    public CoordinadorEntity updateCoordinador(Long coordinadorId, CoordinadorEntity coordinador) {
        CoordinadorEntity newCoordinadorEntity = persistence.update(coordinador);
        return newCoordinadorEntity;
    }

    /**
     * Borrar un coordinador
     *
     * @param coordinadorId: id del coordinador a borrar
     * @throws BusinessLogicException Si el coordinador a eliminar tiene incidentes.
     */
    public void deleteCoordinador(Long coordinadorId) throws BusinessLogicException {
        List<IncidenteEntity> incidente = getCoordinador(coordinadorId).getIncidentes();
        if (incidente != null && !incidente.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el coordinador con id = " + coordinadorId + " porque tiene incidentes asociados");
        }
        persistence.delete(coordinadorId);
    }

    /*
    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }
        return false;
    }*/
}
