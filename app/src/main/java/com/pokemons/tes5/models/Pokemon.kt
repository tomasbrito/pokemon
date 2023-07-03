package com.pokemons.tes5.models

class Pokemon (val name:String, val url: String) {

    constructor() : this("","")

    override fun toString(): String {
        return "Pokemon(name='$name', url='$url')"
    }


}