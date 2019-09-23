/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.ejb;

import co.edu.uniandes.csw.incidentes.entities.EquipoEntity;
import co.edu.uniandes.csw.incidentes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidentes.persistence.EquipoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author Julian Jaimes
 */
@Stateless
public class EquipoLogic {
    
    @Inject
    private EquipoPersistence pEquipo;
    
    public EquipoEntity createEquipo(EquipoEntity equipo) throws BusinessLogicException {
        if(equipo.getIdEquipo() < 0) {
            throw new BusinessLogicException("El id del equipo no es válido");
        }
        equipo = pEquipo.create(equipo);
        return equipo;
    }
    
    public EquipoEntity findEquipo(Long equiposId) {
        return pEquipo.find(equiposId);
    }
    
    public List<EquipoEntity> findAllEquipos() {
        return pEquipo.findAll();
    }
    
    public EquipoEntity updateEquipo(Long equipoID,EquipoEntity equipoE) throws BusinessLogicException {
        if(equipoE.getIdEquipo() < 0) {
            throw new BusinessLogicException("El id del equipo no es válido");
        }
        equipoE = pEquipo.update(equipoE);
        return equipoE;
    }
    
    public void deleteEquipo(Long equipoId) {
        pEquipo.delete(equipoId);
    }
}
