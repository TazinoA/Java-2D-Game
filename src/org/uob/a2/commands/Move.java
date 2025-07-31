package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {  
    String direction;

    public Move(String direction){
        this.commandType = CommandType.MOVE;
        this.direction = direction;
    }

    @Override
    public String toString(){
        return "You moved " + direction;
    }

    public String execute(GameState gameState){
         Room currentRoom = gameState.getMap().getCurrentRoom();
        Player player = gameState.getPlayer();
        try{
            for(Exit exit: currentRoom.getExits()){
                if(!exit.getHidden() && exit.getName().equalsIgnoreCase(direction)){
                    gameState.getMap().setCurrentRoom(exit.getNextRoom());
                    gameState.roomsVisited++;
                    if (direction.equalsIgnoreCase("north")) {
                    player.startPosX--;
                } else if (direction.equalsIgnoreCase("south")) {
                    player.startPosX++;
                } else if (direction.equalsIgnoreCase("east")) {
                    player.startPosY++;
                } else if (direction.equalsIgnoreCase("west")) {
                   player.startPosY--;
                }
                    for(Feature feature: gameState.getMap().getCurrentRoom().getFeatures()){
                        if(feature instanceof Hazard){
                            Hazard hazard = (Hazard) feature;
                            if(!player.hasItem(hazard.getItem()) && !hazard.getHidden()){
                            if(player.hp <= 0){
                                gameState.roomsVisited += 5;
                            } else{
                                player.hp -= 5;
                                }
                                System.out.println("You do not have the item to counter the hazard in this room, Your hp has reduced");
                            }else{
                                System.out.println(hazard.getMessage());
                                hazard.setHidden(true);
                                player.getInventory().remove(player.getItem(hazard.getItem()));
                            }
                        }
                    }
                    
                     return "Moving towards " + direction + "\n";
                }
            }
        
       return "No exit found in that direction.";
        
    }
        catch(Exception e){
            return "Move command requires a direction";
        }
    }
  
}
