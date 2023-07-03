package com.pokemons.tes5.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pokemons.tes5.R;


public class SecondFragment extends Fragment {

    View fragmentView;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       fragmentView =  inflater.inflate(R.layout.fragment_second, container, false);
        return  fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}