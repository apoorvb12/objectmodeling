package com.crio.jukebox.services;

import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.dtos.UserSongDto;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOrder;
import com.crio.jukebox.entities.SongStatus;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserService implements IUserService {

    IUserRepository userRepository;
    ISongRepository songRepository;
    private List<Song> songPlaylistQueue;
    private Integer currentSongPlayingIdx;


    public UserService(IUserRepository userRepository,ISongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        songPlaylistQueue = new LinkedList<Song>();
    }


    @Override
    public User create(String name) {
        return userRepository.createUser(name);
    }

    @Override
    public List<PlayList> getAllPlayList(String userId) {
        return null;
    }

    @Override
    public UserSongDto playSongById(String userId, String songId) throws UserNotFoundException, SongNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User No Found!!!"));
        Song song=songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song No Found!!!"));
        if(songPlaylistQueue.get(currentSongPlayingIdx).getId().equals(songId)) {
            UserSongDto userSongDto=new UserSongDto(user.getName(),song.getSongName(),song.getAlbumName(),listToString(song.getArtistList()));
            return userSongDto;
        }
        for(int i=0;i<songPlaylistQueue.size();i++){
            if(songPlaylistQueue.get(i).getId().equals(songId)){
                currentSongPlayingIdx=i;
                Song newSong=songPlaylistQueue.get(i);
                UserSongDto userSongDto=new UserSongDto(user.getName(),newSong.getSongName(),newSong.getAlbumName(),listToString(newSong.getArtistList()));
                return userSongDto;
            }
        }
        throw new SongNotFoundException("Song is Not Present Current Playing PlayList !!!!");
    }
 
    private static String listToString(List<String> artistList){
        String str = "";
        for(String s : artistList){
            str = str + s + ",";
        }
        str = str.substring(0,str.length()-1);
        return str;
    }

    @Override
    public UserSongDto playSongByOrder(String userId, SongOrder playingOrder) throws UserNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User No Found"));
        if(playingOrder==SongOrder.NEXT){
            currentSongPlayingIdx=(currentSongPlayingIdx+1)%songPlaylistQueue.size();
            Song currentPlayingSong=songPlaylistQueue.get(currentSongPlayingIdx);
            UserSongDto userSongDto=new UserSongDto(user.getName(),currentPlayingSong.getSongName(),currentPlayingSong.getAlbumName(),listToString(currentPlayingSong.getArtistList()));
            return userSongDto;
        }else if(playingOrder==SongOrder.BACK){
            currentSongPlayingIdx=(currentSongPlayingIdx-1+songPlaylistQueue.size())%songPlaylistQueue.size();
            Song currentPlayingSong=songPlaylistQueue.get(currentSongPlayingIdx);
            UserSongDto userSongDto=new UserSongDto(user.getName(),currentPlayingSong.getSongName(),currentPlayingSong.getAlbumName(),listToString(currentPlayingSong.getArtistList()));
            return userSongDto;
        }
        return null;
    }

    @Override
    public PlayList createPlayList(String userId, String PlayListName, List<String> songs) throws UserNotFoundException,SongNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User No Found"));
        List<Song> listOfAllSongs=new LinkedList<Song>();
        for(String songId:songs){
            Song song=songRepository.findSongById(songId);
            if(song==null)
                throw new SongNotFoundException("Song doesn't exist...");
        }
        PlayList playList = userRepository.createPlayList(userId,PlayListName,songs);
        return playList;
    }


    @Override
    public void deletePlayList(String userId, String PlayListId) throws UserNotFoundException, PlayListNotFoundException {
        if(userRepository.isPlayListExistByPlayListId(userId,PlayListId)){
            userRepository.deletePlayListByUserIdAndPlayListId(userId,PlayListId);
        }else{
            throw new PlayListNotFoundException("PlayList is Not Found");
        }

    }

    @Override
    public PlayList addSongToPlayList(String userId, String playListId, List<String> songs) throws UserNotFoundException, PlayListNotFoundException, SongNotFoundException ,InvalidOperationException{
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User No Found"));
        List<Song> listOfAllSongs=new ArrayList<Song>();
        for(String songId:songs){
            Song song=songRepository.findSongById(songId);
            if(song!=null)
                listOfAllSongs.add(song);
            else throw new SongNotFoundException("Songs Not Found");
        }
        PlayList playList=userRepository.addListOfSongsToUserPlayList(userId,playListId,listOfAllSongs);
        if(playList==null) 
            throw new InvalidOperationException("No PlayList Found!!!");
        return playList;
    }

    @Override
    public PlayList deleteSongFromPlayList(String userId, String playListId, List<String> songs) throws UserNotFoundException, PlayListNotFoundException, SongNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User No Found"));
        List<Song> listOfAllSongs=new ArrayList<Song>();
        for(String songId:songs){
            Song song=songRepository.findSongById(songId);
            if(song!=null)
                listOfAllSongs.add(song);
            else throw new SongNotFoundException("Songs Not Found");
        }
        PlayList playList=userRepository.removeListOfSongsFromUserPlayList(userId,playListId,listOfAllSongs);
        if(playList==null) 
            throw new InvalidOperationException("Song ID is No Found !!!");
        return playList;
    }

    @Override
    public UserSongDto setCurrentPlayList(String userId, String playListId) throws UserNotFoundException, PlayListNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User No Found"));
        if(!userRepository.isPlayListExistByPlayListId(userId,playListId)) throw new PlayListNotFoundException("PlayList is Not Found!!!");
        List<PlayList> listOfPlayList=userRepository.findAllPlayListByUserId(user.getId());
        for(PlayList curPlay:listOfPlayList){
            if(curPlay.getSongStatus()==SongStatus.PLAYING && !curPlay.getId().equals(playListId)){
                curPlay.setSongStatus(SongStatus.NOT_PLAYING);
            }
        }
        PlayList currentPlayList=listOfPlayList.stream().filter(p->p.getId().equals(playListId)).findFirst().get();
        currentPlayList.setSongStatus(SongStatus.PLAYING);
        songPlaylistQueue.clear();
        for(Song song:currentPlayList.getSongs()){
            songPlaylistQueue.add(song);
        }
        //songIterator=songPlaylistQueue.listIterator();
        currentSongPlayingIdx=0;
        Song song=songPlaylistQueue.get(currentSongPlayingIdx);
        UserSongDto userPlayedSongDto=new UserSongDto(user.getName(),song.getSongName(),song.getAlbumName(),listToString(song.getArtistList()));
        //songIterator.next();
        return userPlayedSongDto;
    }
 

}