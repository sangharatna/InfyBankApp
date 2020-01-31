package com.app.infybankapp.di

import com.app.infybankapp.login.view.LoginActivity
import com.app.infybankapp.registration.view.RegisterActivity
import com.app.infybankapp.updatedata.ProfDetailAct
import com.app.infybankapp.updatedata.UpdateEduAct
import com.app.infybankapp.updatedata.UpdatePIAct
import com.app.infybankapp.updatedata.UpdateSummary
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(FirebaseModule::class))
interface FirebaseComponent {
    fun inject(activity: LoginActivity)
    fun inject(activity: RegisterActivity)
    fun inject(activity: UpdateSummary)
    fun inject(activity: UpdatePIAct)
    fun inject(activity: UpdateEduAct)
    fun inject(activity: ProfDetailAct)
}