package com.pokemons.tes5.interfaces


import com.pokemons.tes5.models.Ability
import com.pokemons.tes5.models.PokeResponse

import quicktype.PokemonData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getItems(@Query("limit") limit : Int, @Query("offset") offset : Int ): PokeResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonData

    @GET("ability/{name}")
    suspend fun getAbility(@Path("name") name: String): Ability
}