package org.uob.a2.commands;
import java.util.HashMap;
import org.uob.a2.gameobjects.*;

public class Combine extends Command{
    String item1;
    String item2;
    HashMap<String, Equipment> combinations;
    
    public Combine(String item1, String item2){
        this.commandType = CommandType.COMBINE;
        this.item1 = item1;
        this.item2 = item2;
        combinations = new HashMap<>();
        combinations.put("woodsteel", new Equipment("h1", "hammer", "A powerful hammer.", false,new UseInformation(false, "smash", "rb2", "e3", "You smash the magic crystal into pieces, the shockwave makes an opening in the wall.")));
         combinations.put("steelwood", new Equipment("h1", "hammer", "A powerful hammer.", false,new UseInformation(false, "smash", "rb2", "e3", "You smash the magic crystal into pieces, the shockwave makes an opening in the wall.")));
         combinations.put("emeraldsilver", new Equipment("n1", "necklace", "An enchanted necklace.", false,new UseInformation(false, "shine", "r5", "i3", "You placed the necklace on the statue and reveal hidden items.")));
          combinations.put("emeraldsilver", new Equipment("n1", "necklace", "An enchanted necklace.", false,new UseInformation(false, "shine", "r5", "i3", "You placed the necklace on the statue and reveal hidden items.")));
    }

    public String execute(GameState gameState){
        try{
            Player player = gameState.getPlayer();
        if(player.hasItem(item1) && player.hasItem(item2)){
            return combine(player, player.getItem(item1), player.getItem(item2));
        }

        return "You do not have both items to combine";
    
        }
        catch(Exception e){
            return "COMBINE command follows 'combine <item> and <item>' format";
        }
    }

    public String combine(Player player, Item item1, Item item2){
        if(!combinations.containsKey(this.item1+this.item2)){
            return "You cannot combine those items";
        }
        player.getInventory().remove(item1);
        player.getInventory().remove(item2);
        player.getEquipment().add(combinations.get(this.item1+this.item2));
       
        return "You combined " + this.item1 + " and " + this.item2 + " into " + combinations.get(this.item1+this.item2).getName();
    }
}