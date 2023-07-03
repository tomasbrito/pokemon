package quicktype

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

data class PokemonData(
    val name: String,
    val height: Long,
    val weight: Long,
    val sprites: Sprites,
    val id : Long,
    val stats: List<Stat>,
    val moves: List<Mfe>,
    val abilities: List<Ability>,
)

data class Stat(
    val base_stat: Long,
    val effort: Long,
    val stat: Stat2,
)

data class Stat2(
    val name: String,
    val url: String,
)

data class Sprites(
    val front_default: String,
    val back_default: String,
    val front_shiny: String,
    val back_shiny: String,

)

data class Mfe(
    val move: Move,
)

data class Move(
    val name: String,
    val url: String,
)

data class Ability(
    val ability: Ability2,
    val isHidden: Boolean,
    val slot: Long,
)

data class Ability2(
    val name: String,
    val url: String,
)