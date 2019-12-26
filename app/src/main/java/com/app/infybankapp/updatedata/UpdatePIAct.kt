package com.app.infybankapp.updatedata

import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.infybankapp.AppClass
import com.app.infybankapp.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_pi.*

class UpdatePIAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pi)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Update Personal Info"


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

                AppClass.userObject.address = addressET.text.toString();
                AppClass.userObject.name = nameET.text.toString();
                AppClass.userObject.mobile = mobileET.text.toString();

                taskRef.setValue(AppClass.userObject)

                Toast.makeText(
                    this@UpdatePIAct,
                    "Update Success",
                    Toast.LENGTH_SHORT
                ).show()

                AppClass.updateSavedList(AppClass.userObject)

                finish()
            }
        }

        addressET.setText(AppClass.userObject.address)
        nameET.setText(AppClass.userObject.name)
        mobileET.setText(AppClass.userObject.mobile)


    }

    fun isValidate(): Boolean {


        if (validName() && validMobileNumber() && validAddress()) {

            return true
        }
        return false
    }
    //function for validate mobile number
    fun validMobileNumber():Boolean
    {
        if(mobileET.text.isEmpty() || mobileET.text.length != 10)
        {
            mobileET.error="Mobile number should be valid"
            showError(mobileET)
            return false
        }
        else
            return true
    }
    //function for validate name
    fun validName():Boolean
    {
        if(nameET.text.isEmpty() && nameET.text.length>5)
        {
            nameET.error = "Name field is Mandatory"
            showError(nameET)
            return false
        }
        return true
    }
    //function for address name
    fun validAddress():Boolean
    {
        if (addressET.text.isEmpty()) {
            addressET.error = "Address field is Mandatory"
            showError(addressET)
            return false
        }
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
    //function for show validation error message
    fun showError(editText: EditText)
    {
        editText.requestFocus()
        editText.isFocusableInTouchMode=true
    }
}
