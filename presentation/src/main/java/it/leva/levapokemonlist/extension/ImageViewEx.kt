package it.leva.levapokemonlist.extension

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImage(context: Context?, url: String?) {
    context?.let {
        val circularProgressDrawable = CircularProgressDrawable(it)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        url?.let { safeUrl ->
            Glide.with(it)
                .load(safeUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(circularProgressDrawable)
                .into(this)
        }

    }

}