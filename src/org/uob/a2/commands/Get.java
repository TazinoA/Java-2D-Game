package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {
  String item;
    public Get(String item){
       this.commandType = CommandType.GET;
       this.item = item;
   }

    

    public String execute(GameState gameState){
          Player player = gameState.getPlayer();
          Room currentRoom = gameState.getMap().getCurrentRoom();
try{
         Item itemGet = currentRoom.getItemByName(item);
        
       
        if (itemGet == null) {  
             Equipment equipmentGet = currentRoom.getEquipmentByName(item);
            if(equipmentGet != null ){
                if(equipmentGet.getHidden()){
                    return "No " + item + " to get.";
                }
                if(player.getEquipment().contains(equipmentGet) == false){
                player.getEquipment().add(equipmentGet);
        currentRoom.getEquipments().remove(equipmentGet);
        return "You pick up: " + equipmentGet.getName();  
                }
                else{
                    return "You already have " + equipmentGet.getName();
                }
            }
            else{
                return "No " + item + " to get.";
            }
        }
        else{
            if(itemGet.getHidden()){
                return "No " + item + " to get.";
            }
            
            if(player.getInventory().contains(itemGet) == false){
           player.getInventory().add(itemGet);
        currentRoom.getItems().remove(itemGet);
        return "You pick up: " + itemGet.getName();  
            }
            else{
                return "You already have " + itemGet.getName();
            }
        }
    }
        catch(Exception e){
            return "Get commmand requires a target";
        }
    }

    
    @Override
    public String toString(){
        return "You took " + item;
    }
}


   

