package com.matcher.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matcher.databinding.MatchItemBinding
import com.matcher.domain.Match
import com.matcher.ui.DetailActivity


class MatchesAdapter(var matches: List<Match>):
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
        if (match.firstTeam.score != null) {
            viewHolder.binding.tvFirstTeamGoals.text = match.firstTeam.score.toString()
        }

        Glide.with(context).load(match.secondTeam.image).circleCrop().into(viewHolder.binding.ivSecondTeamImage)
        viewHolder.binding.tvSecondTeamName.text = match.secondTeam.name
        if (match.secondTeam.score != null) {
            viewHolder.binding.tvSecondTeamGoals.text = match.secondTeam.score.toString()
        }

        viewHolder.itemView.setOnClickListener { view ->
            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra(DetailActivity.Extras.MATCH, match)
            context.startActivity(intent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = matches.size

}
