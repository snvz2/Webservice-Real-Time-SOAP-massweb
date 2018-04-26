package com.corejsf;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.inject.Named; 

// fails here
   // or import javax.faces.bean.ManagedBean;
//import javax.enterprise.context.SessionScoped; 
   // or import javax.faces.bean.SessionScoped;
import javax.faces.bean.SessionScoped;
@ManagedBean(name="user") // @Named("user") // or   
@SessionScoped     
public class User implements Serializable {
  private String name = "MASS_USER";
  private String password = "password";
  
  public String getName() { return name; }
  public void setName(String newValue) { this.name = newValue; }
    
  public String getPassword() { return password; }
  public void setPassword(String newValue) { this.password = newValue; } 
  
      public String converted() { // static
        try {
            System.out.println("User login OK");
            return "account"; // outcome XHML page
        } catch (Exception ex) {
            System.out.println("User login failed");
            return "login";
        }
      }
}
