/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import java.io.Serializable;

/**
 *
 * @author xx
 */
public class TecnicoDTO implements Serializable {

    public TecnicoDTO() {

    }

    public TecnicoDTO(TecnicoEntity tecnicoEntity) {
        if (tecnicoEntity != null) {

        }
    }

    public TecnicoEntity toEntity() {
        TecnicoEntity tecnicoEntity = new TecnicoEntity();
        
        return tecnicoEntity;
    }
}
