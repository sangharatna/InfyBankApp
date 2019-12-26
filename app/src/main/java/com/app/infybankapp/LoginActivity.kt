package com.app.infybankapp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.infybankapp.model.UserData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.login_activity.*
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {


    var flag:Int = 0

    lateinit var userChangeListener: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        signin.setOnClickListener {

            hideSoftKeyBoard(this,it)

            if(!loginEmailET.text.isEmpty()){
                if(isEmailValid(loginEmailET.text.toString())){
                    if(passET.text.length>7){

                        AppClass.showProgress(this@LoginActivity)

                        val database = FirebaseDatabase.getInstance()
                        val userRef = database.getReference("userData")

                        //val userChangeListener: ValueEventListener
                        userChangeListener    = object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {

                                AppClass.userDataList.clear()

                                var iterable: MutableIterable<DataSnapshot> = dataSnapshot.children

                                for(child:DataSnapshot in iterable)
                                {
                                    AppClass.userDataList.add(child.getValue(UserData::class.java))

                                    var test: UserData? = child.getValue(
                                        UserData::class.java)

                                    if(test!!.email.equals(loginEmailET.text.toString()))
                                    {
                                        flag = 1
                                        AppClass.userObject = test;
                                        //break;
                                    }
                                }


                                if(flag == 1) {

                                    AppClass.dismissProgress()

                                    if (AppClass.userObject.password.equals(passET.text.toString())) {



                                        Toast.makeText(
                                            this@LoginActivity,
                                            "Login Success",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        flag = 0;

                                        userRef.removeEventListener(userChangeListener)

                                        loginEmailET.setText("")
                                        passET.setText("")

                                        startActivity(Intent(this@LoginActivity,OptionActivity::class.java))
                                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

                                    } else {
                                        Toast.makeText(
                                            this@LoginActivity,
                                            "Invalid Password",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                                else {

                                    AppClass.dismissProgress()

                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Email is not registred",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                            override fun onCancelled(databaseError: DatabaseError) {

                            }
                        }

                        userRef.addValueEventListener(userChangeListener)

                    }else{
                        passET.error="Password is incorrect"
                    }

                }
                else{
                    loginEmailET.error="Enter email is invalid"
                }



            }
            else{
                loginEmailET.error="Please enter your email id"
            }

        }

        register_btn.setOnClickListener {
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

    }

   /* override fun onStop() {
        super.onStop()
        userRef.rem(userChangeListener)
    }*/


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home)
        {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }

    override fun onBackPressed() {


        lateinit var alertDialog:AlertDialog;
        var alert:AlertDialog.Builder = AlertDialog.Builder(this@LoginActivity,android.R.style.ThemeOverlay_Material_Dialog_Alert)

        alert.setTitle("Warning")
        alert.setMessage("Do you really want to exit from app?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            finish()
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        })
        alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->  alertDialog.dismiss()})

        alertDialog = alert.create();
        alertDialog.show()

    }
    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun showError(editText: EditText)
    {
        editText.requestFocus()
        editText.isFocusableInTouchMode = true
    }
}
