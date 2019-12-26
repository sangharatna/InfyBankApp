package com.app.infybankapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.app.infybankapp.updatedata.ProfDetailAct
import com.app.infybankapp.updatedata.UpdateEduAct
import com.app.infybankapp.updatedata.UpdatePIAct
import com.app.infybankapp.updatedata.UpdateSummary
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONObject


class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Your Profile"

        personal_edit_img.setOnClickListener {  val taskId = AppClass.userObject.id;
            startActivity(Intent(this@DashboardActivity,UpdatePIAct::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        summery_edit_img.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,UpdateSummary::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        education_edit_img.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,UpdateEduAct::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
        prof_edit_img.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,ProfDetailAct::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

    }

    override fun onResume() {
        super.onResume()

        name_tv.text = "Name : "+AppClass.userObject.name
        email_tv.text = "Email : "+AppClass.userObject.email
        mobbile_tv.text = "Mobile : "+AppClass.userObject.mobile
        address_tv.text = "Address : "+AppClass.userObject.address

        summery_tv.text = AppClass.userObject.summary

        try {

            if(!AppClass.userObject.education.equals("NA")) {

                var jsonObject: JSONObject = JSONObject(AppClass.userObject.education)

                var eduString: String = "Degree : " +
                        jsonObject.getString("degree") + "\nPercentage : " +
                        jsonObject.getString("percentage") + "%\nPassing Year : " +
                        jsonObject.getString("year")

                education_tv.setText(eduString)
            }
            else
            {
                education_tv.setText("NA")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            education_tv.setText("NA")
        }


        try {

            if(!AppClass.userObject.experiance.equals("NA")) {

                var jsonObject: JSONObject = JSONObject(AppClass.userObject.experiance)

                var eduString: String = "" +
                        jsonObject.getString("design") + "\n" +
                        jsonObject.getString("company") + "\n" +
                        jsonObject.getString("start") + " - " + jsonObject.getString("end")

                prof_tv.setText(eduString)
            }
            else
            {
                prof_tv.setText("NA")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            prof_tv.setText("NA")
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home)
        {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

}
