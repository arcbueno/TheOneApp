package com.arcbueno.theoneapp.Models

class Movie(
    var _id: String,
    var name: String,
    var runtimeInMinutes: Double,
    var budgetInMillions: Double,
    var boxOfficeRevenueInMillions: Double,
    var academyAwardNominations: Double,
    var academyAwardWins: Double
)


class MovieData{
    var docs: List<Movie>? = null

}


