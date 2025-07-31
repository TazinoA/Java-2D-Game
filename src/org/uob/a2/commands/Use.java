package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command {
    String equipmentName;
    String target;

    public Use(String equipmentName, String target){
        this.commandType = CommandType.USE;
        this.equipmentName = equipmentName;
        this.target = target;
    }

    @Override
    public String toString(){
        return "You used " + equipmentName + " on " + target;
    }

    public String execute(GameState gameState){
         Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();
try{
    
        if(player.hasEquipment(equipmentName) || player.hasItem(equipmentName)){
            Equipment equipment = player.getEquipment(equipmentName);

             if(target == null || target.equalsIgnoreCase("room")){
                return equipment.use(currentRoom, gameState);
          }
            
            if(equipment.getUseInformation().isUsed()){
                return "You have already used " + equipmentName;
            }

            if(currentRoom.hasItem(target) && !currentRoom.getItemByName(target).getHidden()){
                return equipment.use(currentRoom.getItemByName(target), gameState);
            }

            if(player.hasItem(target)){
                return equipment.use(player.getItem(target), gameState);
            }

            
                if(currentRoom.hasFeature(target) && !currentRoom.getFeatureByName(target).getHidden()){
                    return equipment.use(currentRoom.getFeatureByName(target), gameState);
                }


                    
                 return "Invalid use target";
                 
        }
        
        return "You do not have " + equipmentName;
    }
        catch(Exception e){
            return "use command follows 'use <equipment> on <target>' or use <equipment> format";
        }
    }
   
}
