package com.crio.jukebox.entities;

import java.util.List;


public class Song extends BaseEntity{
    private final String songName;
    private final String genre;
    private String albumName;
    private final List<String> artistList;

    public Song(String id, String songName, String genre, List<String> artistList) {
        this.songName = songName;
        this.genre = genre;
        this.artistList = artistList;
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public String getGenre() {
        return genre;
    }

    public List<String> getArtistList() {
        return artistList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artistList == null) ? 0 : artistList.hashCode());
        result = prime * result + ((songName == null) ? 0 : songName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (artistList == null) {
            if (other.artistList != null)
                return false;
        } else if (!artistList.equals(other.artistList))
            return false;
        if (songName == null) {
            if (other.songName != null)
                return false;
        } else if (!songName.equals(other.songName))
            return false;
        return true;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    
    @Override
    public String toString() {
        return "Song [id=" + id +", artist=" + artistList + ", name=" + songName + "]";
    } 
    
    
}