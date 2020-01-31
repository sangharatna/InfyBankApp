package com.app.infybankapp.viewprofile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.viewprofile.view.ViewProfile
import kotlinx.android.synthetic.main.activity_browse_profile.*
import org.json.JSONObject

class ViewProfileViewModel(application: Application) :AndroidViewModel(application){


    fun showProfile(viewProfile: ViewProfile)
    {
        viewProfile.name_tv.text = "Name : " + AppClass.selectedUserObject.name
        viewProfile.email_tv.text = "Email : " + AppClass.selectedUserObject.email
        viewProfile.mobbile_tv.text = "Mobile : " + AppClass.selectedUserObject.mobile
        viewProfile.address_tv.text = "Address : " + AppClass.selectedUserObject.address

        viewProfile.summery_tv.text = AppClass.selectedUserObject.summary

        try {

            if (!AppClass.selectedUserObject.education.equals("NA")) {

                var jsonObject: JSONObject = JSONObject(AppClass.selectedUserObject.education)

                var eduString: String = "Degree : " +
                        jsonObject.getString("degree") + "\nPercentage : " +
                        jsonObject.getString("percentage") + "%\nPassing Year : " +
                        jsonObject.getString("year")

                viewProfile.education_tv.text = eduString
            } else {
                viewProfile.education_tv.text = "NA"
            }

        } catch (e: Exception) {
            e.printStackTrace()
            viewProfile.education_tv.text = "NA"
        }


        try {

            if (!AppClass.selectedUserObject.experience.equals("NA")) {
                var jsonObject: JSONObject = JSONObject(AppClass.selectedUserObject.experience)

                var eduString: String = "" +
                        jsonObject.getString("design") + "\n" +
                        jsonObject.getString("company") + "\n" +
                        jsonObject.getString("start") + " - " + jsonObject.getString("end")

                viewProfile.prof_tv.text = eduString
            } else {
                viewProfile.prof_tv.text = "NA"
            }

        } catch (e: Exception) {
            e.printStackTrace()
            viewProfile.prof_tv.text = "NA"
        }
    }


}