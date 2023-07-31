package com.crio.jukebox.commands;

import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserService;

import java.util.ArrayList;
import java.util.List;

public class ModifyPlayListCommand implements ICommand{
    private final IUserService userService;

    public ModifyPlayListCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        List<String> csong=new ArrayList<String>();
        for(int i=4;i<tokens.size();i++)
            csong.add(tokens.get(i));
        PlayList returnPlayList;
        if(tokens.get(1).equals("ADD-SONG")){
            try {
                returnPlayList = userService.addSongToPlayList(tokens.get(2), tokens.get(3), csong);
                System.out.println("Playlist ID - " + returnPlayList.getId());
                System.out.println("Playlist Name - " + returnPlayList.getPlayListName());
                System.out.println("Song IDs - " + songListToString(returnPlayList.getSongs()));
            }catch (UserNotFoundException e){
                System.out.println("User Not Found");
            }catch (SongNotFoundException e){
                System.out.println("Song Not Found");
            }catch (PlayListNotFoundException e){
                System.out.println("PlayList Not Found");
            }catch (InvalidOperationException e){
                System.out.println("Some Requested Songs Not Available. Please try again.");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            try{
                returnPlayList =userService.deleteSongFromPlayList(tokens.get(2),tokens.get(3),csong);
                System.out.println("Playlist ID - "+returnPlayList.getId());
                System.out.println("Playlist Name - "+returnPlayList.getPlayListName());
                System.out.println("Song IDs - "+ songListToString(returnPlayList.getSongs()));
            }catch (UserNotFoundException e){
                System.out.println("User Not Found");
            }catch (SongNotFoundException e){
                System.out.println("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }catch (PlayListNotFoundException e){
                System.out.println("PlayList Not Found");
            }catch (InvalidOperationException e){
                System.out.println("Some Requested Songs Not Available. Please try again.");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static String songListToString(List<Song> songs){
        String str = "";
        for(Song song : songs){
            str = str + song.getId() + " ";
        }
        str = str.substring(0,str.length()-1);
        return str;
    }
}