package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository implements IUserRepository{

    private static Map<String,User> userMap=new HashMap<String,User>();
    private static Map<String,Map<String,PlayList>> userPlayListMap = new HashMap<>();
    private static Map<String,PlayList> playListMap = new HashMap<>();
    private static Integer autoIncrementUser=0;
    private static Integer autoIncrementPlayList = 0;

    @Override
    public User save(User user) {
        if(user.getId()==null){
            autoIncrementUser++;
            User u=new User(autoIncrementUser.toString(),user.getName());
            user = u;
        }
        userMap.put(user.getId(),user);
        return user;
    }

    @Override
    public User createUser(String name){
        autoIncrementUser++;
        User user = new User(autoIncrementUser.toString(), name);
        userMap.put(user.getId(), user);
        Map<String,PlayList> map = new HashMap<>();
        userPlayListMap.put(user.getId(), map);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Optional<User> findById(String s) {
        return userMap.values().stream().filter(u->u.getId().equals(s)).findFirst();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public long count() {
        return 0;
    }

    // @Override
    // public List<PlayList> findAllPlayList(String userId) {
    //     return new ArrayList<>(userPlayListMap.get(userId).values());
    // }

    public PlayList save(PlayList playList) {
        if(playList.getId()==null) {
                autoIncrementPlayList++;
                PlayList p = new PlayList(autoIncrementPlayList.toString(), playList.getPlayListName(), playList.getSongs());
                playList = p;
        }
        // userPlayListMap.put(playList.getUser().getId(), palyList);
        return playList;
    }

    @Override
    public List<PlayList> findAllPlayListByUserId(String userId) {
        if(userPlayListMap.containsKey(userId))
            return userPlayListMap.get(userId).values().stream().collect(Collectors.toList());
        return null;
    }

    @Override
    public void deletePlayListByUserIdAndPlayListId(String userId, String playListId) {
        if(userPlayListMap.containsKey(userId)){
            Map<String,PlayList> map = userPlayListMap.get(userId);
            if(map.containsKey(playListId)){
                map.remove(playListId);
            }
            userPlayListMap.put(userId, map);
        }
    }

    @Override
    public PlayList createPlayList(String userId, String playListName, List<String> songs){
        List<Song> list = new LinkedList<>();
        for(String s : songs){
            if(SongRepository.getSongsList().containsKey(s)){
                Song song = SongRepository.getSongsList().get(s);
                list.add(song);
            }
        }
        int playListId = userMap.get(userId).getPlayListNumber();        
        playListId++;
        userMap.get(userId).setPlayListNumber(playListId);
        PlayList playList = new PlayList(String.valueOf(playListId), playListName, list);
        Map<String,PlayList> map = userPlayListMap.get(userId);
        map.put(playList.getId(), playList);
        userPlayListMap.put(userId, map);
        return playList;
    }

    @Override
    public PlayList createPlayList(PlayList entity) {
        if(entity.getId()==null){
            autoIncrementPlayList++;
            PlayList p = new PlayList(autoIncrementPlayList.toString(),entity.getPlayListName(),entity.getSongs());
            return p;
        }
        return entity;
    }

    @Override
    public boolean isPlayListExistByPlayListId(String userId,String playListId) {
        Map<String,PlayList> map = userPlayListMap.get(userId);
        if(map.containsKey(playListId))
            return true;
        return false;
    }

    @Override
    public PlayList addListOfSongsToUserPlayList(String userId, String playListId, List<Song> songs) {
        PlayList playList = null;
        if(userPlayListMap.containsKey(userId)){
            Map<String,PlayList> map = userPlayListMap.get(userId);
            if(map.containsKey(playListId)){
                playList = map.get(playListId);
                for(Song song:songs){
                    playList.getSongs().add(song);
                }
                map.put(playListId, playList);
            }
            userPlayListMap.put(userId, map);
        }
        return playList;
    }

    @Override
    public PlayList removeListOfSongsFromUserPlayList(String userId, String playListId, List<Song> songs) {
        PlayList playList = null;
        if(userPlayListMap.containsKey(userId)){
            Map<String,PlayList> map = userPlayListMap.get(userId);
            if(map.containsKey(playListId)){
                playList = map.get(playListId);
                for(Song song:songs){
                    if(playList.getSongs().contains(song)){
                        playList.getSongs().remove(song);
                    }
                }
                map.put(playListId, playList);
            }
            userPlayListMap.put(userId, map);
        }
        return playList;
    }
}