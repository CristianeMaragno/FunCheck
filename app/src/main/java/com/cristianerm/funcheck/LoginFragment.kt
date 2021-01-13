package com.cristianerm.funcheck

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment: Fragment() {

    private lateinit var auth: FirebaseAuth

    private var email = ""
    private var password = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        auth = FirebaseAuth.getInstance()

        view.log_in_button.setOnClickListener({
            getLogInInfo()
        })

        view.sign_up_button.setOnClickListener({
            // Navigate to the next Fragment.
            val intent = Intent(activity, SignUpActivity::class.java)
            startActivity(intent)
        })

        return view
    }

    private fun getLogInInfo(){
        email = email_edit_text_log_in.text.toString()
        password = password_edit_text_log_in.text.toString()

        logIn(email, password)
    }

    private fun logIn(email: String, password: String){

        this.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    redirectsMainScreen()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
        }

    }

    private fun redirectsMainScreen(){
        val intent = Intent(activity, MainFunctionalitiesActivity::class.java)
        startActivity(intent)
    }
}