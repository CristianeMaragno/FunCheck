package com.cristianerm.bestflight

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.login_fragment.view.*

class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        // Set an error if the password is less than 8 characters.
        view.log_in_button.setOnClickListener({
            if (!isPasswordValid(view.password_edit_text.text!!)) {
                view.password_text_input.error = "Password must contain at least 8 characters."
            } else {
                // Clear the error.
                view.password_text_input.error = null
                // Navigate to the next Fragment.
                (activity as NavigationHost).navigateTo(MonitoredDestinationsFragment(), false)
            }
        })

        // Clear the error once more than 8 characters are typed.
        view.password_edit_text.setOnKeyListener({ _, _, _ ->
            if (isPasswordValid(view.password_edit_text.text!!)) {
                // Clear the error.
                view.password_text_input.error = null
            }
            false
        })

        view.sign_up_button.setOnClickListener({
            // Navigate to the next Fragment.
            (activity as NavigationHost).navigateTo(SignUpFragment(), false)
        })

        return view
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}