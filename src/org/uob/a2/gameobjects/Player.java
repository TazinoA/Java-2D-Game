package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry items and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {
   public String name;
   ArrayList<Item> inventory;
   ArrayList<Equipment> equipment;
    public int startPosX;
   public int startPosY;
    public int hp;

    public Player(String name){
        this.name = name;
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        startPosX = 6;
        startPosY =4;
        this.hp = 20;
    }
    
    public Player(){
        
    }

    

    public String getName(){
        return this.name;
    }

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    public boolean hasItem(String itemName){
       for (Item item : inventory) {
        if (item.getName().equals(itemName)) {
            return true;
        }
    }
    return false;
    }

    public Item getItem(String itemName){
       for (Item item : inventory) {
        if (item.getName().equals(itemName)) {
            return item; 
        }
    }
    return null;
    }

    public void addItem(Item item){
        this.inventory.add(item);
    }

    public ArrayList<Equipment> getEquipment(){
        return this.equipment;
    }

    public boolean hasEquipment(String equipmentName){
        for (Equipment equipment : equipment) {
        if (equipment.getName().equals(equipmentName)) {
            return true;
        }
    }
    return false;
    }

    public Equipment getEquipment(String equipmentName){
        for (Equipment equipment : equipment) {
        if (equipment.getName().equals(equipmentName)) {
            return equipment;
        }
    }
    return null;
    }

    public void addEquipment(Equipment equipment){
        this.equipment.add(equipment);
    }

    public int getStartPosX(){
        return startPosX;
    }
    public int getStartPosY(){
        return startPosY;
    }

    

    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}
