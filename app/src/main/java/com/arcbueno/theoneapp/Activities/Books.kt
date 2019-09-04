package com.arcbueno.theoneapp.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.arcbueno.theoneapp.Models.BooksData
import com.arcbueno.theoneapp.R
import com.arcbueno.theoneapp.Service.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Books : AppCompatActivity() {


    lateinit var textView: TextView
    lateinit var nameArray: MutableList<String>
    lateinit var idArray: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        nameArray = mutableListOf<String>()
        idArray = mutableListOf<String>()
        var listView = findViewById(R.id.booksListId) as ListView;
        var progBar = findViewById(R.id.progressBarBook) as ProgressBar

        progBar.visibility = View.VISIBLE

        var call = RetrofitInitializer().apiService().listBook()


        call.enqueue(object: Callback<BooksData?> {
            override fun onResponse(call: Call<BooksData?>?,
                                    response: Response<BooksData?>?) {

                response?.body()?.let {
                    val book: BooksData? = it
                    if (book != null) {

                        var getData = book.docs
                        var text = ""
                        for(item in getData){
                            nameArray.add(item.name)
                            idArray.add(item._id)

                        }
                        setListView(listView)
                        progBar.visibility = View.GONE

                    }
                }

            }

            override fun onFailure(call: Call<BooksData?>, t: Throwable) {

                progBar.visibility = View.GONE
                textView.text = t.toString()
                Toast.makeText(this@Books,"Is not possible to get any information", Toast.LENGTH_SHORT).show()

            }
        })



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
