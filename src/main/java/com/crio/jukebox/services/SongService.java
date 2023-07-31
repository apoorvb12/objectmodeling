package com.crio.jukebox.services;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongService implements ISongService{
    private final ISongRepository songRepository;
    private final IAlbumRepository albumRepository;

    public SongService(ISongRepository songRepository, IAlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public void loadSong(String inputFile) {
        BufferedReader reader;
        Integer songNum = 0;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            Song song;
            Album album;
            while (line != null) {
                songNum++;
                List<String> list = Arrays.asList(line.split(","));
                String songId = songNum.toString();
                String songName = list.get(0);
                String genre = list.get(1);
                String albumName = list.get(2);
                String albumOwner = list.get(3);
                List<String> listOfAlbumArtist = Arrays.asList(list.get(4).split("#"));
                song=new Song(songId,songName,genre,listOfAlbumArtist);
                song.setAlbumName(albumName);
                Song savedSong=songRepository.save(song);
                album=albumRepository.findAlbumByName(albumName);
                if(album==null){
                    album=new Album(null,albumName,new ArrayList<Song>(Arrays.asList(savedSong)),albumOwner);
                }else{
                    album.getSongList().add(savedSong);
                }
                Album savedAlbum=albumRepository.save(album);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}