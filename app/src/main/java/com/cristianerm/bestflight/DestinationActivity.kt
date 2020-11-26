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


        RetrieveWebInfo()
    }

    private fun RetrieveWebInfo(){
        thread{
            val url = "https://www.tripadvisor.com.br/Attractions-g303631-Activities-Sao_Paulo_State_of_Sao_Paulo.html"
            val doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").timeout(10*1000).get()

            val attractionGrid = doc.getElementsByClass("_1h6gevVw")
            val attractions = attractionGrid[0].getElementsByTag("h3")

            for(attraction in attractions){
                val attraction_name = attraction.text()
                Log.v("DestinatioActivity", "TEXT: " + attraction_name)
            }
        }
    }
}
