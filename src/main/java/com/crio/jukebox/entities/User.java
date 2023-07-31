package com.crio.jukebox.entities;

import java.util.LinkedList;
import java.util.List;

public class User extends BaseEntity{
    private final String userName;
    private Integer playListNumber = 0;
    private List<PlayList> playLists = new LinkedList<>();

    public User(String id,String userName) {
        this.id = id;
        this.userName = userName;
    }
    
    public String getName() {
        return userName;
    }

    public Integer getPlayListNumber() {
        return this.playListNumber;
    }

    public void setPlayListNumber(Integer playListNumber) {
        this.playListNumber = playListNumber;
    }

    public List<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + userName + " ]";
    }
}