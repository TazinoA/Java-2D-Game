package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

import org.uob.a2.utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {
    ArrayList<Room> rooms = new ArrayList<>();
    HashMap<String, Room> roomsMap = new HashMap<>(); 
    Room currentRoom;

      public Map(){
          
      }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public void addRoom(Room room){
        if (room != null && !roomsMap.containsKey(room.getId())) {
            rooms.add(room);
            roomsMap.put(room.getId(), room);
        }  
    }

    public void setCurrentRoom(String roomId){
        if (roomsMap.containsKey(roomId)) {
            this.currentRoom = roomsMap.get(roomId);
        }
    }

    public HashMap<String, Room> getRoomsMap(){
        return this.roomsMap;
    }

    public Room getRoom(String roomId){
        return roomsMap.get(roomId);
    }









    
    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}

