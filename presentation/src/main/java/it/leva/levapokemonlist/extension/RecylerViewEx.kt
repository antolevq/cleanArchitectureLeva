package it.leva.levapokemonlist.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollListener(listener: () -> Unit) {
    var loading = true
    this.addOnScrollListener(object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            if (dy > 0) {
                val visibleItemCount = layoutManager.getChildCount();
                val totalItemCount = layoutManager.getItemCount();
                val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false
                        listener()
                        loading = true
                    }
                }
            }
        }
    })
}