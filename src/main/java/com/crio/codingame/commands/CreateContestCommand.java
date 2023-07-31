package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.exceptions.QuestionNotFoundException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.services.IContestService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IContestService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","CRIODO2_CONTEST","LOW Monica","40"]
    // or
    // ["CREATE_CONTEST","CRIODO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
        Contest contest = null;
        Integer numQuestions = null;
        Level level = null;
        
        if(tokens.get(2).equals("HIGH")){
            level = Level.HIGH;
        }
        else if(tokens.get(2).equals("MEDIUM")){
            level = Level.MEDIUM;
        }
        else if(tokens.get(2).equals("LOW")){
            level = Level.LOW;
        }

        if(tokens.size()==5){
            try{
                numQuestions = Integer.parseInt(tokens.get(4));
            }catch(Exception exception){
                numQuestions = null;
            }
        }
        
        try{
            contest = contestService.create(tokens.get(1), level, tokens.get(3), numQuestions);
        }catch(UserNotFoundException exception){
            System.out.println(exception.getMessage());
            return;
        }catch(QuestionNotFoundException exception){
            System.out.println(exception.getMessage());
            return;
        }

        System.out.println(contest);
    }
    
}
