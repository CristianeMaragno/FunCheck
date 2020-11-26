package com.cristianerm.bestflight

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_destination.*
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class DestinationActivity : AppCompatActivity() {

    private lateinit var destinationResultsRecyclerViewAdapter: DestinationResultsRecyclerViewAdapter
    private val list = ArrayList<DestinationResultInformation>()

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

        //RetrieveWebInfo()
        list.add(
            DestinationResultInformation(
                "Attraction name 1"
            )
        )
        list.add(
            DestinationResultInformation(
                "Attraction name 2"
            )
        )
        list.add(
            DestinationResultInformation(
                "Attraction name 3"
            )
        )

        destinationResultsRecyclerViewAdapter.submitList(list)
    }

    private fun RetrieveWebInfo(){
        thread{
            val url = "https://www.tripadvisor.com.br/Attractions-g187895-Activities-Florence_Tuscany.html"
            val doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").timeout(10*1000).get()

            val attractionGrid = doc.getElementsByClass("_1h6gevVw")
            val attractions = attractionGrid[0].getElementsByTag("h3")

            for(attraction in attractions){
                val attraction_name = attraction.text()
                Log.v("DestinatioActivity", "TEXT: " + attraction_name)
                list.add(
                    DestinationResultInformation(
                        attraction_name
                    )
                )
            }
        }
    }

}
