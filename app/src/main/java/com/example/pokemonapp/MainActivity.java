package com.example.pokemonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;

import com.example.pokemonapp.model.Pokemon;
import com.example.pokemonapp.model.PokemonListViewModel;
import com.example.pokemonapp.model.PokemonResponse;
import com.example.pokemonapp.service.PokemonRepoServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Pokemon> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listViewPokemon=findViewById(R.id.listViewPokemons);
     //  final  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        PokemonListViewModel listViewModel=new PokemonListViewModel(this,R.layout.pokemon_listview_layout,data);
        listViewPokemon.setAdapter((listViewModel));
       final  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final  PokemonRepoServiceAPI pokemonRepoServiceAPI=retrofit.create(PokemonRepoServiceAPI.class);
        Call<PokemonResponse> callPokemon = pokemonRepoServiceAPI.getPokemons();
        callPokemon.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if(!response.isSuccessful()){
                    Log.i("info",String.valueOf(response.code()));
                    return;
                }
                PokemonResponse pokemonResponse=response.body();
                for(Pokemon p:pokemonResponse.pokemon){
                    data.add(p);
                }
                listViewModel.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.e("error","Error");
            }
        });
    }
}