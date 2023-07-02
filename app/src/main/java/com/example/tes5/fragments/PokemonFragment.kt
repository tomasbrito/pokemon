package com.example.tes5.fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.tes5.R
import com.example.tes5.interfaces.ApiService
import com.example.tes5.serviceSingleton.ServiceSingleton
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.launch
import quicktype.Ability
import quicktype.PokemonData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class PokemonFragment : Fragment() {

    lateinit var shimmerLayout : ShimmerFrameLayout
    lateinit var data_view : LinearLayoutCompat
    lateinit var fragmentView : View
    lateinit var nameTxtView : TextView
    lateinit var pokemonName: String; lateinit var pokemonID: TextView
    lateinit var pokemonWeight: TextView;lateinit var pokemonHeight: TextView
    lateinit var txt_move1 : TextView;lateinit var txt_move2 : TextView;lateinit var txt_move3 : TextView;lateinit var txt_move4 : TextView;lateinit var txt_move5 : TextView;
    lateinit var image1 : ImageView;lateinit var image2 : ImageView;lateinit var image3 : ImageView;lateinit var image4 : ImageView
    lateinit var txt_hp_val: TextView; lateinit var txt_attack_val : TextView; lateinit var txt_defense_val : TextView
    lateinit var txt_speed_val: TextView; lateinit var txt_specialA_val: TextView; lateinit var txt_specialD_val : TextView
    lateinit var txt_ability1_title : TextView;lateinit var txt_ability1_desc : TextView
    lateinit var txt_ability2_title : TextView;lateinit var txt_ability2_desc : TextView
    lateinit var ability2_card : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         fragmentView =  inflater.inflate(R.layout.fragment_pokemon, container, false)
        shimmerLayout = fragmentView.findViewById(R.id.shimmerLayout)
        data_view = fragmentView.findViewById(R.id.data_view)
        nameTxtView = fragmentView.findViewById(R.id.txt_name)
        pokemonID = fragmentView.findViewById(R.id.txt_id)
        image1 = fragmentView.findViewById(R.id.image1)
        image2 = fragmentView.findViewById(R.id.image2)
        image3 = fragmentView.findViewById(R.id.image3)
        image4 = fragmentView.findViewById(R.id.image4)

        txt_move1 = fragmentView.findViewById(R.id.txt_move1)
        txt_move2 = fragmentView.findViewById(R.id.txt_move2)
        txt_move3 = fragmentView.findViewById(R.id.txt_move3)
        txt_move4 = fragmentView.findViewById(R.id.txt_move4)
        txt_move5 = fragmentView.findViewById(R.id.txt_move5)

        txt_ability1_desc = fragmentView.findViewById(R.id.txt_ability1_desc)
        txt_ability1_title  = fragmentView.findViewById(R.id.txt_ability1_title)
        txt_ability2_desc = fragmentView.findViewById(R.id.txt_ability2_desc)
        txt_ability2_title  = fragmentView.findViewById(R.id.txt_ability2_title)

        txt_hp_val = fragmentView.findViewById(R.id.txt_hp_val)
        txt_attack_val = fragmentView.findViewById(R.id.txt_attack_val)
        txt_defense_val = fragmentView.findViewById(R.id.txt_defense_val)
        txt_speed_val = fragmentView.findViewById(R.id.txt_speed_val)
        txt_specialA_val = fragmentView.findViewById(R.id.txt_specialA_val)
        txt_specialD_val = fragmentView.findViewById(R.id.txt_specialD_val)

        ability2_card = fragmentView.findViewById(R.id.ability2)

        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        pokemonName = PokemonFragmentArgs.fromBundle(requireArguments()).name
        nameTxtView.text = pokemonName
        val serviceSingleton = ServiceSingleton.getInstance()
        val apiService = serviceSingleton.getApiService()
        //val apiService = createApiService()

        lifecycleScope.launch {
            try {
                val pokemonData = apiService.getPokemon(pokemonName)

                val abilityList : ArrayList<com.example.tes5.models.Ability> = arrayListOf()
                abilityList.add(apiService.getAbility(pokemonData.abilities[0].ability.name))
                if (pokemonData.abilities.getOrNull(1)?.ability?.name != null){
                    abilityList.add(apiService.getAbility(pokemonData.abilities[1].ability.name))
                }

                setData(pokemonData, abilityList)
                shimmerLayout.visibility = View.INVISIBLE;
                data_view.visibility = View.VISIBLE
            } catch (e : Error){
                Log.d(TAG, ": ${e}")
            }
        }
    }



    @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    private fun setData(pokemon: PokemonData, abilitys : ArrayList<com.example.tes5.models.Ability>){
        nameTxtView.text = pokemon.name
        if (pokemon.id<100 )  pokemonID.text = "#0" + pokemon.id.toString() else pokemonID.text = "#" + pokemon.id.toString()
        txt_hp_val.text = pokemon.stats[0].base_stat.toString()
        txt_attack_val.text = pokemon.stats[1].base_stat.toString()
        txt_defense_val.text = pokemon.stats[2].base_stat.toString()
        txt_speed_val.text = pokemon.stats[3].base_stat.toString()
        txt_specialA_val.text = pokemon.stats[4].base_stat.toString()
        txt_specialD_val.text = pokemon.stats[5].base_stat.toString()
        txt_move1.text = pokemon.moves?.getOrNull(0)?.move?.name
        txt_move2.text = pokemon.moves?.getOrNull(1)?.move?.name
        txt_move3.text = pokemon.moves?.getOrNull(2)?.move?.name
        txt_move4.text = pokemon.moves?.getOrNull(3)?.move?.name
        txt_move5.text = pokemon.moves?.getOrNull(4)?.move?.name

        txt_ability1_desc.text = abilitys[0].effect_entries[1].short_effect
        txt_ability1_title.text = abilitys[0].name.capitalized()
        if(abilitys.getOrNull(1) != null){
            txt_ability2_desc.text = abilitys[1].effect_entries[1].short_effect
            txt_ability2_title.text = abilitys[1].name.capitalized()
        } else {
            ability2_card.visibility = View.INVISIBLE
        }


        setImages(pokemon)

    }



    fun String.capitalized(): String {
        return this.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }

    private fun setImages(pokemon: PokemonData) {
        Glide
            .with(requireContext())
            .load(pokemon.sprites.front_default)
            .into(image1)
        Glide
            .with(requireContext())
            .load(pokemon.sprites.back_default)
            .into(image2)
        Glide
            .with(requireContext())
            .load(pokemon.sprites.front_shiny)
            .into(image3)
        Glide
            .with(requireContext())
            .load(pokemon.sprites.back_shiny)
            .into(image4)
    }

    private fun createApiService(): ApiService {
        Log.d(TAG, "createApiService: kia")
        val retro = ServiceSingleton.getInstance()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }


}