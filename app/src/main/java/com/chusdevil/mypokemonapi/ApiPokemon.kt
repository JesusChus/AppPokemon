package com.chusdevil.mypokemonapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import retrofit2.http.Url as Url1

interface ApiPokemon {
    @GET("pokemon")
    fun traerPokemon(): Call<DatosPokemon>

    @GET
    fun traerUnPokemon(@Url url: String): Call<UnPokemon>


}