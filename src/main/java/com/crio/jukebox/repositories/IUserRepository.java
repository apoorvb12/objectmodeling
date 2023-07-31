package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

public interface IUserRepository extends CRUDRepository<User,String>{
    public User createUser(String name);
    public List<PlayList> findAllPlayListByUserId(String name);
    public void deletePlayListByUserIdAndPlayListId(String userId,String playListId);
    public PlayList createPlayList(String userId, String playListName, List<String> songs);
    public PlayList createPlayList(PlayList entity);
    public boolean isPlayListExistByPlayListId(String userId,String playListId);
    public PlayList addListOfSongsToUserPlayList(String userId, String playListId, List<Song> songs);
    public PlayList removeListOfSongsFromUserPlayList(String userId, String playListId, List<Song> songs);
}