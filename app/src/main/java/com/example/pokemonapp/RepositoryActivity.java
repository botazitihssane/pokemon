package com.example.pokemonapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokemonapp.model.Pokemon;
import com.example.pokemonapp.model.PokemonResponse;
import com.example.pokemonapp.service.PokemonRepoServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryActivity extends AppCompatActivity {
    List<String> data = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_layout);
        Intent intent=getIntent();
        String name=intent.getStringExtra("pokemon.name");
        setTitle("Abilities");
        TextView textViewName = findViewById(R.id.textViewname);
        ListView listViewAbilities = findViewById(R.id.listViewRepositories);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        listViewAbilities.setAdapter(arrayAdapter);
        textViewName.setText(name);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final PokemonRepoServiceAPI pokemonRepo=retrofit.create(PokemonRepoServiceAPI.class);

        Call<PokemonResponse> callList = pokemonRepo.pokemonCaract(name);
        callList.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (!response.isSuccessful()) {
                    Log.i("info", String.valueOf(response.code()));
                    return;
                }
                PokemonResponse pokemonResponse = response.body();
                data.clear();

                for (Pokemon p : pokemonResponse.pokemon) {
                    String content = "";
                    content += p.id + "\n";
                    content += p.height + "\n";
                    content += p.base_experience + "\n";
                    data.add(content);
                    System.out.println("content"+content);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.e("error", "Error");
            }
        });
    }
}
