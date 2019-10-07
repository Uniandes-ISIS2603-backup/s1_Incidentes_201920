/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Estudiante Diana Alejandra Silva Alvarez
 */
@Entity
public class TecnicoEntity extends BaseEntity implements Serializable {
   // private long id;
    private int incidenteASignado;
    private String especialidad;
    
    @PodamExclude
    @ManyToOne
    private CoordinadorEntity coordinador;
    @PodamExclude
    @OneToMany(mappedBy = "tecnico",fetch=FetchType.LAZY)
    private List<IncidenteEntity> incidentes = new ArrayList<IncidenteEntity>();
    
    public TecnicoEntity()
    {
        
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

    public CoordinadorEntity getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(CoordinadorEntity coordinador) {
        this.coordinador = coordinador;
    }
    
    
}

