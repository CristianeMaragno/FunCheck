package com.cristianerm.funcheck

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_change_password.view.*


class ChangePasswordFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var firebaseUser: FirebaseUser

    private var currentPassword = ""
    private var newPassword = ""
    private var ConfirmNewPassword = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_change_password, container, false)

        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseUser = this.auth.currentUser!!

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
            verifyCurrentPassword()
        }else{
            Log.v(ContentValues.TAG, "New pass is NOT equal to confirm new pass")
        }
    }

    private fun verifyCurrentPassword(){
        val uid = firebaseUser.uid
        myRef = firebaseDatabase.getReference().child(uid).child("UserInfo")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(UserInformation::class.java)
                var trueCurrentPassword = user!!.password

                if(trueCurrentPassword == currentPassword){
                    changeUserPassword(newPassword)
                }else{

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        myRef.addValueEventListener(postListener)
    }

    private fun changeUserPassword(newPassword: String){
        Log.v(ContentValues.TAG, "CHANGE password")

        firebaseUser.updatePassword(newPassword)
    }

}
