package com.app.infybankapp.option.viewmodel

import android.app.Activity
import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import com.app.infybankapp.option.view.OptionActivity
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.browseprofile.view.BrowseProfiles
import com.app.infybankapp.dashboard.view.DashboardActivity

class OptionViewModel(application:Application): AndroidViewModel(application) {

    fun showProfile(optionActivity: OptionActivity)
    {
        optionActivity.startActivity(Intent(optionActivity, DashboardActivity::class.java))
        optionActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
    fun browseProfile(optionActivity: OptionActivity)
    {
        optionActivity.startActivity(Intent(optionActivity, BrowseProfiles::class.java))
        optionActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
    fun showDialog(optionActivity: OptionActivity)
    {
        lateinit var alertDialog: AlertDialog
        var alert: AlertDialog.Builder = AlertDialog.Builder(optionActivity,android.R.style.ThemeOverlay_Material_Dialog_Alert)

        alert.setTitle("Warning")
        alert.setMessage("Do you want to logout?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->

            try {
                AppClass.userDataList.clear()
                var con = optionActivity as Activity
                con.finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->  alertDialog.dismiss()})
        alertDialog = alert.create()
        alertDialog.show()
    }
}