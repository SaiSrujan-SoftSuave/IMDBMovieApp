package com.example.imdbmoviesapp.data.remote.api

import com.example.imdbmoviesapp.data.remote.dto.PersonDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonsApi {
    //https://api.themoviedb.org/3/person/878?api_key=4e44d9029b1270a757cddc766a1bcb63&language=en-US

    @GET("/person/{personId}")
    suspend fun getPersonDetails(@Path("personId")personId:Int) :PersonDto
}