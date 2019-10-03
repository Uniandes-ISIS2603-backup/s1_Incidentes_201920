/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.dtos;

import co.edu.uniandes.csw.incidentes.entities.IncidenteEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author df.foreroc
 */
public class IncidenteDetailDTO extends IncidenteDTO implements Serializable{
   // private List<ActuacionDTO> actuaciones;
    
    public IncidenteDetailDTO()
    {
        super();
    }
    public IncidenteDetailDTO(IncidenteEntity entidad)
    {
        super(entidad);
        if(entidad!=null && entidad.getEquipo()!=null
                //entidad.getActuaciones()!=null
                )
        {
            // actuaciones= new ArrayList<>();
            //for(ActuacionEntity actuacion: entidad.getActuaciones()){actuaciones.add(new ActuacionDTO()actuacion)}
        }
    }
    public IncidenteEntity toEntity()
    {
        IncidenteEntity entidad=super.toEntity();
        //if(actuaciones!=null){
        //List<ActuacionEntity> actuacionesEntity=new ArrayList<>();
        //for(ActuacionDTO act: actuaciones){actuacionesEntity.add(act.toEntity())}
        // entidad.setActuaciones(actuacionesEntity);}
        return entidad;     
    }
    /**
     * @return the actuaciones
     */
    //public List<ActuacionDTO> getActuaciones() {
      //  return actuaciones;
    //}

    /**
     * @param actuaciones the actuaciones to set
     */
   // public void setActuaciones(List<ActuacionDTO> actuaciones) {
     //   this.actuaciones = actuaciones;
    //}
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
