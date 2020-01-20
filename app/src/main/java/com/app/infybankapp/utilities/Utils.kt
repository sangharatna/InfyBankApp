package com.app.infybankapp.utilities

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.app.infybankapp.application.AppClass

class Utils
{
    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Suppress("DEPRECATION")
    open fun showProgress(context: Context) {
        try {
            AppClass.progress = ProgressDialog(context)
            AppClass.progress.setMessage("Please Wait...")
            AppClass.progress.setCancelable(false)
            AppClass.progress.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}