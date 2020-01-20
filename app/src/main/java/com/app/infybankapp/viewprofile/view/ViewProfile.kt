package com.app.infybankapp.viewprofile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.app.infybankapp.R
import com.app.infybankapp.viewprofile.viewmodel.ViewProfileViewModel

class ViewProfile : AppCompatActivity() {
    lateinit var viewProfileViewModel: ViewProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_profile)
        initViewProfileViewModel()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"

    }

    override fun onResume() {
        super.onResume()
        viewProfileViewModel.showProfile(this@ViewProfile)


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


    private fun initViewProfileViewModel() {
        viewProfileViewModel = ViewModelProviders.of(this)[ViewProfileViewModel::class.java]
    }

}
