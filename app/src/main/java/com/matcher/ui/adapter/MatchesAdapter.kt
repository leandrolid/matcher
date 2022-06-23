package com.matcher.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matcher.databinding.MatchItemBinding
import com.matcher.domain.Match


class MatchesAdapter(private val matches: List<Match>):
    RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    class ViewHolder(binding: MatchItemBinding): RecyclerView.ViewHolder(binding.root) {
        val binding: MatchItemBinding

        init {
            this.binding = binding
        }
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = MatchItemBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        val match = matches[position]

        Glide.with(context).load(match.firstTeam.image).circleCrop().into(viewHolder.binding.ivFirstTeamImage)
        viewHolder.binding.tvFirstTeamName.text = match.firstTeam.name
        Glide.with(context).load(match.secondTeam.image).circleCrop().into(viewHolder.binding.ivSecondTeamImage)
        viewHolder.binding.tvSecondTeamName.text = match.secondTeam.name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = matches.size

}
