package com.cristianerm.funcheck

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_new_destination.*
import kotlinx.android.synthetic.main.fragment_new_destination.view.*
import java.text.SimpleDateFormat
import java.util.*

class AddNewDestinationFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter_origin = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, locations)
        origin_edit_text_add_destination.setAdapter(adapter_origin)

        var adapter_destination = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, locations)
        destination_edit_text_add_destination.setAdapter(adapter_destination)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_destination, container, false)

        view.close_add_destination.setOnClickListener({
            val intent = Intent(activity, MainFunctionalitiesActivity::class.java)
            startActivity(intent)
        })

        view.pick_date_add_destination_button.setOnClickListener({
            openDatePicker()
        })

        view.check_box_just_go_add_destination.setOnClickListener({
            if(check_box_just_go_add_destination.isChecked()){
                back_date_edit_text_add_destination.isEnabled =false
            }else{
                back_date_edit_text_add_destination.isEnabled =true
            }
        })

        view.go_date_edit_text_add_destination.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.length == 2 || s.length == 5) {
                    go_date_edit_text_add_destination.append("/")
                }
            }
        })

        view.back_date_edit_text_add_destination.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.length == 2 || s.length == 5) {
                    back_date_edit_text_add_destination.append("/")
                }
            }
        })

        return view
    }

    private fun openDatePicker(){
        if(check_box_just_go_add_destination.isChecked()){
            openDatePickerOneDate()
        }else{
            openDatePickerTwoDate()
        }
    }

    private fun openDatePickerOneDate(){
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(childFragmentManager, picker.toString())

        picker.addOnPositiveButtonClickListener {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            var date_epoch = it
            var date = sdf.format(Date(date_epoch)).toString()
            go_date_edit_text_add_destination.setText(date)
        }
    }

    private fun openDatePickerTwoDate(){
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        //builder.setTheme(R.attr.materialCalendarFullscreenTheme)
        val picker = builder.build()
        picker.show(childFragmentManager, picker.toString())

        picker.addOnPositiveButtonClickListener {
            //Toast.makeText(context, "Date String = ${picker.headerText}::  Date epoch values::${it.first}:: to :: ${it.second}", Toast.LENGTH_SHORT).show()
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            var date_epoch_departure = it.first
            var date_epoch_back = it.second
            var date_departure = sdf.format(date_epoch_departure?.let { it1 -> Date(it1) }).toString()
            var date_back = sdf.format(date_epoch_back?.let { it1 -> Date(it1) }).toString()
            go_date_edit_text_add_destination.setText(date_departure)
            back_date_edit_text_add_destination.setText(date_back)
        }
    }

    private val locations = arrayOf(
        "Paris, France", "New York, USA", "Rome, Italy", "London, UK", "Tokyo, Japan", "Lisbon, Portugal", "Barcelona, Spain",
        "Honolulu, Hawaii", "Istanbul, Turkey", "Bangkok, Thailand", "Agra, India", "Cairo, Egypt", "Helsinki, Finland",
        "Ubud, Bali, Indonesia", "Berlin, Germany", "Shanghai, China", "Las Vegas, USA", "Jerusalem, Israel", "Venice, Italy",
        "Cape Town, South Africa", "Rio de Janeiro, Brazil", "Singapore, Singapore", "Toronto, Canada", "Seoul, South Korea",
        "Casablanca, Morocco", "Mosco, Russia", "Sydney, Australia", "Lima, Peru", "Beijing, China", "Buenos Aires, Argentina",
        "Edinburgh, Scotland", "Los Angeles, USA", "Copenhagen, Denmark", "Osaka, Japan", "Auckland, New Zealand", "Mumbai, India",
        "Dubai, United Arab Emirates", "Munich, Germany", "Hong Kong, China", "Qatar, State of Qatar", "Seattle, USA", "Oslo, Norway",
        "Bath, UK", "Athens, Greece", "Vienna, Austria", "Madrid, Spain", "Dublin, Republic of Ireland", "Florence, Italy",
        "Bruges, Belgium", "Krakow, Poland", "Amsterdam, Holland", "Stockholm, Sweden", "Ljubljana, Slovenia", "Hanoi, Vietnam",
        "Wellington, New Zealand", "Nice, France", "Vancouver, Canada", "Kiev, Ukraine", "Hallstatt, Austria", "Havana, Cuba",
        "Mexico City, Mexico", "Beirut, Lebanon", "Salzburg, Austria", "Sao Paolo, Brazil", "Prague, Czech Republic", "Kyoto, Japan",
        "Marrakech, Morocco", "St Petersburg, Russia", "San Francisco, USA", "Oxford, UK", "Melbourne, Australia", "Kingston, Jamaica",
        "Amman, Jordan", "Lagos, Nigeria", "New Orleans, USA", "San Jose, Costa Rica", "Tallinn, Estonia", "Suva Fiji", "Jarkarta, Indonesia",
        "New Delhi, India", "Adelaide, Australia", "Tripoli, Libya", "Male, Maldives", "Luxembourg, Luxembourg", "Kuala Lumpur, Malaysia",
        "Glasgow, Scotland", "Valletta, Malta", "Kathmandu, Nepal", "Brussels, Belgium", "Panama City, Panama", "Bucharest, Romania",
        "Cardiff, Wales", "Manila, Philippines", "Castries, Saint Lucia", "Apia, Samoa", "Bern, Switzerland", "Taipei, Taiwan",
        "Hobart, Australia", "Budapest, Hungary", "Reykjavik, Iceland"
    )

}