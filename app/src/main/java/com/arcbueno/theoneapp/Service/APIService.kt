package com.arcbueno.theoneapp.Service

import com.arcbueno.theoneapp.Models.BooksData
import com.arcbueno.theoneapp.Models.CharacterData
import com.arcbueno.theoneapp.Models.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface APIService{


    @GET("book")
    fun listBook() : Call<BooksData>

    @GET("movie")
    fun listMovie(): Call<MovieData>

    @GET("character")
    fun listCharacter() : Call<CharacterData>


}
