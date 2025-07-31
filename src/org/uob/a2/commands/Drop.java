package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
 
/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {
   String item;
    
    public Drop(String item){
        this.commandType = CommandType.DROP;
        this.item = item;
    }


    public String execute(GameState gameState){
         Player player = gameState.getPlayer();
          Room currentRoom = gameState.getMap().getCurrentRoom();
try{
        if (player.hasItem(item) == false) {  
            if(player.hasEquipment(item)){
                Equipment equipmentDrop = player.getEquipment(item);
                player.getEquipment().remove(equipmentDrop);
        currentRoom.getEquipments().add(equipmentDrop);
        return "You drop: " + equipmentDrop.getName();  
                }
                else{
                    return "You cannot drop " + item;
                }
            }
        else{
             Item itemDrop = player.getItem(item);
             player.getInventory().remove(itemDrop);
             currentRoom.getItems().add(itemDrop);
            return "You drop: " + itemDrop.getName();  
        }
    }
        catch(Exception e){
            return "Drop command requires a target";
        }
 }
       
      @Override
   public String toString(){
        return "You dropped " + item;
   }
}
