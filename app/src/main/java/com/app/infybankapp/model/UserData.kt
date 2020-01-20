package com.app.infybankapp.model

data class UserData(var id:String?,var name: String, var email: String, var mobile: String, var password: String, var address: String,var summary:String,var education:String,var experience:String,var skill:String)
{
    constructor() : this("","","","","","","NA","NA","NA","NA")
}