package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.exceptions.*;
import com.crio.jukebox.dtos.UserSongDto;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.SongOrder;
import com.crio.jukebox.entities.User;

public interface IUserService {
    public User create(String name);
    public List<PlayList> getAllPlayList(String userId);
    public UserSongDto playSongById(String userId,String songId) throws UserNotFoundException,SongNotFoundException;
    public UserSongDto playSongByOrder(String userId,SongOrder playingOrder) throws UserNotFoundException;
    public PlayList createPlayList(String userId,String PlayListName,List<String>songs) throws UserNotFoundException,SongNotFoundException;
    public void deletePlayList(String userId,String PlayListId) throws UserNotFoundException,PlayListNotFoundException;
    public PlayList addSongToPlayList(String userId, String playListId, List<String>songs) throws UserNotFoundException,PlayListNotFoundException,SongNotFoundException, InvalidOperationException;
    public PlayList deleteSongFromPlayList(String userId,String playListId,List<String>songs) throws UserNotFoundException,PlayListNotFoundException,SongNotFoundException, InvalidOperationException;
    public UserSongDto setCurrentPlayList(String userId,String playListId) throws UserNotFoundException,PlayListNotFoundException;
    
}