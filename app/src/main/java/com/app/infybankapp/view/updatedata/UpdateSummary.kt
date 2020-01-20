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
import kotlinx.android.synthetic.main.activity_update_summary.*
import javax.inject.Inject

class UpdateSummary : AppCompatActivity() {


    lateinit var myComponent: FirebaseComponent
    @Inject
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_summary)

        //injecting firebase module
        myComponent = DaggerFirebaseComponent.builder().firebaseModule(FirebaseModule()).build() as DaggerFirebaseComponent
        myComponent.inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Update Summary"

        AppClass.myObjectSummaryTest = this

        summaryET.setText(AppClass.userObject.summary)

        cancel_update_btn.setOnClickListener {
            finish()
        }
        update_pi_btn.setOnClickListener {

            if(isValidate(summaryET.text.toString()))
            {
                val taskId = AppClass.userObject.id
                val taskRef = databaseReference.child(taskId!!)

                AppClass.userObject.summary = summaryET.text.toString()

                taskRef.setValue(AppClass.userObject)

                Toast.makeText(
                    this@UpdateSummary,
                    "Update Success",
                    Toast.LENGTH_SHORT
                ).show()

                AppClass.updateSavedList(
                    AppClass.userObject)

                finish()
            }
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
    fun isValidate(summary:String):Boolean
    {
        var booleanValue = false

        try{
        if(summary.isEmpty())
        {
            booleanValue =false
            summaryET.error = "Enter Summary"
            //showError(summaryET)

        }else if(summary.length<5) {
            booleanValue =false
            summaryET.error = "Mimimum 5 char required"
            //showError(summaryET)

        }else
            booleanValue =true
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return booleanValue
    }

    fun showError(editText: EditText)
    {
        editText.requestFocus()
        editText.isFocusableInTouchMode=true
    }
}
