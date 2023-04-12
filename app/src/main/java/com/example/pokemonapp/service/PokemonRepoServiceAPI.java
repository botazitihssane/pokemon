package com.example.pokemonapp.service;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.example.pokemonapp.model.PokemonResponse;

public interface PokemonRepoServiceAPI {
    @GET("v2/pokemon")
    Call<PokemonResponse> getPokemons();
    @GET("v2/pokemon/{name}/")
    Call <PokemonResponse> pokemonCaract(@Path("name")String name);

}

