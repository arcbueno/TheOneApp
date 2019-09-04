package com.arcbueno.theoneapp.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.arcbueno.theoneapp.Models.CharacterData
import com.arcbueno.theoneapp.Models.MovieData
import com.arcbueno.theoneapp.R
import com.arcbueno.theoneapp.Service.RetrofitInitializer
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Characters : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var nameArray: MutableList<String>
    lateinit var idArray: MutableList<String>
    lateinit var characterData: CharacterData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        nameArray = mutableListOf<String>()
        idArray = mutableListOf<String>()

        var listView = findViewById<ListView>(R.id.characterListId)

        val progBar = findViewById<ProgressBar>(R.id.progressBarCharacter)

        progBar.visibility = View.VISIBLE

        var call = RetrofitInitializer().apiService().listCharacter()

        call.enqueue(object: Callback<CharacterData?> {
            override fun onResponse(call: Call<CharacterData?>?,
                                    response: Response<CharacterData?>?) {

                response?.body()?.let {
                    val character: CharacterData? = it
                    characterData = it
                    if (character != null) {

                        var getData = character.docs

                        if (getData != null) {
                            for(item in getData){
                                nameArray.add(item.name)
                                idArray.add(item._id)
                            }
                        }
                        setListView(listView)
                        progBar.visibility = View.GONE


                    }
                }

            }

            override fun onFailure(call: Call<CharacterData?>, t: Throwable) {

                textView.text = t.toString()
                Toast.makeText(this@Characters,"Is not possible to get informations", Toast.LENGTH_SHORT).show()

            }
        })

        listView.setOnItemClickListener{ _, _, position, _ ->
            var intent = Intent(this, CharacterSpec::class.java)
            intent.putExtra("data", Gson().toJson(characterData.docs?.get(position)))
            startActivity(intent)

        }
    }
    fun setListView(androidListView: ListView){
        var list = ArrayList<HashMap<String, String>>()

        for (i in 0 until nameArray.size){
            var hm = HashMap<String, String>()
            hm.put("name", nameArray[i])
            hm.put("id", idArray[i])
            hm.put("img", R.drawable.ic_icons8_one_ring_.toString())
            list.add(hm)
        }

        var from = arrayOf("name","id","img")

        var to = intArrayOf(
            R.id.listview_item_title,
            R.id.listview_item_short_description,
            R.id.listview_image
        )

        val simpleAdapter = SimpleAdapter(baseContext, list, R.layout.listview_activity, from, to)
        androidListView.adapter = simpleAdapter

    }
}
