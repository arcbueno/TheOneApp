package com.arcbueno.theoneapp.Models

class Character(
    var _id: String,
    var name: String,
    var height: String,
    var race: String,
    var gender: String,
    var birth: String,
    var spouse: String,
    var death: String,
    var realm: String,
    var hair: String,
    var wikiUrl: String
)


class CharacterData{
    var docs: List<Character>? = null

}


