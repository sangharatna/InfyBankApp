package com.app.infybankapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_option.*

class OptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Dashboard"

        view_profile.setOnClickListener {
            startActivity(Intent(this@OptionActivity, DashboardActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        find_profile.setOnClickListener {
            startActivity(Intent(this@OptionActivity, BrowseProfiles::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

    }

    override fun onBackPressed() {

        lateinit var alertDialog: AlertDialog;
        var alert: AlertDialog.Builder = AlertDialog.Builder(this@OptionActivity,android.R.style.ThemeOverlay_Material_Dialog_Alert)

        alert.setTitle("Warning")
        alert.setMessage("Do you want to logout?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            finish()
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            AppClass.userDataList.clear()


        })
        alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->  alertDialog.dismiss()})
        alertDialog = alert.create();
        alertDialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home)
        {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
