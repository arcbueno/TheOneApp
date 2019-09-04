package com.arcbueno.theoneapp.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.arcbueno.theoneapp.Models.MovieData
import com.arcbueno.theoneapp.R
import com.arcbueno.theoneapp.Service.RetrofitInitializer
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Movies : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var nameArray: MutableList<String>
    lateinit var idArray: MutableList<String>
    lateinit var movieData: MovieData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        nameArray = mutableListOf<String>()
        idArray = mutableListOf<String>()
        var listView = findViewById<ListView>(R.id.movieListId)

        val progBar = findViewById<ProgressBar>(R.id.progressBarMovie)

        progBar.visibility = View.VISIBLE
        var call = RetrofitInitializer().apiService().listMovie()

        call.enqueue(object: Callback<MovieData?> {
            override fun onResponse(call: Call<MovieData?>?,
                                    response: Response<MovieData?>?) {

                response?.body()?.let {
                    val movie: MovieData = it
                    movieData = it
                    if (movie != null) {

                        var getData = movie.docs
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

            override fun onFailure(call: Call<MovieData?>, t: Throwable) {

                Toast.makeText(this@Movies,"Is not possible to get information", Toast.LENGTH_SHORT).show()
                progBar.visibility = View.GONE
            }
        })


        listView.setOnItemClickListener{ _, _, position, _ ->
            var intent = Intent(this, MovieSpec::class.java)
            intent.putExtra("data", Gson().toJson(movieData.docs?.get(position)))
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
