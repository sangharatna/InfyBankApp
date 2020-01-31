package com.app.infybankapp.updatedata

import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.R
import com.app.infybankapp.di.DaggerFirebaseComponent
import com.app.infybankapp.di.FirebaseComponent
import com.app.infybankapp.di.FirebaseModule
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_update_pi.*
import kotlinx.android.synthetic.main.activity_update_pi.cancel_update_btn
import kotlinx.android.synthetic.main.activity_update_pi.update_pi_btn
import javax.inject.Inject

class UpdatePIAct : AppCompatActivity() {

    lateinit var myComponent: FirebaseComponent
    @Inject
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pi)

        //injecting firebase module
        myComponent = DaggerFirebaseComponent.builder().firebaseModule(FirebaseModule()).build() as DaggerFirebaseComponent
        myComponent.inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Update Personal Info"

        AppClass.myObjectPITest = this

        cancel_update_btn.setOnClickListener {
            finish()
        }
        update_pi_btn.setOnClickListener {


            try {
                if(isValidate(nameET.text.toString(), mobileET.text.toString(), addressET.text.toString()))
                {

                    val taskId = AppClass.userObject.id
                    val taskRef = databaseReference.child(taskId!!)

                    AppClass.userObject.let{ it ->
                        it.address = addressET.text.toString()
                        it.name = nameET.text.toString()
                        it.mobile = mobileET.text.toString()
                    }

                    taskRef.setValue(AppClass.userObject)

                    Toast.makeText(
                        this@UpdatePIAct,
                        "Update Success",
                        Toast.LENGTH_SHORT
                    ).show()

                    AppClass.updateSavedList(
                        AppClass.userObject)

                    finish()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        addressET.text = (AppClass.userObject.address).toEditable()
        nameET.text = (AppClass.userObject.name).toEditable()
        mobileET.text = (AppClass.userObject.mobile).toEditable()


    }

    fun isValidate(name:String,mobilenumber:String,address:String): Boolean {

        var booleanValue = false

        try {
            if(name.isEmpty())
            {
                booleanValue =false
                nameET.error = "Enter Name"
                //showError(nameET)
            }else if(mobilenumber.isEmpty())
            {
                booleanValue =false
                mobileET.error = "Enter Mobile Number"
                //showError(percentET)
            }else if(address.isEmpty() )
            {
                booleanValue =false
                addressET.error = "Enter Address"
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
    //function for show validation error message
    fun showError(editText: EditText)
    {
        editText.requestFocus()
        editText.isFocusableInTouchMode=true
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}
