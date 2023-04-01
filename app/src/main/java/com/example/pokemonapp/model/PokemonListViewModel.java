package com.example.pokemonapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pokemonapp.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PokemonListViewModel extends ArrayAdapter<Pokemon> {
    private List<Pokemon> pokemons;
    private int resource;
    public PokemonListViewModel(@NonNull Context context, int resource,List<Pokemon> data) {
        super(context, resource,data);
        this.pokemons=data;
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent){
        View listViewItem=convertView;
        if(listViewItem== null){
            listViewItem= LayoutInflater.from(getContext()).inflate(resource,parent,false);
        }
        ImageView imageViewPokemon=listViewItem.findViewById(R.id.imageViewPokemon);
        TextView textViewName=listViewItem.findViewById(R.id.textViewName);

        textViewName.setText(pokemons.get(position).name);
     /*   try {
            URL url=new URL(pokemons.get(position).URL);
            Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
            imageViewPokemon.setImageBitmap(bitmap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
*/
        return listViewItem;
    }
}
