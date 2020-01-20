package com.app.infybankapp.registration.viewmodel

import android.app.Application
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.AndroidViewModel
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.login.view.LoginActivity
import com.app.infybankapp.model.UserData
import com.app.infybankapp.registration.view.RegisterActivity
import com.app.infybankapp.utilities.Utils
import com.google.firebase.database.DatabaseReference
import java.util.regex.Pattern


class RegistrationViewModel(application: Application) : AndroidViewModel(application){


    fun validateCredentials(nameET : EditText, mobileET: EditText,passET: EditText,addressET : EditText,emailET : EditText,  registerActivity: RegisterActivity ) : Boolean{
        if (nameET.text.length < 5) {
            nameET.error = "Enter name"
            showError(nameET)
        } else {
            if (mobileET.text.isDigitsOnly() && mobileET.text.length == 10) {

                if (!emailET.text.isEmpty()) {

                    if (isEmailValid(emailET.text.toString())) {

                        if (!passET.isDirty && !passET.text.isEmpty() && passET.text.length>7) {

                            if (addressET.text.length > 10) {
                                Utils().showProgress(registerActivity)
                                return true
                            } else {
                                addressET.error = "Minimun 10 char required"
                                showError(addressET)
                            }

                        } else {
                            passET.error = "Minimum 8 digit/char required"
                            showError(passET)
                        }

                    } else {
                        emailET.error = "Invalid email id"
                        showError(emailET)
                    }


                } else {
                    emailET.error = "Enter Email"
                    showError(emailET)
                }
            } else {
                mobileET.error = "Invalid mobile number"
                showError(mobileET)
            }
        }
        return false
    }

    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun showError(editText: EditText) {
        editText.requestFocus()
        editText.isFocusableInTouchMode = true
    }

    fun registerUser(databaseReference: DatabaseReference,nameET : EditText, mobileET: EditText,passET: EditText,addressET : EditText,emailET : EditText,  registerActivity: RegisterActivity ) {

        var id:String? = databaseReference.push().key

        var userData = UserData(
            id,
            nameET.text.toString(),
            emailET.text.toString(),
            mobileET.text.toString(),
            passET.text.toString(),
            addressET.text.toString(),
            "NA",
            "NA",
            "NA",
            "NA"
        )

        databaseReference.child(id!!).setValue(userData)

        AppClass.dismissProgress()

        Toast.makeText(registerActivity,"Success", Toast.LENGTH_SHORT).show()

        AppClass.userObject = userData

        registerActivity.startActivity(
            Intent(registerActivity,
                LoginActivity::class.java)
        )
        registerActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }


}