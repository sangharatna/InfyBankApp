package com.app.infybankapp.dashboard.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.app.infybankapp.dashboard.view.DashboardActivity
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.updatedata.ProfDetailAct
import com.app.infybankapp.updatedata.UpdateEduAct
import com.app.infybankapp.updatedata.UpdatePIAct
import com.app.infybankapp.updatedata.UpdateSummary
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONObject

class DashboardViewModel(application: Application):AndroidViewModel(application) {

    //1.function for edit personal information
    fun editPersonalInformation(dashboardActivity: DashboardActivity)
    {
        dashboardActivity.startActivity(Intent(dashboardActivity, UpdatePIAct::class.java))
        dashboardActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
    //2.function for edit summary information
    fun editSummaryInformation(dashboardActivity: DashboardActivity)
    {
        dashboardActivity.startActivity(Intent(dashboardActivity, UpdateSummary::class.java))
        dashboardActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
    //3.function for edit educational information
    fun editEducationalInformation(dashboardActivity: DashboardActivity)
    {
        dashboardActivity.startActivity(Intent(dashboardActivity, UpdateEduAct::class.java))
        dashboardActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    //4.function for edit professional information
    fun editProfessionalInformation(dashboardActivity: DashboardActivity)
    {
        dashboardActivity.startActivity(Intent(dashboardActivity, ProfDetailAct::class.java))
        dashboardActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    //5.function for showResume()
    fun showDefaultProfile(dashboardActivity: DashboardActivity)
    {
        dashboardActivity.name_tv.text = "Name : "+ AppClass.userObject.name
        dashboardActivity.email_tv.text = "Email : "+ AppClass.userObject.email
        dashboardActivity.mobbile_tv.text = "Mobile : "+ AppClass.userObject.mobile
        dashboardActivity.address_tv.text = "Address : "+ AppClass.userObject.address

        dashboardActivity.summery_tv.text = AppClass.userObject.summary

        try {

            if(!AppClass.userObject.education.equals("NA")) {

                var jsonObject: JSONObject = JSONObject(AppClass.userObject.education)

                var eduString: String = "Degree : " +
                        jsonObject.getString("degree") + "\nPercentage : " +
                        jsonObject.getString("percentage") + "%\nPassing Year : " +
                        jsonObject.getString("year")

                dashboardActivity.education_tv.text = eduString
            }
            else
            {
                dashboardActivity.education_tv.text = "NA"
            }

        } catch (e: Exception) {
            e.printStackTrace()
            dashboardActivity.education_tv.text = "NA"
        }


        try {

            if(!AppClass.userObject.experience.equals("NA")) {

                var jsonObject: JSONObject = JSONObject(AppClass.userObject.experience)

                var eduString: String = "" +
                        jsonObject.getString("design") + "\n" +
                        jsonObject.getString("company") + "\n" +
                        jsonObject.getString("start") + " - " + jsonObject.getString("end")

                dashboardActivity.prof_tv.text = eduString
            }
            else
            {
                dashboardActivity.prof_tv.text = "NA"
            }

        } catch (e: Exception) {
            e.printStackTrace()
            dashboardActivity.prof_tv.text = "NA"
        }
    }




}