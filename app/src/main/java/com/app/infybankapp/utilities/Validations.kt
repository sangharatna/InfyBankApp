package com.app.infybankapp.utilities

import android.widget.EditText
import java.util.regex.Pattern

class Validations {

    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isValidPassword(pass:String):Boolean
    {
        return pass.length >= 8
    }

    fun showError(editText: EditText, msg:String)
    {
        editText.error = msg
        editText.requestFocus()
        editText.isFocusableInTouchMode = true
    }

}