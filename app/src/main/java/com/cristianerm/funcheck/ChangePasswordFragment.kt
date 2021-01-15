package com.cristianerm.funcheck

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_change_password.view.*

class ChangePasswordFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var currentPassword = ""
    private var newPassword = ""
    private var ConfirmNewPassword = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_change_password, container, false)

        auth = FirebaseAuth.getInstance()

        view.change_password_button.setOnClickListener({
            getNewPasswordInfo()
        })

        return view
    }

    private fun getNewPasswordInfo(){
        currentPassword = current_password_edit_text_change_password.text.toString()
        newPassword = new_password_edit_text_change_password.text.toString()
        ConfirmNewPassword = comfirm_new_password_edit_text_change_password.text.toString()

        Log.v(ContentValues.TAG, currentPassword)
        Log.v(ContentValues.TAG, newPassword)
        Log.v(ContentValues.TAG, ConfirmNewPassword)

        verifyNewPassword()
    }

    private fun verifyNewPassword(){

        if (newPassword.equals(ConfirmNewPassword)){
            Log.v(ContentValues.TAG, "New pass is equal to confirm new pass")
            if (currentPassword == "123456"){
                changeUserPassword(newPassword)
            }
        }else{
            Log.v(ContentValues.TAG, "New pass is NOT equal to confirm new pass")
        }
    }

    private fun changeUserPassword(newPassword: String){

        val firebaseUser = this.auth.currentUser!!

        Log.v(ContentValues.TAG, "CHANGE password")

        //firebaseUser.updatePassword(newPassword)
    }

}