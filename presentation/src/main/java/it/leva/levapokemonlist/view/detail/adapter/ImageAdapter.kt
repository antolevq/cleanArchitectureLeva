package it.leva.levapokemonlist.view.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import it.leva.levapokemonlist.R
import it.leva.levapokemonlist.extension.loadImage

class ImageAdapter(private val images: List<String?>?, private val context: Context) :
    RecyclerView.Adapter<ImageAdapter.StatsViewHolder>() {

    inner class StatsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: AppCompatImageView = view.findViewById(R.id.img_other_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return StatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        images?.get(position)?.let { image ->
            if (image.isNotEmpty())
                holder.img.loadImage(context, image)
        }

    }

    override fun getItemCount() = images?.size ?: 0
}