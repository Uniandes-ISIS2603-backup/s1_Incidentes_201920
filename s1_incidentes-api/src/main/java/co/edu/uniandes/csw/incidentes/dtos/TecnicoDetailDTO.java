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
 * @author da.silvaa
 */
public class TecnicoDetailDTO extends TecnicoDTO implements Serializable {

    /**
     *
     * @param x
     */
    public TecnicoDetailDTO(TecnicoEntity x) {
        super(x);
    }
    public TecnicoDetailDTO()
    {
        
    }
}



