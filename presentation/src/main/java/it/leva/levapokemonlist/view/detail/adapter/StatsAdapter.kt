package it.leva.levapokemonlist.view.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.leva.domain.model.Stat
import it.leva.levapokemonlist.R

class StatsAdapter (private val statsList: List<Stat>?):
    RecyclerView.Adapter<StatsAdapter.StatsViewHolder>(){

    inner class StatsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtStatName: TextView = view.findViewById(R.id.txt_stat_name)
        val txtStatValue: TextView = view.findViewById(R.id.txt_stat_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stat_item, parent, false)
        return StatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        statsList?.get(position)?.let {
            holder.txtStatName.text = it.name
            holder.txtStatValue.text = "${it.baseStat}"
        }

    }

    override fun getItemCount() = statsList?.size ?: 0
}