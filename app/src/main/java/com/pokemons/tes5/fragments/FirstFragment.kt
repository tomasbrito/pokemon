package com.pokemons.tes5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.pokemons.tes5.R


class FirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    lateinit var fragmentView: View
    lateinit var secondBtn : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_first, container, false)

        return fragmentView
    }

    override fun onStart() {
        super.onStart()

    }


}