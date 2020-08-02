package com.example.databindinglogin.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.databindinglogin.data.repositories.UserRespository
import com.example.databindinglogin.util.Coroutines

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

        Coroutines.main{
            val response=UserRespository().userLogin(email!!,password!!)
            if(response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }


    }
}