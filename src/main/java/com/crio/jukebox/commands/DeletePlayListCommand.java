package com.crio.jukebox.commands;

import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserService;

import java.util.List;

public class DeletePlayListCommand implements ICommand{

    private final IUserService userService;

    public DeletePlayListCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            userService.deletePlayList(tokens.get(1),tokens.get(2));
            System.out.println("Delete Successful");
        }catch (UserNotFoundException e){
            System.out.println("User Not Found!!! Try Again Later\n");
        }catch (PlayListNotFoundException e){
            System.out.println("Playlist Not Found\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}