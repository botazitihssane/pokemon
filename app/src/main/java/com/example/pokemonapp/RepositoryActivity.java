package com.example.pokemonapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokemonapp.model.CaractResponse;
import com.example.pokemonapp.model.Pokemon;
import com.example.pokemonapp.model.PokemonResponse;
import com.example.pokemonapp.service.PokemonRepoServiceAPI;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_layout);
        Intent intent=getIntent();
        String name=intent.getStringExtra("pokemon.name");
         int height;
        int base;
        setTitle("Abilities");
        TextView textViewName = findViewById(R.id.textViewname);
        ImageView image = findViewById(R.id.imageViewPokemon);
        TextView textHeight = findViewById(R.id.textHeight);
        TextView textBase = findViewById(R.id.textBase);
      //  ListView listViewAbilities = findViewById(R.id.listViewRepositories);
      //  ArrayAdapter<CaractResponse> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
       // listViewAbilities.setAdapter(arrayAdapter);
        textViewName.setText(name);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final PokemonRepoServiceAPI pokemonRepo=retrofit.create(PokemonRepoServiceAPI.class);

        Call<CaractResponse> callList = pokemonRepo.pokemonCaract(name);
        callList.enqueue(new Callback<CaractResponse>() {

            @Override
            public void onResponse(Call<CaractResponse> call, Response<CaractResponse> response) {
                if (!response.isSuccessful()) {
                    Log.i("info", String.valueOf(response.code()));
                    return;
                }

                CaractResponse pokemonResponse = response.body();
           /*     height= pokemonResponse.height;
                base= pokemonResponse.base_experience;*/
                textBase.setText(pokemonResponse.base_experience+"");
                textHeight.setText(pokemonResponse.height+"");

                URL url= null;
                try {
                    int p = pokemonResponse.id;
                    url = new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p+".png");
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                    image.setImageBitmap(bitmap);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onFailure(Call<CaractResponse> call, Throwable t) {

            }
        });
    }
}
