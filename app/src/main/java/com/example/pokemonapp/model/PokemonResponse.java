package com.example.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokemonResponse {
    @SerializedName("count")
    public int count;
    @SerializedName("results")
    public List<Pokemon> pokemon = new ArrayList<>();
}
