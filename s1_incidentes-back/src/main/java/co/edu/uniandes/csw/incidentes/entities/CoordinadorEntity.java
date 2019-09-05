/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidentes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
/**
 *
 * @author Juan Camilo Castiblanco
 */
@Entity
public class CoordinadorEntity extends BaseEntity implements Serializable{
    
    private String name;

    public CoordinadorEntity() {
    }
    
    public String getName(){
    return name;
    }
    
    public void setName(String pName){
        this.name = pName;
    }
}