package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {
String topic;

    public Help(String topic){
        this.commandType = CommandType.HELP;
      this.topic = topic;  
    }

    @Override
    public String toString(){
        return "HELP " + topic;
    }

    public String execute(GameState gameState){
        if(topic == null || topic.isEmpty()){
            return "Welcome to the game! Here are the available commands:\n- MOVE: Move to a different location (e.g., ’move north’).\n- LOOK: Look around the current room or inspect an object.\n- GET: Pick up an item from the current room (e.g., ’get key’).\n- DROP: Drop an item from your inventory (e.g., ’drop key’)\n- USE: Use an item in your inventory (e.g., ’use key’ or ’use key on chest').\n- STATUS: Check your current status, including inventory.(e.g. ’status player’ or ’status inventory’).\n- HELP: Display this help information or get help on a specific topic (e.g., ’help move’).\n-COMBINE: Combine two items into an equipment e.g ('combine sword and fire')\n- QUIT: Exit the game.\nExplore the game world, interact with items, and enjoy the adventure!";
        }
        switch (topic.toLowerCase()) {
            case "move":
                return "MOVE Command: Use the 'move' command Move <direction> to move in the specified direction.";
            case "get":
                return "GET Command: Use the 'get' command Get <item/equipment> to pick up an item or equipment from the current room.";
            case "status":
                return "STATUS Command: Use the 'status' to check you current status, including inventory e.g 'status player' or 'status inventory'";
            case "look":
                return "LOOK Command: Use the 'look' command to look at the current room or look at a specified feature or object in the room.";
            case "drop":
                return "DROP Command: Use the 'drop' command drop <item> to drop a specified item or equipment from you inventory";
            case "quit":
                return "QUIT Command: Use the quit command 'QUIT' to quit the game";
            case "combine":
                return "COMBINE Command: Use the combine command 'combine <item> and/with <item>' to combine two items";
            default:
                return "No help available for the topic: " + topic;
        }
    }  
}
