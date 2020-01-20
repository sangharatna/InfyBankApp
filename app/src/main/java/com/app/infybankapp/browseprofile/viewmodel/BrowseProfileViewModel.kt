package com.app.infybankapp.browseprofile.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.infybankapp.R
import com.app.infybankapp.browseprofile.adapter.CustomAdapter
import com.app.infybankapp.browseprofile.view.BrowseProfiles
import com.app.infybankapp.application.AppClass

class BrowseProfileViewModel(application: Application):AndroidViewModel(application) {
     fun showListofProfile(browseProfiles: BrowseProfiles)
    {
        val recyclerView = browseProfiles.findViewById(R.id.recylerView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(browseProfiles, 2)

        Log.e("UserDataListSize",">> "+AppClass.userDataList.size)

        val adapter =
            CustomAdapter(
                AppClass.userDataList,
                browseProfiles
            )
        recyclerView.adapter = adapter
    }

}