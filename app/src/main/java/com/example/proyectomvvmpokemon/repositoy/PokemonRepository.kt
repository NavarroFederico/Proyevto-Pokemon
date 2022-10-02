package com.example.proyectomvvmpokemon.repositoy

import com.example.proyectomvvmpokemon.services.RetrofitClient
import com.example.proyectomvvmpokemon.services.WebService

class PokemonRepository {
    private var apiService: WebService?= null

    init {
        apiService = RetrofitClient.getClient?.create(WebService::class.java)

    }
    suspend fun getPokemon() = apiService?.getPokemons()
}