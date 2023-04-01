package com.example.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

public class Pokemon {
    public int id;
    public String name;
    @SerializedName("pokemon_url")
    public String URL;
    public float height;
    public boolean is_default;
    public String[] abilities;
}
