package com.rs.assignment.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APICALL {

    companion object {

        private val BASE_URL = "http://api.myjson.com/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getRetrofitInstance(): Retrofit = retrofit

    }

}