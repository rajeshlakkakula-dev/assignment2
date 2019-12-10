
package com.rs.assignment.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;




public class AlbumJsonResponse {

    @SerializedName("data")
    private List<Album> mData;

    public List<Album> getData() {
        return mData;
    }

    public static class Builder {

        private List<Album> mData;

        public AlbumJsonResponse.Builder withData(List<Album> data) {
            mData = data;
            return this;
        }

        public AlbumJsonResponse build() {
            AlbumJsonResponse album = new AlbumJsonResponse();
            album.mData = mData;
            return album;
        }

    }

}
