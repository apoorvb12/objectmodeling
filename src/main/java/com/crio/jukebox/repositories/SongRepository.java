package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.Song;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class SongRepository implements ISongRepository{
    private static Map<String,Song> songsList=new HashMap<String,Song>();
    private static Integer autoIncrement = 0;
    
    public static Map<String, Song> getSongsList() {
        return songsList;
    }

    @Override
    public Song save(Song song) {
        if(song.getId()==null){
            autoIncrement++;
            Song s=new Song(autoIncrement.toString(), song.getSongName(), song.getGenre(), song.getArtistList());
            song = s;
        }
        songsList.put(song.getId(),song);
        return song;
    }

    @Override
    public List<Song> findAll() {
        return songsList.values()
                        .stream()
                        .sorted((s1,s2)->Integer.valueOf(s1.getId())-Integer.valueOf(s2.getId()))
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(String s) {
        return Optional.ofNullable(songsList.get(s));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(Song entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public long count() {
        return songsList.values().stream().count();
    }

    @Override
    public Song findSongById(String songId) {
        return songsList.values().stream().filter(s->s.getId().equals(songId)).findAny().get();
    }
}