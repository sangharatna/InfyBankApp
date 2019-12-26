package com.app.infybankapp

import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import com.app.infybankapp.model.UserData

class AppClass : Application() {


    companion object {

        open val userDataList:ArrayList<UserData?> = ArrayList()

        open lateinit var userObject: UserData;
        open lateinit var selectedUserObject: UserData;

        lateinit var progress: ProgressDialog;

        @Suppress("DEPRECATION")
        open fun showProgress(context: Context) {
            try {
                progress = ProgressDialog(context)
                progress.setMessage("Please Wait...")
                progress.setCancelable(false)
                progress.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        open fun dismissProgress() {
            try {
                progress.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        open fun updateSavedList(user: UserData)
        {
            var indexValue:Int = userDataList.size;
            for(index in userDataList.indices)
            {
                if(user.email.equals(userDataList.get(index)?.email))
                {
                    indexValue = index;
                    break;
                }
            }
            userDataList.removeAt(indexValue)
            userDataList.add(indexValue,user)
        }

    }



}