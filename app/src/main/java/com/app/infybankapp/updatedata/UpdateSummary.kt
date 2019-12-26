package com.app.infybankapp.updatedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.app.infybankapp.AppClass
import com.app.infybankapp.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_summary.*

class UpdateSummary : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_summary)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Update Summary"

        summaryET.setText(AppClass.userObject.summary)

        cancel_update_btn.setOnClickListener {
            finish()
        }
        update_pi_btn.setOnClickListener {

            if(isValidate())
            {
                val taskId = AppClass.userObject.id;
                val database = FirebaseDatabase.getInstance()
                val userRef = database.getReference("userData")
                val taskRef = userRef.child(taskId!!)

                AppClass.userObject.summary = summaryET.text.toString()

                taskRef.setValue(AppClass.userObject)

                Toast.makeText(
                    this@UpdateSummary,
                    "Update Success",
                    Toast.LENGTH_SHORT
                ).show()

                AppClass.updateSavedList(AppClass.userObject)

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
    fun isValidate():Boolean
    {
        if(summaryET.text.isEmpty())
        {
            summaryET.error = "Enter Summary"
            showError(summaryET)
            return false
        }else if(summaryET.text.length<5) {
            summaryET.error = "Mimimum 5 char required"
            showError(summaryET)
            return false
        }else
        {
            return true
        }
    }

    fun showError(editText: EditText)
    {
        editText.requestFocus()
        editText.isFocusableInTouchMode=true
    }
}
