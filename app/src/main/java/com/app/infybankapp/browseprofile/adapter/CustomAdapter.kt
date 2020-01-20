package com.app.infybankapp.browseprofile.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.infybankapp.R
import com.app.infybankapp.model.UserData
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.viewprofile.view.ViewProfile
import org.json.JSONObject

class CustomAdapter(val userList: ArrayList<UserData?>, var context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cell_layout, parent, false)
        return ViewHolder(
            v,
            context
        )
    }


    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        userList[position]?.let { holder.bindItems(it) }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is holding the list view
    class ViewHolder(itemView: View, con: Context) : RecyclerView.ViewHolder(itemView) {

        var contxt = con

        fun bindItems(user: UserData) {
            val textViewName = itemView.findViewById(R.id.name_cell_tv) as TextView
            val textViewAddress  = itemView.findViewById(R.id.domain_tv) as TextView
            textViewName.text = user.name


            try {

                if(!user.experience.equals("NA")) {

                    var jsonObject: JSONObject = JSONObject(user.experience)

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
                AppClass.selectedUserObject = user
                val intent = Intent (contxt, ViewProfile::class.java)
                contxt.startActivity(intent)
            }

        }
    }
}