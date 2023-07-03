package com.pokemons.tes5.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pokemons.tes5.R
import com.pokemons.tes5.entities.Mascota

class MascotaListAdapter (private var mascotasList: MutableList<Mascota>) : RecyclerView.Adapter<MascotaListAdapter.MascotaHolder>() {

    companion object {
        private val TAG = "MascotasListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaListAdapter.MascotaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mascota, parent,false)
        return (MascotaHolder(view))
    }

    override fun getItemCount(): Int {
        return mascotasList.size
    }

    fun setData(newData: ArrayList<Mascota>){
        this.mascotasList = newData
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MascotaHolder, position: Int) {
        holder.setName(mascotasList[position].name)
    }

    class MascotaHolder (v: View) : RecyclerView.ViewHolder(v){
        private var view : View

        init {
            this.view = v
        }

        fun setName(name:String){
            val txt : TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }

    }

}