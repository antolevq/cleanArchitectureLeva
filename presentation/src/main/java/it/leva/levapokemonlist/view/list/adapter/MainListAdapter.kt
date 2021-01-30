package it.leva.levapokemonlist.view.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import it.leva.domain.model.Pokemon
import it.leva.levapokemonlist.R

class MainListAdapter(
    private var pokemonList: MutableList<Pokemon>,
    val onClick: (name: String, url: String) -> Unit
) : PagingDataAdapter<Pokemon, MainListAdapter.PokemonViewHolder>(PokemonComparator) {


    inner class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPokemonName: TextView = view.findViewById(R.id.txt_pokemon_name)
        val imgPokemonDownloaded: AppCompatImageView = view.findViewById(R.id.img_sphere)
        val container: ConstraintLayout = view.findViewById(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemonList[position]
        holder.txtPokemonName.text = item.name
        holder.imgPokemonDownloaded.setImageResource(if (item.isDownloaded()) R.drawable.opened else R.drawable.closed)
        holder.container.setOnClickListener { onClick(item.name, item.url) }
    }

    override fun getItemCount() = pokemonList.size

    fun addItemsToList(additionalItems: List<Pokemon>) {
        pokemonList.addAll(additionalItems)
        notifyDataSetChanged()
    }

    object PokemonComparator : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

    }
}