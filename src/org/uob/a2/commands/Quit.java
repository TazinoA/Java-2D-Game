package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command {

    public Quit(){
        this.commandType = CommandType.QUIT;
    }

    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();
        
        String message = "Game over: Your current status is:\n";
        message += "Player Name: " + player.getName() + "\n";
        message += "Inventory:\n";
        if (player.getInventory().isEmpty() == false) {
            for (Item item : player.getInventory()) {
                message += " " + item.getDescription() + "\n";  
            }
        } else {
            message += "  (No items)\n";
        }

        message += "Equipment:\n";
        if (player.getEquipment().isEmpty() == false) {
            for (Equipment equipment : player.getEquipment()) {
                message += " " + equipment.getDescription() + "\n";  
            }
        } else {
            message += "  (No equipment)\n";
        }
        if (message.endsWith("\n")) {
    message = message.substring(0, message.length() - 1);
}
        return message;
    } 
}
