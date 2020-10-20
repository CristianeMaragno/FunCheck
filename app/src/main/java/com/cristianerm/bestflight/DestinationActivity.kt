package com.cristianerm.bestflight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_destination.*
import kotlinx.android.synthetic.main.fragment_add_destinations.view.*
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class DestinationActivity : AppCompatActivity() {

    private lateinit var destinationResultsRecyclerViewAdapter: DestinationResultsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)

        val destination = intent.getStringExtra("destination")
        val date_go = intent.getStringExtra("date go")
        val date_back = intent.getStringExtra("date back")

        val airline = intent.getStringExtra("airline")

        text_destination_destination.setText(destination)
        text_date_go_destination.setText(date_go)
        text_date_back_destination.setText(date_back)

        close_destination_result.setOnClickListener({
            val intent = Intent(this, MainFunctionalitiesActivity::class.java)
            startActivity(intent)
        })

        recyclerView_destination_results.apply {
            layoutManager = LinearLayoutManager(context)
            destinationResultsRecyclerViewAdapter = DestinationResultsRecyclerViewAdapter()
            adapter = destinationResultsRecyclerViewAdapter
        }

        val list = ArrayList<DestinationResultInformation>()
        list.add(
            DestinationResultInformation(
                "14:50 - 18:30",
                "07:20 - 11:08",
                "R$ 250,00"
            )
        )
        list.add(
            DestinationResultInformation(
                "15:50 - 18:30",
                "09:20 - 11:08",
                "R$ 120,00"
            )
        )
        list.add(
            DestinationResultInformation(
                "10:50 - 18:30",
                "02:20 - 11:08",
                "R$ 599,00"
            )
        )

        destinationResultsRecyclerViewAdapter.submitList(list)


        retrieveWebInfo()
    }

    private fun retrieveWebInfo(){
        thread{
            // network call, so run it in the background
            val doc =
                Jsoup.connect("https://www.wildaboutmovies.com/2019_movies/")
                    .get()

            val movieGrid = doc.getElementsByClass("post-grid")
            val movieItems = movieGrid[0].getElementsByTag("a")

            //val movieList = ArrayList<MovieListItem>()

            for(movieItem in movieItems){
                val movieName = movieItem.text()
                Log.v("DestinatioActivity", "TEXT: " + movieName)
                //val movieImageUrl = movieItem.getElementsByTag("img")[0].absUrl("data-original").toString()
                //movieList.add(MovieListItem(movieName, movieImageUrl))
            }

            // can't access UI elements from the background thread
            /*this.runOnUiThread{
                val recyclerViewAdapter = RecyclerViewAdapter(movieList)
                val linearLayoutManager = LinearLayoutManager(this)

                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = recyclerViewAdapter
            }*/
        }
    }
}
