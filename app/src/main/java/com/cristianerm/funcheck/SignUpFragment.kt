package com.cristianerm.funcheck

import android.content.ContentValues.TAG
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
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*


/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

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
        val email = email_edit_text_sign_up.text.toString()
        val password = password_edit_text_sign_up.text.toString()
        val confirm_password = comfirm_password_edit_text_sign_up.text.toString()
        val month_birth = month_auto_complete_sign_up.text.toString()
        val day_birth = day_edit_text_sign_up.text.toString()
        val year_birth = year_edit_text_sign_up.text.toString()

        Log.v(TAG, email + " " + password + " " + confirm_password + " " + month_birth + " " + day_birth + " " + year_birth + " ")

        //createAccount(email, password)
    }

    private fun createAccount(email: String, password: String) {
        this.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                //Registration OK
                Log.v(TAG, "createUserWithEmail:success")
                val firebaseUser = this.auth.currentUser!!
                redirectsMainScreen()
            } else {
                //Registration error
                Log.v(TAG, "createUserWithEmail:failure", task.exception)
            }
        }
    }

    private fun redirectsMainScreen(){

    }
}
