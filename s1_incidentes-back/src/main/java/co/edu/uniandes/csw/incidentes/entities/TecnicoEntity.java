/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
>>>>>>> 46ccd71f474fc5af537b993092d44764c3cd62d3
package co.edu.uniandes.csw.incidentes.entities;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Estudiante Diana Alejandra Silva Alvarez
 */
@Entity
public class TecnicoEntity extends BaseEntity implements Serializable {
   // private long id;
    private int incidenteASignado;
    private String especialidad;
    
    public TecnicoEntity()
    {
        
    }
    
    
    public int getIncidenteAsignado()
    {
        return getIncidenteASignado();
    }
    
    public String getEspecialidad()
    {
        return especialidad;
    }
    
    
    
    public void setEspecialidad (String pEspecialidad)
    {
        especialidad = pEspecialidad;
    }

    /**
     * @return the id
     */
    //public Long getId() {
      //  return id;
    //}

    /**
     * @param id the id to set
     */
    //public void setId(int id) {
     //   this.id = id;
    //}

    /**
     * @return the incidenteASignado
     */
    public int getIncidenteASignado() {
        return incidenteASignado;
    }

    /**
     * @param incidenteASignado the incidenteASignado to set
     */
    public void setIncidenteASignado(int incidenteASignado) {
        this.incidenteASignado = incidenteASignado;
    }
}

<<<<<<< HEAD

=======
>>>>>>> 46ccd71f474fc5af537b993092d44764c3cd62d3
