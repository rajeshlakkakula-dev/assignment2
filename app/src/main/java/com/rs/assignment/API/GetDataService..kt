package com.rs.assignment.API

import com.rs.assignment.models.AlbumJsonResponse
import retrofit2.Call
import retrofit2.http.GET


interface GetDataService {

    @get:GET("/bins/rov51")
    val albumsList: Call<AlbumJsonResponse>

}