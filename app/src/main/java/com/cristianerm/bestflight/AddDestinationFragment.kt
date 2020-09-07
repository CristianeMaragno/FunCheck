package com.cristianerm.bestflight

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_add_destinations.*
import kotlinx.android.synthetic.main.fragment_add_destinations.view.*
import java.util.*


class AddDestinationFragment: Fragment() {
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
            Toast.makeText(context, "Date String = ${picker.headerText}:: Date epoch value = ${it}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openDatePickerTwoDate(){
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        val picker = builder.build()
        picker.show(childFragmentManager, picker.toString())

        picker.addOnPositiveButtonClickListener {
            Toast.makeText(context, "Date String = ${picker.headerText}::  Date epoch values::${it.first}:: to :: ${it.second}", Toast.LENGTH_SHORT).show()
        }
    }
}