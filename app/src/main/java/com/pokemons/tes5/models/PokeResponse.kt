package com.pokemons.tes5.models

data class PokeResponse(
    val count : Int,
    val next: String,
    val previous: String,
    val results: Array<Pokemon>
)