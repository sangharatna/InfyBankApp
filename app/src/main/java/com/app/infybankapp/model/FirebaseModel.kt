package com.app.infybankapp.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.app.infybankapp.application.AppClass
import com.google.firebase.database.*
import javax.inject.Inject

class FirebaseModel {
    lateinit var userChangeListener: ValueEventListener
    lateinit var context: Context


    constructor(context: Context) {
        this.context = context
    }

    fun checkUser(email: String, password: String): MutableLiveData<UserData> {

        var mutableLiveData: MutableLiveData<UserData> = MutableLiveData()

        for (child: UserData? in AppClass.userDataList) {
            if (child!!.email.equals(email) && child!!.password.equals(password)) {
                AppClass.userObject = child
                mutableLiveData.setValue(child)

            }
        }
        return mutableLiveData
    }
}