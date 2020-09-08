package com.cristianerm.bestflight

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_add_destinations.*
import kotlinx.android.synthetic.main.fragment_add_destinations.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.text.SimpleDateFormat
import java.util.*


class AddDestinationFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ///Put elements on the dropdown menu of airlines
        val items = listOf("All", "Gol", "Latam", "Azul")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_general, items)
        (airlines_text_input_add_destination.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_destinations, container, false)

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

        view.check_box_notifications_add_destination.setOnClickListener({
            if(check_box_notifications_add_destination.isChecked()){
                price_text_input_add_destination.visibility = View.VISIBLE
            }else{
                price_text_input_add_destination.visibility = View.GONE
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

}