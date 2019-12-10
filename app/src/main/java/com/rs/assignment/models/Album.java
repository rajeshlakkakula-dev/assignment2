
package com.rs.assignment.models;


import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("Album")
    private String mAlbum;
    @SerializedName("Artist")
    private String mArtist;
    @SerializedName("Name")
    private String mName;

    public String getAlbum() {
        return mAlbum;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getName() {
        return mName;
    }

    public static class Builder {

        private String mAlbum;
        private String mArtist;
        private String mName;

        public Album.Builder withAlbum(String album) {
            mAlbum = album;
            return this;
        }

        public Album.Builder withArtist(String artist) {
            mArtist = artist;
            return this;
        }

        public Album.Builder withName(String name) {
            mName = name;
            return this;
        }

        public Album build() {
            Album datum = new Album();
            datum.mAlbum = mAlbum;
            datum.mArtist = mArtist;
            datum.mName = mName;
            return datum;
        }

    }

}
