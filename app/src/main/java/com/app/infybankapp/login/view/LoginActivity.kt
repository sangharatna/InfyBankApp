package com.app.infybankapp.login.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.infybankapp.R
import com.app.infybankapp.login.viewmodel.LoginViewModel
import com.app.infybankapp.model.UserData
import com.app.infybankapp.utilities.Utils
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.di.DaggerFirebaseComponent
import com.app.infybankapp.di.FirebaseComponent
import com.app.infybankapp.di.FirebaseModule
import com.app.infybankapp.option.view.OptionActivity
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.login_activity.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity(){

    lateinit var myComponent: FirebaseComponent
    @Inject
    lateinit var databaseReference:DatabaseReference

    @Inject
    lateinit var users: ArrayList<UserData?>

    var flag: Int = 0
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        //injecting firebase module
        myComponent = DaggerFirebaseComponent.builder().firebaseModule(FirebaseModule()).build() as DaggerFirebaseComponent
        myComponent.inject(this)

        /*AppClass.databaseReference = databaseReference*/
        AppClass.userDataList = users

        initLoginViewModel()
0
        signin.setOnClickListener {
            try {
                getDataFromFirebase()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        register_btn.setOnClickListener {
            loginViewModel.openRegisterScreen(this)
        }

    }

    private fun getDataFromFirebase() {
        Utils().hideSoftKeyBoard(this@LoginActivity, signin)
        if (loginViewModel.validateCredentials(
                loginEmailET.text.toString(),
                passET.text.toString()
            )
        ) {
            Utils().showProgress(this@LoginActivity)

            loginViewModel.getUserData(
                loginEmailET.text.toString(),
                passET.text.toString(),
                passET,
                loginEmailET
            ).observe(this, object :
                Observer<UserData> {
                override fun onChanged(userData: UserData?) {
                    AppClass.progress.dismiss()
                    if (userData?.id != null) {
                        if (userData.password.equals(passET.text.toString()) && userData.email.equals(
                                loginEmailET.text.toString()
                            )
                        ) {
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    OptionActivity::class.java
                                )
                            )
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Invalid Credentials",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

                }
            })
        }
    }

    private fun initLoginViewModel() {
        loginViewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        loginViewModel.showDialogOnBackPress(this@LoginActivity)

    }
}



