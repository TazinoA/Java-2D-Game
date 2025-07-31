package org.uob.a2.commands;
import java.util.ArrayList;
import org.uob.a2.gameobjects.*;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {
    String topic;

  public Status(String topic){
      this.commandType = CommandType.STATUS;
      this.topic = topic;
  }

    @Override
    public String toString(){
        return "STATUS "+ topic;
    }

    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();
try{
        switch (topic.toLowerCase()) {
            case "inventory":
                return inventoryStatus(player);
            case "player":
                return playerStatus(player);
            case "map":
                return mapStatus(gameState);
            case "score":
                return scoreStatus(gameState);
            case "hp":
                return "Your current hp is: " + Integer.toString(gameState.getPlayer().hp);
            default: break;  
        }
       if(player.hasItem(topic)){
        StringBuilder result = new StringBuilder();
        for(Item item: player.getInventory()){
            if(topic.equalsIgnoreCase(item.getName())){
                result.append(item.getDescription());
            }
        }
        return result.toString();
       }
        else if(player.hasEquipment(topic)){
            StringBuilder result = new StringBuilder();
        for(Equipment equipment: player.getEquipment()){
            if(topic.equalsIgnoreCase(equipment.getName())){
                result.append(equipment.getDescription());
            }
        }
        return result.toString();
        }

        return "";
    }
        catch(Exception e){
            return "Status command requires a topic e.g 'status player'";
        }
    }

  String inventoryStatus(Player player){
      StringBuilder result = new StringBuilder("You have the following equipment:\n");
      for(Equipment equipment: player.getEquipment()){
          result.append(equipment.getName() +"\n");
      }
      result.append("You have the following items:\n");
      for(Item item: player.getInventory()){
          result.append(item.getName() + "\n");
      }
        if (result.length() > 0 && result.charAt(result.length() - 1) == '\n') {
    result.deleteCharAt(result.length() - 1);
}
      return result.toString();
  }


    String playerStatus(Player player){
        StringBuilder result = new StringBuilder("Player Name: "+ player.getName() + "\nInventory:\n");
        for(Item item: player.getInventory()){
          result.append(item.getName() + "\n");
      }
        result.append("Equipment:\n");
        for(Equipment equipment: player.getEquipment()){
          result.append(equipment.getName() +"\n");
      }
        if (result.length() > 0 && result.charAt(result.length() - 1) == '\n') {
    result.deleteCharAt(result.length() - 1);
}
        return result.toString();
    }

    String mapStatus(GameState gameState){
        Room currentRoom = gameState.getMap().getCurrentRoom();
        Player player = gameState.getPlayer();
        ArrayList<ArrayList<String>> map = new ArrayList<>();
        for (int i = 0; i < 10; i++) { 
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) { 
                row.add("[  ]");
            }
            map.add(row);
        }
        

map.get(player.startPosX).set(player.startPosY, "[P ]");
      
        for (Exit exit : currentRoom.getExits()) {
        if (!exit.getHidden()) { 
             int newX = player.getStartPosX(); 
            int newY = player.getStartPosY(); 
            switch (exit.getName().toLowerCase()) {
                case "north":
                    newX--; 
                    break;
                case "south":
                    newX++; 
                    break;
                case "east":
                    newY++;
                    break;
                case "west":
                    newY--; 
                    break;
            }
            map.get(newX).set(newY, "[" + gameState.getMap().getRoom(exit.getNextRoom()).getId() + "]");
        }
    }
        
        StringBuilder display = new StringBuilder();
        for (ArrayList<String> row : map) { 
            for (String str : row) { 
                display.append(str);
            }
            display.append("\n");
        }
        
return display.toString();
    }


    String scoreStatus(GameState gameState){
        return "Your current score is: " + Integer.toString(gameState.getScore());
    }
    
}
