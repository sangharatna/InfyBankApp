package com.app.infybankapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.app.infybankapp.model.UserData
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Register with us"

        signUp.setOnClickListener {

            // Log.e("Signup","Click")

            if (nameET.text.length < 5) {
                nameET.error = "Enter name"
                showError(nameET)
            } else {
                if (mobileET.text.isDigitsOnly() && mobileET.text.length == 10) {

                    if (!emailET.text.isEmpty()) {

                        if (isEmailValid(emailET.text.toString())) {

                            if (!passET.isDirty && !passET.text.isEmpty() && passET.text.length>7) {

                                if (addressET.text.length > 10) {
                                    AppClass.showProgress(this@RegisterActivity)
                                    registerUser()
                                } else {
                                    addressET.error = "Minimun 10 char required"
                                    showError(addressET)
                                }

                            } else {
                                passET.error = "Minimum 8 digit/char required"
                                showError(passET)
                            }

                        } else {
                            emailET.error = "Invalid email id"
                            showError(emailET)
                        }


                    } else {
                        emailET.error = "Enter Email"
                        showError(emailET)
                    }
                } else {
                    mobileET.error = "Invalid mobile number"
                    showError(mobileET)
                }
            }
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

    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun showError(editText: EditText) {
        editText.requestFocus()
        editText.isFocusableInTouchMode = true
    }


    fun registerUser() {

        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("userData")

        var id:String? = userRef.push().key;



        var userData = UserData(
            id,
            nameET.text.toString(),
            emailET.text.toString(),
            mobileET.text.toString(),
            passET.text.toString(),
            addressET.text.toString(),
            "NA",
            "NA",
            "NA",
            "NA"
        );

        userRef.child(id!!).setValue(userData)

        AppClass.dismissProgress()

        Toast.makeText(this@RegisterActivity,"Success",Toast.LENGTH_SHORT).show()

        AppClass.userObject = userData;

        startActivity(Intent(this@RegisterActivity,OptionActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

    }

}
