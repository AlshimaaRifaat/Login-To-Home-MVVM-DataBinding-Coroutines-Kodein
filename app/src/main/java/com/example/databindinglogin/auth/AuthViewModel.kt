package com.example.databindinglogin.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.databindinglogin.data.repositories.UserRepository
import com.example.databindinglogin.util.ApiException
import com.example.databindinglogin.util.Coroutines
import com.example.databindinglogin.util.NoInternetException

class AuthViewModel(private val userRespository: UserRepository) : ViewModel() {
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

           try {
               val authResponse=userRespository.userLogin(email!!,password!!)
               authResponse.user.let {
                   authListener?.onSuccess(it)
                   return@main
               }
               authListener?.onFailure(authResponse.message)
           }catch (e:ApiException)
           {
               authListener?.onFailure(e.message!!)
           }catch (e:NoInternetException)
           {
               authListener?.onFailure(e.message!!)
           }
        }


    }
}