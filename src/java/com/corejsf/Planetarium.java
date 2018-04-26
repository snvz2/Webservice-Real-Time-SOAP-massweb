package com.corejsf;

import java.io.Serializable;
import javax.inject.Named;
// fails here
//import javax.faces.bean.ManagedBean; // or
// or import javax.enterprise.context.RequestScoped; 
import javax.faces.bean.RequestScoped; // or

// fails here
@Named // or @ManagedBean
@RequestScoped
public class Planetarium implements Serializable {
  private String selectedPlanet;
  
  public String getSelectedPlanet() { return selectedPlanet; }
  
  public String changePlanet(String newValue) {
     selectedPlanet = newValue;
     return selectedPlanet;
  }
}
