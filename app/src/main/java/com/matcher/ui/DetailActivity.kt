package com.matcher.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.matcher.databinding.ActivityDetailBinding
import com.matcher.domain.Match

class DetailActivity : AppCompatActivity() {
    object Extras {
        const val MATCH = "EXTRA_MATCH"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let { match ->
            supportActionBar?.title = match.place.name
            Glide.with(this).load(match.place.image).into(binding.ivPlace)
            binding.tvDescription.text = match.description

            Glide.with(this).load(match.firstTeam.image).circleCrop().into(binding.ivFirstTeamImage)
            binding.tvFirstTeamName.text = match.firstTeam.name
            binding.rbFirstTeamStars.rating = match.firstTeam.stars.toFloat()
            if (match.firstTeam.score != null) {
                binding.tvFirstTeamGoals.text = match.firstTeam.score.toString()

            }

            Glide.with(this).load(match.secondTeam.image).circleCrop().into(binding.ivSecondTeamImage)
            binding.tvSecondTeamName.text = match.secondTeam.name
            binding.rbSecondTeamStars.rating = match.secondTeam.stars.toFloat()
            if (match.secondTeam.score != null) {
                binding.tvSecondTeamGoals.text = match.secondTeam.score.toString()
            }
        }
    }
}