package com.example.databindinglogin.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.databindinglogin.data.repositories.UserRespository

class AuthViewModel : ViewModel() {
     var email:String?=null
    var password:String?=null
  var authListener:AuthListener?=null

    fun onLoginButtonClick(view:View)
    {
        authListener?.onStarted()
        if(email.isNullOrEmpty()||password.isNullOrEmpty())
        {
          authListener?.onFailure("invalid email or password!")
            return
        }

        val loginResponse=UserRespository().userLogin(email!!,password!!)
        authListener?.onSuccess(loginResponse)
    }
}