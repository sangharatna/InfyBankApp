package com.app.infybankapp.dashboard.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.app.infybankapp.R
import com.app.infybankapp.dashboard.viewmodel.DashboardViewModel
import com.app.infybankapp.application.AppClass
import kotlinx.android.synthetic.main.activity_dashboard.*


class DashboardActivity : AppCompatActivity() {
    lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        //initialize view Model
        initDashboardModel()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Your Profile"

        personal_edit_img.setOnClickListener {  val taskId = AppClass.userObject.id
            dashboardViewModel.editPersonalInformation(this@DashboardActivity)
        }

        summery_edit_img.setOnClickListener {
           dashboardViewModel.editSummaryInformation(this@DashboardActivity)
        }

        education_edit_img.setOnClickListener {
            dashboardViewModel.editEducationalInformation(this@DashboardActivity)
        }
        prof_edit_img.setOnClickListener {
           dashboardViewModel.editProfessionalInformation(this@DashboardActivity)
        }

    }

    override fun onResume() {
        super.onResume()

        //call method from view model
        dashboardViewModel.showDefaultProfile(this@DashboardActivity)


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
    //function for initialize viewModel
    private fun initDashboardModel()
    {
        dashboardViewModel=ViewModelProviders.of(this)[DashboardViewModel::class.java]
    }


}
