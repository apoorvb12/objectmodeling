package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Song;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlbumRepository implements IAlbumRepository{

    private static Map<String, Album> albumList=new HashMap<String,Album>();
    private static Map<String, String> albumNameAndId=new HashMap<String,String>();
    private static Integer autoIncrement = 0;

    @Override
    public Album save(Album album) {
        if(album.getId()==null){
            autoIncrement++;
            Album a=new Album(autoIncrement.toString(),album.getAlbumName(),album.getSongList(),album.getOwnerName());
            album = a;
        }
        albumList.put(album.getId(),album);
        albumNameAndId.put(album.getAlbumName(),album.getId());
        return album;
    }

    @Override
    public List<Album> findAll() {
        return albumList.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Album> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(Album entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public long count() {
        return 0;
    }


    @Override
    public Album findAlbumById(String albumId) {
        return null;
    }

    @Override
    public Album findAlbumByName(String albumName) {
        if(albumNameAndId.containsKey(albumName)){
            return albumList.get(albumNameAndId.get(albumName));
        }
        return null;
    }
}