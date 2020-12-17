package com.cristianerm.funcheck

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_destination.*
import org.jsoup.Jsoup
import java.io.IOException


class DestinationActivity : AppCompatActivity() {

    private lateinit var destinationResultsRecyclerViewAdapter: DestinationResultsRecyclerViewAdapter
    private val list = ArrayList<DestinationResultInformation>()
    private var loader: AsyncTask<Void, Void, ArrayList<DestinationResultInformation>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)

        val destination = intent.getStringExtra("destination")
        val date_go = intent.getStringExtra("date go")
        val date_back = intent.getStringExtra("date back")

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

        destinationResultsRecyclerViewAdapter.submitList(list)

        loader = LoadAttractions(this)
        loader!!.execute()
    }

    inner class LoadAttractions(var activity: AppCompatActivity?): AsyncTask<Void, Void, ArrayList<DestinationResultInformation>>(){

        override fun doInBackground(vararg params: Void?): ArrayList<DestinationResultInformation> {
            try {
                val url = getUrl()
                val doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").timeout(10*1000).get()

                list.add(DestinationResultInformation("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/13/f5/22/8f/perlan-stands-upon-reykjavik.jpg?w=300&h=-1&s=1"))

                //val attractionGrid = doc.getElementsByClass("ZVAUHZqh")
                val attractions = doc.getElementsByTag("img")
                Log.v("DestinationActivity", "TEXT: " + "DOC: " + doc)

                for (element in attractions) {
                    val src = element.absUrl("src")
                    Log.v("DestinationActivity", "TEXT: " + "src: " + src)

                    /*if (src.isNotEmpty()){
                        list.add(
                            DestinationResultInformation(
                                src
                            )
                        )
                    }*/
                }

                /*val attractionGrid = doc.getElementsByClass("_1h6gevVw")
                val attractions = attractionGrid[0].getElementsByTag("h3")

                for(attraction in attractions){
                    val attraction_name = attraction.text()

                    Log.v("DestinatioActivity", "TEXT: " + attraction_name.toString())

                    list.add(
                        DestinationResultInformation(
                            attraction_name
                        )
                    )
                }*/

            }catch (e: IOException){
                e.printStackTrace()
            }
            return list
        }

        override fun onPostExecute(result: ArrayList<DestinationResultInformation>?) {
            destinationResultsRecyclerViewAdapter.notifyDataSetChanged()
        }
    }

    private fun getUrl(): String? {
        val destination = intent.getStringExtra("destination")

        val numbersMap = mapOf("Paris, France" to "https://www.tripadvisor.com.br/Attractions-g187147-Activities-Paris_Ile_de_France.html",
            "New York, USA" to "https://www.tripadvisor.com.br/Attractions-g60763-Activities-New_York_City_New_York.html",
            "Rome, Italy" to "https://www.tripadvisor.com.br/Attractions-g187791-Activities-Rome_Lazio.html",
            "London, UK" to "https://www.tripadvisor.com.br/Attractions-g186338-Activities-London_England.html",
            "Tokyo, Japan" to "https://www.tripadvisor.com.br/Attractions-g298184-Activities-Tokyo_Tokyo_Prefecture_Kanto.html",
            "Lisbon, Portugal" to "https://www.tripadvisor.com.br/Attractions-g189158-Activities-Lisbon_Lisbon_District_Central_Portugal.html",
            "Barcelona, Spain" to "https://www.tripadvisor.com.br/Attractions-g187497-Activities-Barcelona_Catalonia.html",
            "Honolulu, Hawaii" to "https://www.tripadvisor.com.br/Attractions-g60982-Activities-Honolulu_Oahu_Hawaii.html"/*,
            "Istanbul, Turkey" to "https://www.tripadvisor.com.br/Attractions-g293974-Activities-Istanbul.html",
            "Bangkok, Thailand" to "https://www.tripadvisor.com.br/Attractions-g293916-Activities-Bangkok.html",
            "Agra, India" to "https://www.tripadvisor.com.br/Attractions-g297683-Activities-Agra_Agra_District_Uttar_Pradesh.html",
            "Cairo, Egypt" to "https://www.tripadvisor.com.br/Attractions-g294201-Activities-Cairo_Cairo_Governorate.html",
            "Helsinki, Finland" to "https://www.tripadvisor.com.br/Attractions-g189934-Activities-Helsinki_Uusimaa.html",
            "Ubud, Bali, Indonesia" to "https://www.tripadvisor.com.br/Attractions-g297701-Activities-Ubud_Gianyar_Regency_Bali.html",
            "Berlin, Germany" to "https://www.tripadvisor.com.br/Attractions-g187323-Activities-Berlin.html",
            "Shanghai, China" to "https://www.tripadvisor.com.br/Attractions-g308272-Activities-Shanghai.html",
            "Las Vegas, USA" to "https://www.tripadvisor.com.br/Attractions-g45963-Activities-Las_Vegas_Nevada.html",
            "Jerusalem, Israel" to "https://www.tripadvisor.com.br/Attractions-g293983-Activities-Jerusalem_Jerusalem_District.html",
            "Venice, Italy" to "https://www.tripadvisor.com.br/Attractions-g187870-Activities-Venice_Veneto.html",
            "Cape Town, South Africa" to "https://www.tripadvisor.com.br/Attractions-g1722390-Activities-Cape_Town_Western_Cape.html",
            "Rio de Janeiro, Brazil" to "https://www.tripadvisor.com.br/Attractions-g303506-Activities-Rio_de_Janeiro_State_of_Rio_de_Janeiro.html",
            "Singapore, Singapore" to "https://www.tripadvisor.com.br/Attractions-g294265-Activities-Singapore.html",
            "Toronto, Canada" to "https://www.tripadvisor.com.br/Attractions-g155019-Activities-Toronto_Ontario.html",
            "Seoul, South Korea" to "https://www.tripadvisor.com.br/Attractions-g294197-Activities-Seoul.html",
            "Casablanca, Morocco" to "https://www.tripadvisor.com.br/Attractions-g293732-Activities-Casablanca_Casablanca_Settat.html",
            "Mosco, Russia" to "https://www.tripadvisor.com.br/Attractions-g298484-Activities-Moscow_Central_Russia.html",
            "Sydney, Australia" to "https://www.tripadvisor.com.br/Attractions-g255060-Activities-Sydney_New_South_Wales.html",
            "Lima, Peru" to "https://www.tripadvisor.com.br/Attractions-g294316-Activities-Lima_Lima_Region.html",
            "Beijing, China" to "https://www.tripadvisor.com.br/Attractions-g294212-Activities-Beijing.html",
            "Buenos Aires, Argentina" to "https://www.tripadvisor.com.br/Attractions-g312741-Activities-Buenos_Aires_Capital_Federal_District.html",
            "Edinburgh, Scotland" to "https://www.tripadvisor.com.br/Attractions-g186525-Activities-Edinburgh_Scotland.html",
            "Los Angeles, USA" to "https://www.tripadvisor.com.br/Attractions-g32655-Activities-Los_Angeles_California.html",
            "Copenhagen, Denmark" to "https://www.tripadvisor.com.br/Attractions-g189541-Activities-Copenhagen_Zealand.html",
            "Osaka, Japan" to "https://www.tripadvisor.com.br/Attractions-g298566-Activities-Osaka_Osaka_Prefecture_Kinki.html",
            "Auckland, New Zealand" to "https://www.tripadvisor.com.br/Attractions-g1811027-Activities-Auckland_North_Island.html",
            "Mumbai, India" to "https://www.tripadvisor.com.br/Attractions-g304554-Activities-Mumbai_Maharashtra.html",
            "Dubai, United Arab Emirates" to "https://www.tripadvisor.com.br/Attractions-g295424-Activities-Dubai_Emirate_of_Dubai.html",
            "Munich, Germany" to "https://www.tripadvisor.com.br/Attractions-g187309-Activities-Munich_Upper_Bavaria_Bavaria.html",
            "Hong Kong, China" to "https://www.tripadvisor.com.br/Attractions-g294217-Activities-Hong_Kong.html",
            "Qatar, State of Qatar" to "https://www.tripadvisor.com.br/Attractions-g294008-Activities-Qatar.html",
            "Seattle, USA" to "https://www.tripadvisor.com.br/Attractions-g60878-Activities-Seattle_Washington.html",
            "Oslo, Norway" to "https://www.tripadvisor.com.br/Attractions-g190479-Activities-Oslo_Eastern_Norway.html",
            "Bath, UK" to "https://www.tripadvisor.com.br/Attractions-g186370-Activities-Bath_Somerset_England.html",
            "Athens, Greece" to "https://www.tripadvisor.com.br/Attractions-g189400-Activities-Athens_Attica.html",
            "Vienna, Austria" to "https://www.tripadvisor.com.br/Attractions-g190454-Activities-Vienna.html",
            "Madrid, Spain" to "https://www.tripadvisor.com.br/Attractions-g187514-Activities-Madrid.html",
            "Dublin, Republic of Ireland" to "https://www.tripadvisor.com.br/Attractions-g186605-Activities-Dublin_County_Dublin.html",
            "Florence, Italy" to "https://www.tripadvisor.com.br/Attractions-g187895-Activities-Florence_Tuscany.html",
            "Bruges, Belgium" to "https://www.tripadvisor.com.br/Attractions-g188671-Activities-Bruges_West_Flanders_Province.html",
            "Krakow, Poland" to "https://www.tripadvisor.com.br/Attractions-g274772-Activities-Krakow_Lesser_Poland_Province_Southern_Poland.html",
            "Amsterdam, Holland" to "https://www.tripadvisor.com.br/Attractions-g188590-Activities-Amsterdam_North_Holland_Province.html",
            "Stockholm, Sweden" to "https://www.tripadvisor.com.br/Attractions-g189852-Activities-Stockholm.html",
            "Ljubljana, Slovenia" to "https://www.tripadvisor.com.br/Attractions-g274873-Activities-Ljubljana_Upper_Carniola_Region.html",
            "Hanoi, Vietnam" to "https://www.tripadvisor.com.br/Attractions-g293924-Activities-Hanoi.html",
            "Wellington, New Zealand" to "https://www.tripadvisor.com.br/Attractions-g255115-Activities-Wellington_Greater_Wellington_North_Island.html",
            "Nice, France" to "https://www.tripadvisor.com.br/Attractions-g187234-Activities-Nice_French_Riviera_Cote_d_Azur_Provence_Alpes_Cote_d_Azur.html",
            "Vancouver, Canada" to "https://www.tripadvisor.com.br/Attractions-g154943-Activities-Vancouver_British_Columbia.html",
            "Kiev, Ukraine" to "https://www.tripadvisor.com.br/Attractions-g294474-Activities-Kyiv_Kiev.html",
            "Hallstatt, Austria" to "https://www.tripadvisor.com.br/Attractions-g190427-Activities-Hallstatt_Upper_Austria.html",
            "Havana, Cuba" to "https://www.tripadvisor.com.br/Attractions-g147271-Activities-Havana_Ciudad_de_la_Habana_Province_Cuba.html",
            "Mexico City, Mexico" to "https://www.tripadvisor.com.br/Attractions-g150800-Activities-Mexico_City_Central_Mexico_and_Gulf_Coast.html",
            "Beirut, Lebanon" to "https://www.tripadvisor.com.br/Attractions-g294005-Activities-Beirut.html",
            "Salzburg, Austria" to "https://www.tripadvisor.com.br/Attractions-g190441-Activities-Salzburg_Austrian_Alps.html",
            "Sao Paolo, Brazil" to "https://www.tripadvisor.com.br/Attractions-g303631-Activities-Sao_Paulo_State_of_Sao_Paulo.html",
            "Prague, Czech Republic" to "https://www.tripadvisor.com.br/Attractions-g274707-Activities-Prague_Bohemia.html",
            "Kyoto, Japan" to "https://www.tripadvisor.com.br/Attractions-g298564-Activities-Kyoto_Kyoto_Prefecture_Kinki.html",
            "Marrakech, Morocco" to "https://www.tripadvisor.com.br/Attractions-g293734-Activities-Marrakech_Marrakech_Safi.html",
            "St Petersburg, Russia" to "https://www.tripadvisor.com.br/Attractions-g298507-Activities-St_Petersburg_Northwestern_District.html",
            "San Francisco, USA" to "https://www.tripadvisor.com.br/Attractions-g60713-Activities-San_Francisco_California.html",
            "Oxford, UK" to "https://www.tripadvisor.com.br/Attractions-g186361-Activities-Oxford_Oxfordshire_England.html",
            "Melbourne, Australia" to "https://www.tripadvisor.com.br/Attractions-g255100-Activities-Melbourne_Victoria.html",
            "Kingston, Jamaica" to "https://www.tripadvisor.com.br/Attractions-g147310-Activities-Kingston_Kingston_Parish_Jamaica.html",
            "Amman, Jordan" to "https://www.tripadvisor.com.br/Attractions-g293986-Activities-Amman_Amman_Governorate.html",
            "Lagos, Nigeria" to "https://www.tripadvisor.com.br/Attractions-g304026-Activities-Lagos_Lagos_State.html",
            "New Orleans, USA" to "https://www.tripadvisor.com.br/Attractions-g60864-Activities-New_Orleans_Louisiana.html",
            "San Jose, Costa Rica" to "https://www.tripadvisor.com.br/Attractions-g309293-Activities-San_Jose_San_Jose_Metro_Province_of_San_Jose.html",
            "Tallinn, Estonia" to "https://www.tripadvisor.com.br/Attractions-g274958-Activities-Tallinn_Harju_County.html",
            "Suva Fiji" to "https://www.tripadvisor.com.br/Attractions-g294337-Activities-Suva_Viti_Levu.html",
            "Jarkarta, Indonesia" to "https://www.tripadvisor.com.br/Attractions-g294225-Activities-Indonesia.html",
            "New Delhi, India" to "https://www.tripadvisor.com.br/Attractions-g304551-Activities-New_Delhi_National_Capital_Territory_of_Delhi.html",
            "Adelaide, Australia" to "https://www.tripadvisor.com.br/Attractions-g255093-Activities-Adelaide_Greater_Adelaide_South_Australia.html",
            "Tripoli, Libya" to "https://www.tripadvisor.com.br/Attractions-g293807-Activities-Tripoli_Tripoli_District.html",
            "Male, Maldives" to "https://www.tripadvisor.com.br/Attractions-g298571-Activities-Male.html",
            "Luxembourg, Luxembourg" to "https://www.tripadvisor.com.br/Attractions-g190356-Activities-Luxembourg_City.html",
            "Kuala Lumpur, Malaysia" to "https://www.tripadvisor.com.br/Attractions-g298570-Activities-Kuala_Lumpur_Wilayah_Persekutuan.html",
            "Glasgow, Scotland" to "https://www.tripadvisor.com.br/Attractions-g186534-Activities-Glasgow_Scotland.html",
            "Valletta, Malta" to "https://www.tripadvisor.com.br/Attractions-g190328-Activities-Valletta_Island_of_Malta.html",
            "Kathmandu, Nepal" to "https://www.tripadvisor.com.br/Attractions-g293890-Activities-Kathmandu_Kathmandu_Valley_Bagmati_Zone_Central_Region.html",
            "Brussels, Belgium" to "https://www.tripadvisor.com.br/Attractions-g188644-Activities-Brussels.html",
            "Panama City, Panama" to "https://www.tripadvisor.com.br/AttractionProductReview-g294480-d12468437-Panama_City_and_Panama_Canal_Tour-Panama_City_Panama_Province.html",
            "Bucharest, Romania" to "https://www.tripadvisor.com.br/Attractions-g294458-Activities-Bucharest.html",
            "Cardiff, Wales" to "https://www.tripadvisor.com.br/Attractions-g186460-Activities-Cardiff_South_Wales_Wales.html",
            "Manila, Philippines" to "https://www.tripadvisor.com.br/Attractions-g298573-Activities-Manila_Metro_Manila_Luzon.html",
            "Castries, Saint Lucia" to "https://www.tripadvisor.com.br/Attractions-g147343-Activities-Castries_Castries_Quarter_St_Lucia.html",
            "Apia, Samoa" to "https://www.tripadvisor.com.br/Attractions-g312868-Activities-Apia_Upolu.html",
            "Bern, Switzerland" to "https://www.tripadvisor.com.br/Attractions-g188052-Activities-Bern_Bern_Mittelland_District_Canton_of_Bern.html",
            "Taipei, Taiwan" to "https://www.tripadvisor.com.br/Attractions-g293913-Activities-Taipei.html",
            "Hobart, Australia" to "https://www.tripadvisor.com.br/Attractions-g255097-Activities-Hobart_Greater_Hobart_Tasmania.html",
            "Budapest, Hungary" to "https://www.tripadvisor.com.br/Attractions-g274887-Activities-Budapest_Central_Hungary.html",
            "Reykjavik, Iceland" to "https://www.tripadvisor.com.br/Attractions-g189970-Activities-Reykjavik_Capital_Region.html"*/
            )

        return numbersMap[destination]
    }

}

