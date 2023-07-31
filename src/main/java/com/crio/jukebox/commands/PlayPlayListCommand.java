package com.crio.jukebox.commands;

import com.crio.jukebox.dtos.UserSongDto;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserService;

import java.util.List;

public class PlayPlayListCommand implements  ICommand{
    private final IUserService userService;

    public PlayPlayListCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            UserSongDto playedSongDto = userService.setCurrentPlayList(tokens.get(1),tokens.get(2));
            System.out.println(playedSongDto);
        }catch (UserNotFoundException e){
            System.out.println("User Not Found");
        }catch (PlayListNotFoundException e){
            System.out.println("Playlist is empty");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}