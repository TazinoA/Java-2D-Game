package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {
  String target;
    public Look(String target){
        this.commandType = CommandType.LOOK;
        if(target == null){
            this.target = "room";
        }
        else{
            this.target = target;
        }
    }

    @Override
    public String toString(){
       return "You looked at " + target; 
    }

    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();
try{
       if(target.equalsIgnoreCase("room")){
            StringBuilder result = new StringBuilder(currentRoom.getDescription()).append("\n");
           for(Item item: currentRoom.getItems()){
               if(item.getHidden() == false){
                    result.append(item.getDescription()).append("\n");
               }
           }
           for(Equipment equipment: currentRoom.getEquipments()){
               if(equipment.getHidden() == false){
                  result.append(equipment.getDescription()).append("\n");
               }
           }

           for(Feature feature: currentRoom.getFeatures()){
               if(feature.getHidden() == false){
                    result.append(feature.getDescription()).append("\n");
               }
           }
           for(Exit exit: currentRoom.getExits()){
               if(!exit.getHidden()){
                   result.append(exit.getDescription()).append("\n");
               }
           }

           
             if (result.length() > 0 && result.charAt(result.length() - 1) == '\n') {
    result.deleteCharAt(result.length() - 1);
}
           return result.toString().trim(); 
       }
           
        else if(target.equalsIgnoreCase("exits") || (target.equalsIgnoreCase("exit"))){
            StringBuilder result = new StringBuilder("The available exits are:\n");
           for(Exit exit: currentRoom.getExits()){
               if(exit.getHidden() == false){
                    result.append(exit.getDescription()).append("\n");
               }
           }
              if (result.length() > 0 && result.charAt(result.length() - 1) == '\n') {
    result.deleteCharAt(result.length() - 1);
}
            return result.toString().trim(); 
        }

        else if(target.equalsIgnoreCase("features")){
            StringBuilder result = new StringBuilder("You also see:\n");
           for(Feature feature: currentRoom.getFeatures()){
               if(feature.getHidden() == false){
                    result.append(feature.getDescription()).append("\n");
               }
           }
            if (result.length() > 0 && result.charAt(result.length() - 1) == '\n') {
    result.deleteCharAt(result.length() - 1);
}
            return result.toString().trim(); 
        }

            for (Item item : currentRoom.getItems()) {
                if (item.getName().equalsIgnoreCase(target) && !item.getHidden()) {
                    return item.getDescription();
                }
            }
            for (Equipment equipment : currentRoom.getEquipments()) {
                if (equipment.getName().equalsIgnoreCase(target) && !equipment.getHidden()) {
                    return equipment.getDescription();
                }
            }

        for (Item item : player.getInventory()) {
                if (item.getName().equalsIgnoreCase(target)) {
                    return item.getDescription();
                }
            }
        for (Equipment equipment : player.getEquipment()) {
                if (equipment.getName().equalsIgnoreCase(target)) {
                    return equipment.getDescription();
                }
            }
    
            return "";
        
    
    }
        catch(Exception e){
            return "look command requires a target";
        }
        
    }
    
}
