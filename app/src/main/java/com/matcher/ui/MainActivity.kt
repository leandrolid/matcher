package com.matcher.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.matcher.R
import com.matcher.data.MatchesApi
import com.matcher.databinding.ActivityMainBinding
import com.matcher.domain.Match
import com.matcher.ui.adapter.MatchesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var matchesAdapter: MatchesAdapter
    private lateinit var matches: List<Match>
    private lateinit var matchesApi: MatchesApi
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupHttpClient()
        setupMatchesList()
    }

    private fun setupHttpClient() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://digitalinnovationone.github.io/matches-simulator-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        matchesApi = retrofit.create(MatchesApi::class.java)
    }

    private fun setupMatchesList() {
        binding.rvMatchesList.layoutManager = LinearLayoutManager(this)
        binding.rvMatchesList.setHasFixedSize(true)

        matchesApi.getMatches().enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    matches = response.body()!!
                    matchesAdapter = MatchesAdapter(matches)
                    binding.rvMatchesList.adapter = matchesAdapter
                } else {
                    showErrorMessage(getString(R.string.default_error_message))
                }
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                Log.i("getMatches error", t.message.toString())
                showErrorMessage(getString(R.string.default_error_message))
            }
        })
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.fabSimulate, message, Snackbar.LENGTH_SHORT).show()
    }
}
