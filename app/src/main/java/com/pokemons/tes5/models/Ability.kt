package com.pokemons.tes5.models


data class  Ability (
    val effect_entries: List<EffectEntry>,
    val name: String,
        )

data class EffectEntry(
    val effect: String,
    val short_effect: String,
)