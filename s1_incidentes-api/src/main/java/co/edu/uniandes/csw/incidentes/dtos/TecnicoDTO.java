
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import co.edu.uniandes.csw.incidentes.entities.TecnicoEntity;
import java.io.Serializable;

/**
 *
 * @author da.silvaa
 */
public class TecnicoDTO extends UserDTO implements Serializable{
       
    private Long id;
    
    private int numCasos; 
    
   
    
     public TecnicoDTO(TecnicoEntity coordinadorEntity) {
       
           super.setId(coordinadorEntity.getId());
           super.setPassword(coordinadorEntity.getPassword());
           super.setUsername(coordinadorEntity.getUsername());
           this.numCasos=coordinadorEntity.getNumCasos();
          
        
    }
    
    public TecnicoEntity toEntity() 
    {
        TecnicoEntity coordinadorEntity = new TecnicoEntity();
        coordinadorEntity.setUsername(this.getUsername());
        coordinadorEntity.setPassword(this.getPassword());
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