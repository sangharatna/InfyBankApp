package com.app.infybankapp.option.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.app.infybankapp.R
import com.app.infybankapp.option.viewmodel.OptionViewModel
import kotlinx.android.synthetic.main.activity_option.*

class OptionActivity : AppCompatActivity() {

    lateinit var optionViewModel: OptionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Dashboard"
        initOptionViewModel()

        view_profile.setOnClickListener {
            optionViewModel.showProfile(this@OptionActivity)
        }

        find_profile.setOnClickListener {
            optionViewModel.browseProfile(this@OptionActivity)

        }

    }

    private fun initOptionViewModel() {
        optionViewModel=ViewModelProviders.of(this)[OptionViewModel::class.java]
    }

    override fun onBackPressed() {
        optionViewModel.showDialog(this@OptionActivity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home)
        {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
