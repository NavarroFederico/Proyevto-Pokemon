package com.example.proyectomvvmpokemon.services

import com.example.proyectomvvmpokemon.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("pokedex.json")//aca va consumir el servicio de la url completa

    suspend fun getPokemos(): Response<PokemonResponse>

}