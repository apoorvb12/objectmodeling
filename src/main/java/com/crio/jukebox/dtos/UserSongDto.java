package com.crio.jukebox.dtos;

public class UserSongDto {
    private final String userName;
    private final String songName;
    private final String albumName;
    private final String artistName;

    public UserSongDto(String userName, String songName, String albumName,
            String artistName) {
        this.userName = userName;
        this.songName = songName;
        this.albumName = albumName;
        this.artistName = artistName;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = prime * result + ((artistName == null) ? 0 : artistName.hashCode());
        result = prime * result + ((songName == null) ? 0 : songName.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        UserSongDto other = (UserSongDto) obj;
        if (albumName == null) {
            if (other.albumName != null)
                return false;
        } else if (!albumName.equals(other.albumName))
            return false;
        if (artistName == null) {
            if (other.artistName != null)
                return false;
        } else if (!artistName.equals(other.artistName))
            return false;
        if (songName == null) {
            if (other.songName != null)
                return false;
        } else if (!songName.equals(other.songName))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Current Song Playing\n"
            + "Song - " + songName
            + "\n" + "Album - " + albumName
            + "\n" + "Artists - " + artistName;
    }



    
    
}