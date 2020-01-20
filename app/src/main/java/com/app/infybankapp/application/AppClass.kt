package com.app.infybankapp.application

import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import com.app.infybankapp.model.UserData
import com.app.infybankapp.view.updatedata.ProfDetailAct
import com.app.infybankapp.view.updatedata.UpdateEduAct
import com.app.infybankapp.view.updatedata.UpdatePIAct
import com.app.infybankapp.view.updatedata.UpdateSummary
import com.google.firebase.database.DatabaseReference

@Suppress("DEPRECATION")
class AppClass : Application() {


    override fun onCreate() {

        super.onCreate()
        appContext = this.applicationContext
    }

    companion object {

        var myObjectUnderTest: UpdateEduAct = UpdateEduAct()
        var myObjectPITest: UpdatePIAct = UpdatePIAct()
        var myObjectProfileTest: ProfDetailAct = ProfDetailAct()
        var myObjectSummaryTest: UpdateSummary = UpdateSummary()

        lateinit var appContext: Context

        open var userDataList:ArrayList<UserData?> = ArrayList()

        open lateinit var userObject: UserData
        open lateinit var selectedUserObject: UserData

        lateinit var progress: ProgressDialog

        open fun dismissProgress() {
            try {
                progress.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        open fun updateSavedList(user: UserData) {
            try {
                var indexValue: Int = userDataList.size
                for (index in userDataList.indices) {
                    if (user.email.equals(userDataList.get(index)?.email)) {
                        indexValue = index
                        break
                    }
                }
                userDataList.removeAt(indexValue)
                userDataList.add(indexValue, user)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }





}