package com.cristianerm.funcheck

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
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
            val email = "criss@gmail.com"
            val password = "123456"
            createAccount(email, password)
        })

        return view
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        /*auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireContext()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                }
            }*/
        // [END create_user_with_email]

        this.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                //Registration OK
                Log.v(TAG, "createUserWithEmail:success")
                val firebaseUser = this.auth.currentUser!!
            } else {
                //Registration error
                Log.v(TAG, "createUserWithEmail:failure", task.exception)
            }
        }
    }
}
