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
public class CoordinadorEntity {
    
    private Integer id;
    
    public int getId(){
    return id;
    }
    
    public void setId(int pId){
        this.id = pId;
    }
}


