package org.uob.a2.gameobjects;

/**
 * Represents the current state of the game, including the map and the player.
 * 
 * <p>
 * The game state contains all necessary information about the game's progress, such as
 * the player's status and the state of the game map.
 * </p>
 */
public class GameState {
    public Map map;
    public Player player;
   public int score;
   public int puzzlesSolved;
   public int roomsVisited;

    public GameState(){
        
    }

    public GameState(Map map, Player player){
        this.map = map;
        this.player = player;
        this.score = 100;
        this.roomsVisited = 0;
    }

    public Map getMap(){
        return map;
    }

    public Player getPlayer(){
        return player;
    }

    public int getScore(){
        return score + ((puzzlesSolved * 5)-(roomsVisited));
    }


    
    /**
     * Returns a string representation of the game state, including the map and player details.
     *
     * @return a string describing the game state
     */
    @Override
    public String toString() {
        return "GameState {" +
               "map=" + (map != null ? map.toString() : "null") + ", " +
               "player=" + (player != null ? player.toString() : "null") +
               '}';
    }
}
