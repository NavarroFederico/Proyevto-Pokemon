package com.example.proyectomvvmpokemon.services

import com.example.proyectomvvmpokemon.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("")//aca va la api

    suspend fun getPokemos(): Response<PokemonResponse>

}