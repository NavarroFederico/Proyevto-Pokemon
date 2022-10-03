package com.example.proyectomvvmpokemon.services

import com.example.proyectomvvmpokemon.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/"
//aca pasamos la base url de la api cortamos "pokedex.json" para que cuando desde el GET de la interface se pegue con esta base

    //hago el get para consumir el servicio
    private val logger: OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()//nos sirve para debuggear la app para saber si nos esta llegando la respuesta
            logging.level= HttpLoggingInterceptor.Level.BODY

            val httpClient= OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
            //if(BuildConfig) httpClient.interceptors().add(logging)
                return httpClient.build()
        }
    val getClient : Retrofit?
    get() {
        if(retrofit == null){
            retrofit= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(logger)
                .build()
        }
        return retrofit
    }
}