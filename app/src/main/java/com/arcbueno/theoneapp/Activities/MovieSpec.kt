package com.arcbueno.theoneapp.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.arcbueno.theoneapp.Models.Movie
import com.arcbueno.theoneapp.R
import com.google.gson.Gson

class MovieSpec : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_spec)

        var receivedData = intent.getStringExtra("data")
        var data : Movie = Gson().fromJson(receivedData, Movie::class.java)

        var nameView = findViewById<TextView>(R.id.movieNameId)
        var dataView = findViewById<TextView>(R.id.movieDataId)

        nameView.text = data.name

        dataView.text = //"ID: ${data._id} " +
                "\nRuntime: ${data.runtimeInMinutes} minutes " +
                "\nBudget: ${data.budgetInMillions} millions " +
                "\nBox Office Revenue: ${data.boxOfficeRevenueInMillions} millions " +
                "\nOscar Nominations: ${data.academyAwardNominations}" +
                "\nOscar Wins: ${data.academyAwardWins}"

    }
}
