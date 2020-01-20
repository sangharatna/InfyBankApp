package com.app.infybankapp.login.viewmodel

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.infybankapp.login.repository.LoginRepository
import com.app.infybankapp.login.view.LoginActivity
import com.app.infybankapp.registration.view.RegisterActivity


class LoginViewModel: ViewModel() {

    private  var loginRepository: LoginRepository = LoginRepository()

    fun validateCredentials(
        email: String,
        passWord: String
    ): Boolean {
        return loginRepository.validateCredentials(email, passWord)
    }

    fun getUserData(email: String,
                    passWord: String,
                    passET: EditText,
                    loginEmailET: EditText):LiveData<com.app.infybankapp.model.UserData>{
        return loginRepository.getUserData(email, passWord,passET,loginEmailET)
    }

    fun openRegisterScreen(loginActivity: LoginActivity){

        loginActivity.startActivity(
            Intent(
                loginActivity,
                RegisterActivity::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    fun showDialogOnBackPress(loginActivity: LoginActivity) {
        lateinit var alertDialog: AlertDialog
        var alert: AlertDialog.Builder = AlertDialog.Builder(
            loginActivity,
            android.R.style.ThemeOverlay_Material_Dialog_Alert
        )

        alert.setTitle("Warning")
        alert.setMessage("Do you really want to exit from app?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            loginActivity.finish()
        })
        alert.setNegativeButton(
            "No",
            DialogInterface.OnClickListener { dialog, which -> alertDialog.dismiss() })

        alertDialog = alert.create()
        alertDialog.show()

    }

}