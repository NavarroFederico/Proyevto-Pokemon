package com.example.proyectomvvmpokemon.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomvvmpokemon.databinding.PokemonRowBinding
import com.example.proyectomvvmpokemon.model.PokemonDataModel

class ItemViewHolder(binding: PokemonRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: PokemonRowBinding? = null

    init {
        this.binding = binding
    }

    fun setItem(model: PokemonDataModel) {
        binding?.let { view->
            view.name = model.name

            Glide.with(view.root.context).load(model.img).into(view.imgCharacter)
        }
    }
}