package org.uob.a2.gameobjects;

public class Hazard extends Feature {
String item;
    String message;
     public Hazard(String id, String name, String description, boolean hidden, String item, String message){
          super(id, name, description, hidden);
         this.item = item;
         this.message = message;
      }

public String getItem(){
    return this.item;
  }
    public String getMessage(){
        return this.message;
    }
    
}
