package org.uob.a2.parser;

import java.util.ArrayList;

import org.uob.a2.commands.*;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {


    public Parser(){
        
    }

    public Command parse(ArrayList<Token> tokens) throws CommandErrorException{
        if (tokens == null || tokens.isEmpty()) {
            throw new CommandErrorException("No command entered");
        }
        TokenType tokenType = tokens.get(0).getTokenType();
        switch (tokenType) {
            case MOVE: return parseMove(tokens);
            case USE: return parseUse(tokens);
            case GET: return parseGet(tokens);
            case DROP: return parseDrop(tokens);
            case LOOK: return parseLook(tokens);
            case STATUS: return parseStatus(tokens);
            case HELP: return parseHelp(tokens);
            case QUIT: return new Quit(); 
            case COMBINE: return parseCombine(tokens);
            default: throw new CommandErrorException("Unknown command: " + tokens.get(0).getValue());
        }
    }

    Command parseCombine(ArrayList<Token> tokens) throws CommandErrorException{
        if (tokens.size() < 4 ||tokens.get(1).getTokenType() != TokenType.VAR ||tokens.get(2).getTokenType() != TokenType.PREPOSITION||tokens.get(3).getTokenType() != TokenType.VAR) {
            throw new CommandErrorException("Combine command follows 'combine <item> and/with <item>' format.");
        }
        return new Combine(tokens.get(1).getValue(), tokens.get(3).getValue());
    }
    
     Command parseMove(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() < 2) {
            throw new CommandErrorException("Move command requires a direction");
        }
        return new Move(tokens.get(1).getValue()); 
    }

    Command parseUse(ArrayList<Token> tokens) throws CommandErrorException {
        if(tokens.size() == 3){
            System.out.println(tokens.get(1).getValue());
            return new Use(tokens.get(1).getValue(), null);
        }
            
 if (tokens.size() < 4 ||tokens.get(1).getTokenType() != TokenType.VAR ||tokens.get(2).getTokenType() != TokenType.PREPOSITION||tokens.get(3).getTokenType() != TokenType.VAR) {
            throw new CommandErrorException("USE command follows 'use <equipment> on target' OR use <equipment> format.");
        }
       
        return new Use(tokens.get(1).getValue(), tokens.get(3).getValue()); 
    }

    Command parseGet(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() < 2) {
            throw new CommandErrorException("Get command requires an item name");
        }
        return new Get(tokens.get(1).getValue()); 
    }

    Command parseDrop(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() < 2) {
            throw new CommandErrorException("Drop command requires an item name");
        }
        return new Drop(tokens.get(1).getValue()); 
    }

    Command parseLook(ArrayList<Token> tokens){
        if (tokens.size() > 1) {
           return new Look(tokens.get(1).getValue()); 
        }
        return new Look("room");
    }

    Command parseStatus(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() > 1) {
            return new Status(tokens.get(1).getValue()); 
        }
        return new Status(null); 
    }

    Command parseHelp(ArrayList<Token> tokens){
        if (tokens.size() > 1) {
           return new Help(tokens.get(1).getValue());
        }
        return new Help(null); 
    }
}
