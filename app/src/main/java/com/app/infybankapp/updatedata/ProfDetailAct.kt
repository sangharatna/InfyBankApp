package com.app.infybankapp.updatedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.app.infybankapp.AppClass
import com.app.infybankapp.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_prof_detail.*

import org.json.JSONObject

class ProfDetailAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prof_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Update Professional Info"


        cancel_update_btn.setOnClickListener {
            finish()
        }
        update_pi_btn.setOnClickListener {

            if(isValidate())
            {

                var jsonObject: JSONObject = JSONObject()
                jsonObject.put("design",designationET.text.toString())
                jsonObject.put("company",comapnyET.text.toString())
                jsonObject.put("start",start_yr_et.text.toString())
                jsonObject.put("end",end_yr_et.text.toString())


                val taskId = AppClass.userObject.id;
                val database = FirebaseDatabase.getInstance()
                val userRef = database.getReference("userData")
                val taskRef = userRef.child(taskId!!)

                AppClass.userObject.experiance = jsonObject.toString()

                taskRef.setValue(AppClass.userObject)

                Toast.makeText(
                    this@ProfDetailAct,
                    "Update Success",
                    Toast.LENGTH_SHORT
                ).show()

                AppClass.updateSavedList(AppClass.userObject)

                finish()
            }
        }

        try {
            var jsonObject:JSONObject = JSONObject(AppClass.userObject.experiance)
            designationET.setText(jsonObject.getString("design"))
            comapnyET.setText(jsonObject.getString("company"))
            start_yr_et.setText(jsonObject.getString("start"))
            end_yr_et.setText(jsonObject.getString("end"))

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun isValidate():Boolean
    {
        if(designationET.text.isEmpty())
        {
            designationET.error = "Enter Designation"
            showError(designationET)
            return false
        }
        else if(comapnyET.text.isEmpty())
        {
            comapnyET.error = "Enter Company Name"
            showError(comapnyET)
            return false
        }
        else if(start_yr_et.text.isEmpty() || start_yr_et.text.length!=4 )
        {
            start_yr_et.error = "Enter Valid Start Year"
            showError(start_yr_et)
            return false
        }
        else if(end_yr_et.text.isEmpty() || end_yr_et.text.length!=4 )
        {
            end_yr_et.error = "Enter Valid End Year"
            showError(end_yr_et)
            return false
        }
        else
            return true
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

    fun showError(editText: EditText)
    {
        editText.requestFocus()
        editText.isFocusableInTouchMode=true
    }
}
