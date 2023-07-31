package com.crio.jukebox.entities;

import java.util.List;
import java.lang.String;

public class Album extends BaseEntity{
    private final String albumName;
    private final List<Song> songList;
    private final String ownerName;

    public Album(String id, String albumName, List<Song> songList, String ownerName) {
        this.id = id;
        this.albumName = albumName;
        this.songList = songList;
        this.ownerName = ownerName;
    }

    public String getAlbumName() {
        return this.albumName;
    }
    public List<Song> getSongList() {
        return this.songList;
    }
    public String getOwnerName() {
        return this.ownerName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
        result = prime * result + ((songList == null) ? 0 : songList.hashCode());
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
        Album other = (Album) obj;
        if (albumName == null) {
            if (other.albumName != null)
                return false;
        } else if (!albumName.equals(other.albumName))
            return false;
        if (ownerName == null) {
            if (other.ownerName != null)
                return false;
        } else if (!ownerName.equals(other.ownerName))
            return false;
        if (songList == null) {
            if (other.songList != null)
                return false;
        } else if (!songList.equals(other.songList))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Album [id=" + id +", name=" + albumName + ", owner="
                + ownerName + ", songList=" + songList + "]";
    } 

    
    
}