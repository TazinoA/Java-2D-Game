package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
   protected UseInformation useInformation;

  public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation){
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }
    
    public String getName(){
         return this.name;
     }
    
 public UseInformation getUseInformation(){
        return this.useInformation;
    }
    
   public void setUseInformation(UseInformation useInfo){
        this.useInformation = useInfo;
    }

    public String use(GameObject target, GameState gameState){
      if (useInformation.isUsed()) {
        return  this.name  + " has already been used.";
    }

        
        if(useInformation.getTarget().equals(target.getId())){
            Room currentRoom = gameState.getMap().getCurrentRoom();
            
        if(currentRoom.hasFeature(target.getName())){
              for(Item item: currentRoom.getItems()){
               if(item.getHidden() && useInformation.getResult().equals(item.getId())){
                    item.setHidden(false);
                    }
                }

            for(Equipment equipment: currentRoom.getEquipments()){
               if(equipment.getHidden() && useInformation.getResult().equals(equipment.getId())){
                    equipment.setHidden(false);
                    }
                }
        }

            for(Exit exit: currentRoom.getExits()){
               if(exit.getHidden() && useInformation.getResult().equals(exit.getId())){
                    exit.setHidden(false);
                    }
                }

             for(Feature feature: currentRoom.getFeatures()){
               if(feature.getHidden() && useInformation.getResult().equals(feature.getId())){
                    feature.setHidden(false);
                    }
                }
            
            
    
            useInformation.setUsed(true);
            gameState.puzzlesSolved++;
            return useInformation.getMessage();
        }

            
        else{
            return this.name + " cannot be used on " + target.getName();
        }

}


    
    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }
}
