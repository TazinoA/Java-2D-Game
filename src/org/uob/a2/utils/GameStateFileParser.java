package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.uob.a2.gameobjects.*;

/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileParser {

    public GameStateFileParser(){
        
    }

    public static GameState parse(String filename){
        try{
        Player player = null;
        Map map = new Map();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        Room currentRoom = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();  
            if (line.isEmpty()) {
                continue;
            }
            String[] tokens = line.split(",");  
            String firstToken = tokens[0];

        
                 if(firstToken.startsWith("player:")){
                    player = new Player(firstToken.substring(7));
                 }
                    
                    
                if(firstToken.startsWith("room:")){
                    String roomId = firstToken.substring(5);
                    String roomName = tokens[1];
                    String roomDesc = tokens[2];
                    boolean roomHidden = Boolean.parseBoolean(tokens[3]);
                    Room room = new Room(roomId, roomName, roomDesc, roomHidden);
                    map.getRoomsMap().put(roomId, room);
                    currentRoom = room;
                     map.addRoom(room);
                  if(map.getCurrentRoom() == null){
                     map.setCurrentRoom(room.getId()); 
                }
            }
            
                 if(firstToken.startsWith("equipment:")){
                    String equipId = firstToken.substring(10);
                    String equipName = tokens[1];
                    String equipDesc = tokens[2];
                    boolean equipHidden = Boolean.parseBoolean(tokens[3]);
                    String action = tokens[4];
                    String target = tokens[5];
                    String result = tokens[6];
                    String useDesc = tokens[7];
                    Equipment equip = new Equipment(equipId, equipName, equipDesc, equipHidden, new UseInformation(false, action, target, result, useDesc));
                    if (currentRoom != null) {
                        currentRoom.addEquipment(equip); 
                    }
                 }
            
                 if(firstToken.startsWith("container:")){
                    String containerId = firstToken.substring(10);
                    String containerName = tokens[1];
                    String containerDesc = tokens[2];
                    boolean containerHidden = Boolean.parseBoolean(tokens[3]);
                    Container container = new Container(containerId, containerName, containerDesc, containerHidden);
                    if (currentRoom != null) {
                        currentRoom.addFeature(container);
                    }
                 }

             if(firstToken.startsWith("hazard:")){
                    String hazardId = firstToken.substring(7);
                    String hazardName = tokens[1];
                    String hazardDesc = tokens[2];
                    boolean hazardHidden = Boolean.parseBoolean(tokens[3]);
                    String hazardItem = tokens[4];
                    String message = tokens[5];
                    Hazard hazard = new Hazard(hazardId, hazardName, hazardDesc, hazardHidden, hazardItem, message);
                    if (currentRoom != null) {
                        currentRoom.addFeature(hazard);
                    }
                 }

            
                if(firstToken.startsWith("item:")){
                    String itemId = firstToken.substring(5);
                    String itemName = tokens[1];
                    String itemDesc = tokens[2];
                    boolean itemHidden = Boolean.parseBoolean(tokens[3]);
                    Item item = new Item(itemId, itemName, itemDesc, itemHidden);
                    if (currentRoom != null) {
                        currentRoom.addItem(item);
                    }
                
                }
                if(firstToken.startsWith("exit:")){
                    String exitId = firstToken.substring(5);
                    String exitDirection = tokens[1];
                    String exitDesc = tokens[2];
                    String nextRoomId = tokens[3];
                    boolean exitHidden = Boolean.parseBoolean(tokens[4]);
                    Exit exit = new Exit(exitId, exitDirection, exitDesc, nextRoomId, exitHidden);
                    if (currentRoom != null) {
                        currentRoom.addExit(exit);
                    }
                
                }
        }
            
        reader.close();
            
        GameState gameState = new GameState(map, player);
        return gameState;
    }
         catch (IOException e) {
            System.out.println("Error reading the game state file: " + e.getMessage());
            return null; 
        }
    }
}

