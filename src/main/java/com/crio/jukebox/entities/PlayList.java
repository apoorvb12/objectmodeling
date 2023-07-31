package com.crio.jukebox.entities;

import java.util.List;

public class PlayList extends BaseEntity{
    private final String playListName;
    private List<Song> songs;
    private SongStatus songStatus;

    public PlayList(String id,String playListName, List<Song> songs) {
        this.playListName = playListName;
        this.songs = songs;
        this.id = id;
        this.songStatus=SongStatus.NOT_PLAYING;
    }
    
    public String getPlayListName() {
        return playListName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((playListName == null) ? 0 : playListName.hashCode());
        result = prime * result + ((songs == null) ? 0 : songs.hashCode());
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
        PlayList other = (PlayList) obj;
        if (playListName == null) {
            if (other.playListName != null)
                return false;
        } else if (!playListName.equals(other.playListName))
            return false;
        if (songs == null) {
            if (other.songs != null)
                return false;
        } else if (!songs.equals(other.songs))
            return false;
        return true;
    }
    public SongStatus getSongStatus() {
        return songStatus;
    }

    public void setSongStatus(SongStatus songStatus) {
        this.songStatus = songStatus;
    }

    @Override
    public String toString() {
        return "PlayList [id=" + id +", name=" + playListName + ", songs=" + songs + "]";
    }
    
    
}