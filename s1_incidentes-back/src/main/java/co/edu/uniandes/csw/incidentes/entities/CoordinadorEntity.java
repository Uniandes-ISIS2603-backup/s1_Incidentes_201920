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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Juan Camilo Castiblanco
 */
@Entity
public class CoordinadorEntity extends User2Entity implements Serializable{
    
    private String name;
    
    @PodamExclude
    @OneToMany(mappedBy = "coordinador")
    private List<TecnicoEntity> tecnicos = new ArrayList<TecnicoEntity>();

    public CoordinadorEntity() {
    }
    
    public String getName(){
    return name;
    }
    
    public void setName(String pName){
        this.name = pName;
    }

    public List<TecnicoEntity> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(List<TecnicoEntity> tecnicos) {
        this.tecnicos = tecnicos;
    }
}