package com.arcbueno.theoneapp.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import com.arcbueno.theoneapp.R


class MainActivity : AppCompatActivity() {

    var itensName = arrayOf("Books","Movies","Characters","Quotes","Chapters")
    var itensIcon = intArrayOf(
        R.drawable.ic_book_24dp,
        R.drawable.ic_movies_24dp,
        R.drawable.ic_characters_menu_24dp,
        R.drawable.ic_icons8_one_ring_,
        R.drawable.ic_icons8_one_ring_
    )
    var itensDesc = arrayOf(
        "List of all books",
        "List of all movies",
        "List of all characters",
        "List of all quotes from TLOTR movies",
        "List of all chapters from TLOTR books"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val androidListView = findViewById(R.id.listId) as ListView
        setListView(androidListView)

        androidListView.setOnItemClickListener{ _, _, position, _ ->

            Toast.makeText(this, "Clicked on "+ itensName[position], Toast.LENGTH_SHORT).show()
            var changeActivity: Boolean = false
            var intent = Intent()
            when(position){
                0 -> {
                    intent = Intent(this, Books::class.java)
                    changeActivity = true
                }
                1 -> {
                    intent = Intent(this, Movies::class.java)
                    changeActivity = true
                }
                2 -> {
                    intent = Intent(this, Characters::class.java)
                    changeActivity = true
                }
                else ->{

                    Toast.makeText(this, "Wait for the next update", Toast.LENGTH_SHORT).show()
                }
            }
            if(changeActivity){
                startActivity(intent)
            }



        }

    }

    fun setListView(androidListView: ListView){
        var list = ArrayList<HashMap<String, String>>()

        for (i in 0 until itensDesc.size){
            var hm = HashMap<String, String>()
            hm.put("title", itensName[i])
            hm.put("desc", itensDesc[i])
            hm.put("img", itensIcon[i].toString())
            list.add(hm)
        }

        var from = arrayOf("title","desc","img")

        var to = intArrayOf(
            R.id.listview_item_title,
            R.id.listview_item_short_description,
            R.id.listview_image
        )

        val simpleAdapter = SimpleAdapter(baseContext, list, R.layout.listview_activity, from, to)
        androidListView.adapter = simpleAdapter

    }
}

