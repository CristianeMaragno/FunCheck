package com.cristianerm.funcheck

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*


/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var confirm_password = ""
    private var month_birth = ""
    private var day_birth = ""
    private var year_birth = ""
    private var gender = ""
    private lateinit var selectedRadioButton: RadioButton


    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ///Put elements on the dropdown menu of airlines
        val items = listOf("January", "February", "March", "April", "May","June","July","August","September","October","November","December")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_general, items)
        (month_text_input_sign_up.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        auth = FirebaseAuth.getInstance()

        view.sign_up_cadastrate_button.setOnClickListener({
            getSignUpInfo()
        })

        return view
    }

    private fun getSignUpInfo(){
        disableForm()

        email = email_edit_text_sign_up.text.toString()
        password = password_edit_text_sign_up.text.toString()
        confirm_password = comfirm_password_edit_text_sign_up.text.toString()
        month_birth = month_auto_complete_sign_up.text.toString()
        day_birth = day_edit_text_sign_up.text.toString()
        year_birth = year_edit_text_sign_up.text.toString()

        val selectedRadioButtonId: Int = radio_group_sign_in.checkedRadioButtonId
        if (selectedRadioButtonId != -1) {
            val gender_id = resources.getResourceEntryName(selectedRadioButtonId)
            if(gender_id == "radio_button_male_sign_in"){
                gender = "male"
            }else if(gender_id == "radio_button_female_sign_in"){
                gender = "female"
            }else if(gender_id == "radio_button_nonbinary_sign_in"){
                gender = "nonbinary"
            }else{
                gender = "none selected"
            }

        }


        val form_validation = "success"

        if(form_validation == "success"){
            createAccount(email, password)
        }
    }

    private fun createAccount(email: String, password: String){
        this.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                //Registration OK
                Log.v(TAG, "createUserWithEmail:success")
                val firebaseUser = this.auth.currentUser!!
                val uid = firebaseUser.uid

                enableForm()
                clearForm()

                writeUserOnDatabase(uid)
            } else {
                //Registration error
                Log.v(TAG, "createUserWithEmail:failure", task.exception)
                enableForm()
            }

        }
    }

    private fun writeUserOnDatabase(uid: String){
        try {
            myRef.child(uid).child("UserInfo").child("email").setValue(email)
            myRef.child(uid).child("UserInfo").child("password").setValue(password)
            myRef.child(uid).child("UserInfo").child("birth").setValue(month_birth + "/" + day_birth + "/" + year_birth)
            myRef.child(uid).child("UserInfo").child("gender").setValue(gender)
        }catch (e: Exception){
            Log.v(TAG, e.toString())
        }

        redirectsMainScreen()
    }

    private fun redirectsMainScreen(){
        val intent = Intent(activity, MainFunctionalitiesActivity::class.java)
        startActivity(intent)
    }


    private fun disableForm(){
        email_edit_text_sign_up.isEnabled = false
        password_edit_text_sign_up.isEnabled = false
        comfirm_password_edit_text_sign_up.isEnabled = false
        month_auto_complete_sign_up.isEnabled = false
        day_edit_text_sign_up.isEnabled = false
        year_edit_text_sign_up.isEnabled = false
        radio_group_sign_in.isEnabled = false
    }

    private fun enableForm(){
        email_edit_text_sign_up.isEnabled = true
        password_edit_text_sign_up.isEnabled = true
        comfirm_password_edit_text_sign_up.isEnabled = true
        month_auto_complete_sign_up.isEnabled = true
        day_edit_text_sign_up.isEnabled = true
        year_edit_text_sign_up.isEnabled = true
        radio_group_sign_in.isEnabled = true
    }

    private fun clearForm(){
        email_edit_text_sign_up.text?.clear()
        password_edit_text_sign_up.text?.clear()
        comfirm_password_edit_text_sign_up.text?.clear()
        month_auto_complete_sign_up.text?.clear()
        day_edit_text_sign_up.text?.clear()
        year_edit_text_sign_up.text?.clear()
        radio_group_sign_in.clearCheck()
    }
}
