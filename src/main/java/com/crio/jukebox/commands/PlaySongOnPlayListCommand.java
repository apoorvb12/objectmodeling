package com.crio.jukebox.commands;

import com.crio.jukebox.dtos.UserSongDto;
import com.crio.jukebox.entities.SongOrder;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserService;

import java.util.List;

public class PlaySongOnPlayListCommand implements ICommand{
    private final IUserService userService;

    public PlaySongOnPlayListCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            UserSongDto playedSongDto;
            if(!tokens.get(2).equals("BACK") && !tokens.get(2).equals("NEXT")){
                playedSongDto = userService.playSongById(tokens.get(1),tokens.get(2));
            }else if(tokens.get(2).equals("BACK")){
               playedSongDto = userService.playSongByOrder(tokens.get(1), SongOrder.BACK);
            }else{
                playedSongDto = userService.playSongByOrder(tokens.get(1), SongOrder.NEXT);
            }
            System.out.println(playedSongDto);
        }catch(UserNotFoundException e){
            System.out.println("User not found");
        }catch(SongNotFoundException e){
            System.out.println("Given song id is not a part of the active playlist");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}