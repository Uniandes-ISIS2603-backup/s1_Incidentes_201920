<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Estudiante
 */
public class TecnicoDTO {
       
    private Long id;
    
    private int numCasos; 
    
    public TecnicoDTO() {
        
    }
    
     public TecnicoDTO(TecnicoEntity coordinadorEntity) {
        if (coordinadorEntity != null) {
           this.id = coordinadorEntity.getId();
          
        }
    }
    
    public TecnicoEntity toEntity() {
        TecnicoEntity coordinadorEntity = new TecnicoEntity();
        coordinadorEntity.setId(this.id);
        coordinadorEntity.setNumCasos(this.numCasos);
        return coordinadorEntity;
    }

    public Long getId() {
        return id;
    }

    public int getNumCasos() {
        return numCasos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumCasos(int nombre) {
        this.numCasos= nombre;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
=======
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
>>>>>>> 79b9c42217a1f64487bcff923c0a08dfe3d94bba
