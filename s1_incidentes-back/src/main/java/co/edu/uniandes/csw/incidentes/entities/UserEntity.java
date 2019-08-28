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
public class UserEntity extends BaseEntity implements Serializable{
    
    private String username;
    
    private String password;
    
    public String getUsername(){
    return username;
    }
    
    public String getPassword(){
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String pPassword){
        this.password = pPassword;
    }
    
    
}
