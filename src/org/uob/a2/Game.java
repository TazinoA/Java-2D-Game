package org.uob.a2;

import java.util.Scanner;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {
    GameState gameState;
    Parser parser;
    Scanner scanner;
    Tokeniser tokeniser;

    public Game(){
        parser = new Parser();
        scanner = new Scanner(System.in);
        tokeniser = new Tokeniser();
    }
    
     public static void main(String[] args) {
         Game game = new Game();
         game.setup("C:\\Users\\user\\OneDrive\\Desktop\\projects\\projects\\src\\org\\uob\\a2\\game.txt"); 
         game.start();
    }

     public void setup(String file) {
        gameState = GameStateFileParser.parse(file);
    }

    public void start(){
         System.out.println("Welcome to the game.");

         while(true){
        try{
            System.out.print(">> ");
          tokeniser.tokenise(scanner.nextLine().trim());
              Command command = parser.parse(tokeniser.getTokens());
              System.out.println(command.execute(gameState));
             if (command instanceof Quit) {
                    return;
                }    
        }
             catch (CommandErrorException e) {
                System.out.println(e.getMessage());
            }
       }    
    }
}
