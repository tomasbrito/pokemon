package com.pokemons.tes5.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pokemons.tes5.R
import com.pokemons.tes5.adapters.PokemonsAdapter
import com.pokemons.tes5.interfaces.ApiService
import com.pokemons.tes5.models.Pokemon
import com.pokemons.tes5.serviceSingleton.ServiceSingleton
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListFragment : Fragment() {

    lateinit var fragmentView : View
    lateinit var recMascotas : RecyclerView

    var pokemons : MutableList<Pokemon> = ArrayList<Pokemon>()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var pokemonsAdapter: PokemonsAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false)
        recMascotas = fragmentView.findViewById(R.id.rec_mascotas)
        return fragmentView
    }

    override fun onStart() {
        super.onStart()

        setRecycler()

        val serviceSingleton = ServiceSingleton.getInstance()
        val apiService = serviceSingleton.getApiService()

        lifecycleScope.launch {
            try {
                val response = apiService.getItems(151,0)
                pokemonsAdapter = PokemonsAdapter(response.results){name -> onItemClick(name)}
                recMascotas.adapter = pokemonsAdapter
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }

    }

    private fun setRecycler() {
        recMascotas.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recMascotas.layoutManager = linearLayoutManager
        //recMascotas.adapter = PokemonsAdapter(response.results)
    }

    private fun createApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
    
     fun onItemClick(name : String){
        val action = ListFragmentDirections.actionListFragmentToPokemonFragment(name)
        fragmentView.findNavController().navigate(action)
    }

}