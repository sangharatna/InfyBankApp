package com.app.infybankapp.updatedata

import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.infybankapp.R
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.di.DaggerFirebaseComponent
import com.app.infybankapp.di.FirebaseComponent
import com.app.infybankapp.di.FirebaseModule
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_update_edu.*
import kotlinx.android.synthetic.main.activity_update_edu.cancel_update_btn
import kotlinx.android.synthetic.main.activity_update_edu.update_pi_btn
import org.json.JSONObject
import javax.inject.Inject

class UpdateEduAct : AppCompatActivity() {

    lateinit var myComponent: FirebaseComponent
    @Inject
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_edu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Update Education Info"


        //injecting firebase module
        myComponent = DaggerFirebaseComponent.builder().firebaseModule(FirebaseModule()).build() as DaggerFirebaseComponent
        myComponent.inject(this)

        AppClass.myObjectUnderTest = this

        cancel_update_btn.setOnClickListener {
            finish()
        }
        update_pi_btn.setOnClickListener {

            if(isValidate(nameET.text.toString(), percentET.text.toString(), yearET.text.toString())) {

                var jsonObject: JSONObject = JSONObject()

                jsonObject.let{ it ->
                    it.put("degree",nameET.text.toString())
                    it.put("percentage",percentET.text.toString())
                    it.put("year",yearET.text.toString())
                }

                val taskId = AppClass.userObject.id
                val taskRef = databaseReference.child(taskId!!)

                AppClass.userObject.education = jsonObject.toString()

                taskRef.setValue(AppClass.userObject)

                Toast.makeText(
                    this@UpdateEduAct,
                    "Update Success",
                    Toast.LENGTH_SHORT
                ).show()

                AppClass.updateSavedList(
                    AppClass.userObject)

                finish()
            }
        }

        try {
            var jsonObject:JSONObject = JSONObject(AppClass.userObject.education)
            nameET.text = (jsonObject.getString("degree")).toEditable()
            percentET.text = (jsonObject.getString("percentage")).toEditable()
            yearET.text = (jsonObject.getString("year")).toEditable()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun isValidate(name:String,percent:String,year:String):Boolean
    {

        var booleanValue = false

        try {
            if(name.isEmpty())
            {
                booleanValue =false
                nameET.error = "Enter Degree"
                //showError(nameET)
            }else if(percent.isEmpty())
            {
                booleanValue =false
                percentET.error = "Enter Percentage"
                //showError(percentET)
            }else if(year.isEmpty() || year.length!=4 )
            {
                booleanValue =false
                yearET.error = "Enter Passing Year"
                //showError(yearET)
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
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}
