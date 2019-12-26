package com.app.infybankapp.updatedata

import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.infybankapp.AppClass
import com.app.infybankapp.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_edu.*
import org.json.JSONObject

class UpdateEduAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_edu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Update Education Info"


        cancel_update_btn.setOnClickListener {
            finish()
        }
        update_pi_btn.setOnClickListener {

            if(isValidate()) {
                var jsonObject: JSONObject = JSONObject()
                jsonObject.put("degree",nameET.text.toString())
                jsonObject.put("percentage",percentET.text.toString())
                jsonObject.put("year",yearET.text.toString())

                val taskId = AppClass.userObject.id;
                val database = FirebaseDatabase.getInstance()
                val userRef = database.getReference("userData")
                val taskRef = userRef.child(taskId!!)

                AppClass.userObject.education = jsonObject.toString()

                taskRef.setValue(AppClass.userObject)

                Toast.makeText(
                    this@UpdateEduAct,
                    "Update Success",
                    Toast.LENGTH_SHORT
                ).show()

                AppClass.updateSavedList(AppClass.userObject)

                finish()
            }
        }

        try {
            var jsonObject:JSONObject = JSONObject(AppClass.userObject.education)
            nameET.setText(jsonObject.getString("degree"))
            percentET.setText(jsonObject.getString("percentage"))
            yearET.setText(jsonObject.getString("year"))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun isValidate():Boolean
    {

        if(nameET.text.isEmpty())
        {
            nameET.error = "Enter Degree"
            showError(nameET)
            return false
        }else if(percentET.text.isEmpty())
        {
            percentET.error = "Enter Percentage"
            showError(percentET)
            return false
        }else if(yearET.text.isEmpty() || yearET.text.length!=4 )
        {
            yearET.error = "Enter Passing Year"
            showError(yearET)
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
