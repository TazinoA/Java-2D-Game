package org.uob.a2.parser;

import java.util.ArrayList;

/**
 * The {@code Tokeniser} class is responsible for converting a string input into a list of tokens
 * that can be parsed into commands by the game.
 * 
 * <p>
 * The tokeniser identifies keywords, variables, and special symbols, assigning each a {@code TokenType}.
 * </p>
 */
public class Tokeniser {
    ArrayList<Token> tokens;


    public Tokeniser(){
        tokens = new ArrayList<>();
    }

    public ArrayList<Token> getTokens(){
        return tokens;
    }

    public String sanitise(String s){
         if (s == null) {
            return "";
        }
        return s.trim().toLowerCase();
    }

    public void tokenise(String s){
         tokens.clear();
        String input = sanitise(s);
        String[] splits = input.split("\\s+");
        for (String split : splits) {
            tokens.add(new Token(tokenType(split), split));
        }
        tokens.add(new Token(TokenType.EOL));
    }

    TokenType tokenType(String tokenValue) {
        switch (tokenValue) {
            case "use": return TokenType.USE;
            case "get": return TokenType.GET;
            case "drop": return TokenType.DROP;
            case "look": return TokenType.LOOK;
            case "status": return TokenType.STATUS;
            case "help": return TokenType.HELP;
            case "quit": return TokenType.QUIT;
            case "move": return TokenType.MOVE;
            case "combine": return TokenType.COMBINE;
            case "on": return TokenType.PREPOSITION;
            case "with": return TokenType.PREPOSITION;
            case "and": return TokenType.PREPOSITION;
            default: return TokenType.VAR;
        }
    }
   
}
