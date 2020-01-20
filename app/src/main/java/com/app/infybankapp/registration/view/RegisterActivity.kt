package com.app.infybankapp.registration.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.app.infybankapp.R
import com.app.infybankapp.di.DaggerFirebaseComponent
import com.app.infybankapp.di.FirebaseComponent
import com.app.infybankapp.di.FirebaseModule
import com.app.infybankapp.registration.viewmodel.RegistrationViewModel
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject


class RegisterActivity : AppCompatActivity() {

    lateinit var myComponent: FirebaseComponent
    lateinit var registrationViewModel : RegistrationViewModel
    // The validator for the email input field.
    private var mEmailValidator: EmailValidator? = null

    @Inject
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        myComponent = DaggerFirebaseComponent.builder().firebaseModule(FirebaseModule()).build() as DaggerFirebaseComponent
        myComponent.inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Register with us"

        initViewModel()

        signUp.setOnClickListener {
            if(registrationViewModel.validateCredentials(nameET, mobileET,passET,addressET,emailET, this@RegisterActivity)){
                registerUser()
            }
        }

        // Setup field validators.
        mEmailValidator = EmailValidator()
        emailET.addTextChangedListener(mEmailValidator)

    }

    private fun initViewModel() {
        registrationViewModel = ViewModelProviders.of(this)[RegistrationViewModel::class.java]
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



    private fun registerUser() {

        registrationViewModel.registerUser(databaseReference,nameET, mobileET,passET,addressET,emailET, this@RegisterActivity)
    }

}
