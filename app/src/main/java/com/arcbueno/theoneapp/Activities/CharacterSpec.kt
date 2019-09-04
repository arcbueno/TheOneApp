package com.arcbueno.theoneapp.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.arcbueno.theoneapp.Models.Character
import com.arcbueno.theoneapp.Models.Movie
import com.arcbueno.theoneapp.R
import com.google.gson.Gson

class CharacterSpec : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_spec)

        var receivedData = intent.getStringExtra("data")
        var data : Character = Gson().fromJson(receivedData, Character::class.java)

        var nameView = findViewById<TextView>(R.id.characterNameId)
        var dataView = findViewById<TextView>(R.id.characterDataId)

        nameView.text = data.name

        dataView.text = //"ID: ${data._id} " +
            "\nHeight: ${data.height} " +
                    "\nBirth: ${data.birth} millions " +
                    "\nDeath: ${data.death} millions " +
                    "\nGender: ${data.gender}" +
                    "\nHair: ${data.hair} " +
                    "\nRace: ${data.race} " +
                    "\nSpouse: ${data.spouse} " +
                    "\nRealm: ${data.realm} " +
                    "\nWikiUrl: ${data.wikiUrl}"

    }
}
