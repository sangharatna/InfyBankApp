package com.app.infybankapp.login.repository

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.model.FirebaseModel
import com.app.infybankapp.model.UserData
import com.app.infybankapp.utilities.Validations


class LoginRepository() {

    var applicationContext: Context
    lateinit var data: MutableLiveData<UserData>

    init {
        this.applicationContext = AppClass.appContext
    }

    fun validateCredentials(
        emailID: String,
        password: String
    ): Boolean {
        if (Validations().isEmailValid(emailID)) {
            if (!Validations().isValidPassword(password)) {

                Handler(Looper.getMainLooper()).post(Runnable {
                    // code goes here
                    Toast.makeText(applicationContext,"Wrong Password",Toast.LENGTH_SHORT).show()
                })

                return false
            } else {
                return true
            }
        } else {
            Handler(Looper.getMainLooper()).post(Runnable {
                // code goes here
                Toast.makeText(applicationContext,"Wrong Email",Toast.LENGTH_SHORT).show()
            })

            return false
        }

        return false
    }

    fun getUserData(
        emailID: String,
        password: String,
        passET: EditText,
        loginEmailET: EditText
    ): MutableLiveData<UserData> {
        var userdata = MutableLiveData<UserData>()
        userdata = (FirebaseModel(applicationContext.applicationContext).checkUser(
            loginEmailET.text.toString(),
            passET.text.toString()
        ))

        return userdata
    }
}