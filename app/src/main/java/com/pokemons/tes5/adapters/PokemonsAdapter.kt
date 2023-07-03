package com.pokemons.tes5.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pokemons.tes5.R
import com.pokemons.tes5.models.Pokemon

class PokemonsAdapter(private var pokemonsList: Array<Pokemon>, val onItemClick : (String) -> Unit)
    : RecyclerView.Adapter<PokemonsAdapter.PokemonHolder>() {

    companion object {
        private val TAG = "PokemonsAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsAdapter.PokemonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mascota, parent,false)
        return (PokemonHolder(view))
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }


    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        val pokemon = pokemonsList[position]
        holder.setName(pokemon.name)

        holder.getCardLayout().setOnClickListener{
            onItemClick(holder.getName())
        }
    }

    class PokemonHolder (v: View) : RecyclerView.ViewHolder(v){
        private var view : View

        init {
            this.view = v
        }

        fun setName(name:String){
            val txt : TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }


        fun getName():String{
            val txt : TextView = view.findViewById(R.id.txt_name_item)
            return txt.text as String
        }

        fun getCardLayout():CardView{
            return view.findViewById(R.id.card_packate_item)
        }

    }

}