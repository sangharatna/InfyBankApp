package com.app.infybankapp.di

import com.app.infybankapp.model.UserData
import com.google.firebase.database.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule()
{

    @Singleton
    @Provides
    fun getFirebaseInstance():FirebaseDatabase
    {
        return FirebaseDatabase.getInstance()
    }

    @Singleton
    @Provides
    fun getFirebaseRefereance():DatabaseReference
    {
        return getFirebaseInstance().getReference("userData")
    }


    lateinit var userChangeListener: ValueEventListener

    @Singleton
    @Provides
    fun getFirebaseUsers():ArrayList<UserData?>
    {

        var userDataList:ArrayList<UserData?> = ArrayList()

        userChangeListener  = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var iterable: MutableIterable<DataSnapshot> = dataSnapshot.children

                for(child: DataSnapshot in iterable)
                {
                    userDataList.add(child.getValue(UserData::class.java))
                }

                getFirebaseRefereance().removeEventListener(userChangeListener)
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        getFirebaseRefereance().addValueEventListener(userChangeListener)

        return userDataList
    }
}