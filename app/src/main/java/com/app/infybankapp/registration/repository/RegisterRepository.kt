package com.app.infybankapp.registration.repository

import android.app.Application
import com.app.infybankapp.model.UserData

class RegisterRepository(application:Application) {

    var application:Application
    lateinit var data:MutableList<UserData>
    init {
        this.application=application
    }

}