package com.app.infybankapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_browse_profile.*
import kotlinx.android.synthetic.main.activity_browse_profile.address_tv
import kotlinx.android.synthetic.main.activity_browse_profile.education_tv
import kotlinx.android.synthetic.main.activity_browse_profile.email_tv
import kotlinx.android.synthetic.main.activity_browse_profile.mobbile_tv
import kotlinx.android.synthetic.main.activity_browse_profile.name_tv
import kotlinx.android.synthetic.main.activity_browse_profile.prof_tv
import kotlinx.android.synthetic.main.activity_browse_profile.summery_tv
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONObject

class ViewProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"

    }

    override fun onResume() {
        super.onResume()

        name_tv.text = "Name : " + AppClass.selectedUserObject.name
        email_tv.text = "Email : " + AppClass.selectedUserObject.email
        mobbile_tv.text = "Mobile : " + AppClass.selectedUserObject.mobile
        address_tv.text = "Address : " + AppClass.selectedUserObject.address

        summery_tv.text = AppClass.selectedUserObject.summary

        try {

            if (!AppClass.selectedUserObject.education.equals("NA")) {

                var jsonObject: JSONObject = JSONObject(AppClass.selectedUserObject.education)

                var eduString: String = "Degree : " +
                        jsonObject.getString("degree") + "\nPercentage : " +
                        jsonObject.getString("percentage") + "%\nPassing Year : " +
                        jsonObject.getString("year")

                education_tv.setText(eduString)
            } else {
                education_tv.setText("NA")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            education_tv.setText("NA")
        }


        try {

            if (!AppClass.selectedUserObject.experiance.equals("NA")) {
                var jsonObject: JSONObject = JSONObject(AppClass.selectedUserObject.experiance)

                var eduString: String = "" +
                        jsonObject.getString("design") + "\n" +
                        jsonObject.getString("company") + "\n" +
                        jsonObject.getString("start") + " - " + jsonObject.getString("end")

                prof_tv.setText(eduString)
            } else {
                prof_tv.setText("NA")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            prof_tv.setText("NA")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
