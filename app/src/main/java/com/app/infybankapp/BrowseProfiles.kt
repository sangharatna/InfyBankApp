package com.app.infybankapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.infybankapp.model.UserData
import org.json.JSONObject

class BrowseProfiles : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_profiles)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Browse Profile"


        val recyclerView = findViewById(R.id.recylerView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this@BrowseProfiles, 2)
        val adapter = CustomAdapter(AppClass.userDataList,this@BrowseProfiles)
        recyclerView.adapter = adapter

    }


    class CustomAdapter(val userList: ArrayList<UserData?>, var context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


        //this method is returning the view for each item in the list
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.cell_layout, parent, false)
            return ViewHolder(v,context)
        }


        //this method is binding the data on the list
        override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
            userList[position]?.let { holder.bindItems(it) }
        }

        //this method is giving the size of the list
        override fun getItemCount(): Int {
            return userList.size
        }

        //the class is hodling the list view
        class ViewHolder(itemView: View,con:Context) : RecyclerView.ViewHolder(itemView) {

            var contxt = con;

            fun bindItems(user: UserData) {
                val textViewName = itemView.findViewById(R.id.name_cell_tv) as TextView
                val textViewAddress  = itemView.findViewById(R.id.domain_tv) as TextView
                textViewName.text = user.name


                try {

                    if(!user.experiance.equals("NA")) {

                        var jsonObject: JSONObject = JSONObject(user.experiance)

                        var eduString: String = "" +
                                jsonObject.getString("design")

                        textViewAddress.text = eduString
                    }
                    else
                    {
                        textViewAddress.text = "NA"
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    textViewAddress.text = "NA"
                }


                itemView.setOnClickListener {
                    AppClass.selectedUserObject = user;
                    val intent = Intent (contxt, ViewProfile::class.java)
                    contxt.startActivity(intent)
                }

            }
        }
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
}
