package com.crio.jukebox.entities;

public class Artist extends BaseEntity{
    private final String artistName;

    public Artist(String id,String artistName) {
        this.id = id;
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artistName == null) ? 0 : artistName.hashCode());
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
        Artist other = (Artist) obj;
        if (artistName == null) {
            if (other.artistName != null)
                return false;
        } else if (!artistName.equals(other.artistName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Artist [id=" + id +", name=" + artistName + "]";
    }
    
}