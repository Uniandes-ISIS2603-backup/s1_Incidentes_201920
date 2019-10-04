/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.CoordinadorEntity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Juan Camilo Castiblanco
 */
public class CoordinadorDetailDTO extends CoordinadorDTO implements Serializable {
    
    //private list <TecnicoDTO> tecnicos;

    public CoordinadorDetailDTO() {
        super();
    }
    
     public CoordinadorDetailDTO(CoordinadorEntity coordinadorEntity) {
        super(coordinadorEntity);
        if (coordinadorEntity.getTecnicos()!= null) {
            //tecnicos = new ArrayList<>();
            //for (TecnicoEntity entityTecnico : coordinadorEntity.getTecnicos()) {
            //    tecnicos.add(new TecnicoDTO(entityTecnico));
            //}
        }
    }
     
    @Override
    public CoordinadorEntity toEntity() {
        CoordinadorEntity coordinadorEntity = super.toEntity();
        /*
        if (tecnicos != null) {
            List<TecnicoEntity> tecnicoEntity = new ArrayList<>();
            for (TecnicoDTO dtoTecnico : getTecnicos()) {
                tecnicoEntity.add(dtotecnico.toEntity());
            }
            coordinadorEntity.setTecnicos(tecnicoEntity);
        }
        */
        
        return null;//corrdinadorEntity;
    }

    /*
     public <TecnicoDTO> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(<TecnicoDTO> tecnicos) {
        this.tecnicos = tecnicos;
    }
    */
   
}
