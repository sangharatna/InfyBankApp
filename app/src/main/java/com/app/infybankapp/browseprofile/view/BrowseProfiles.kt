package com.app.infybankapp.browseprofile.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.MenuItem

import androidx.lifecycle.ViewModelProviders
import com.app.infybankapp.R
import com.app.infybankapp.browseprofile.viewmodel.BrowseProfileViewModel


class BrowseProfiles : AppCompatActivity() {
    lateinit var browseProfileViewModel: BrowseProfileViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_profiles)
        initBrowseViewModel()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Browse Profile"

        browseProfileViewModel.showListofProfile(this@BrowseProfiles)


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
    private fun initBrowseViewModel()
    {
        browseProfileViewModel=ViewModelProviders.of(this)[BrowseProfileViewModel::class.java]
    }

}
