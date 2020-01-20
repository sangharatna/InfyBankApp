package com.app.infybankapp.view.updatedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.R
import com.app.infybankapp.di.DaggerFirebaseComponent
import com.app.infybankapp.di.FirebaseComponent
import com.app.infybankapp.di.FirebaseModule
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_prof_detail.*

import org.json.JSONObject
import javax.inject.Inject

class ProfDetailAct : AppCompatActivity() {

    lateinit var myComponent: FirebaseComponent
    @Inject
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prof_detail)

        //injecting firebase module
        myComponent = DaggerFirebaseComponent.builder().firebaseModule(FirebaseModule()).build() as DaggerFirebaseComponent
        myComponent.inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Update Professional Info"

        AppClass.myObjectProfileTest = this

        cancel_update_btn.setOnClickListener {
            finish()
        }
        update_pi_btn.setOnClickListener {

            if(isValidate(designationET.text.toString(), comapnyET.text.toString(), start_yr_et.text.toString(), end_yr_et.text.toString()))
            {

                var jsonObject: JSONObject = JSONObject()
                jsonObject.put("design",designationET.text.toString())
                jsonObject.put("company",comapnyET.text.toString())
                jsonObject.put("start",start_yr_et.text.toString())
                jsonObject.put("end",end_yr_et.text.toString())


                val taskId = AppClass.userObject.id
                val taskRef = databaseReference.child(taskId!!)

                AppClass.userObject.experience = jsonObject.toString()

                taskRef.setValue(AppClass.userObject)

                Toast.makeText(
                    this@ProfDetailAct,
                    "Update Success",
                    Toast.LENGTH_SHORT
                ).show()

                AppClass.updateSavedList(
                    AppClass.userObject)

                finish()
            }
        }

        try {
            var jsonObject:JSONObject = JSONObject(AppClass.userObject.experience)
            designationET.setText(jsonObject.getString("design"))
            comapnyET.setText(jsonObject.getString("company"))
            start_yr_et.setText(jsonObject.getString("start"))
            end_yr_et.setText(jsonObject.getString("end"))

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun isValidate(designation:String,comapny:String,start_yr:String,end_yr:String):Boolean
    {
        var booleanValue = false

        try {
            if(designation.isEmpty())
            {
                booleanValue =false
                designationET.error = "Enter Designation"

            }else if(comapny.isEmpty())
            {
                booleanValue =false
                comapnyET.error = "Enter Company Name"

            }else if(start_yr.isEmpty() )
            {
                booleanValue =false
                start_yr_et.error = "Enter Start Date"

            }
            else if(end_yr.isEmpty() )
            {
                booleanValue =false
                end_yr_et.error = "Enter End Date"

            }
            else
                booleanValue =true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return booleanValue
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
