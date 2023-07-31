package com.crio.jukebox.commands;

import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserService;
import java.util.ArrayList;
import java.util.List;

public class CreatePlayListCommand implements ICommand{

    private final IUserService userService;

    public CreatePlayListCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            List<String> song=new ArrayList<String>();
            for(int i=3;i<tokens.size();i++)
                song.add(tokens.get(i));
            PlayList playList=userService.createPlayList(tokens.get(1),tokens.get(2),song);
            System.out.println("Playlist ID - " + playList.getId());
        }catch (UserNotFoundException e){
            System.out.println("User is Not Present. Please try again");
        }catch (SongNotFoundException e){
            System.out.println("Some Requested Songs Not Available. Please try again.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}