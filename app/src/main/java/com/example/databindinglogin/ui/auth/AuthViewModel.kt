package com.example.databindinglogin.ui.auth

import androidx.lifecycle.ViewModel
import com.example.databindinglogin.data.repositories.UserRepository

class AuthViewModel(private val userRespository: UserRepository) : ViewModel() {

    suspend fun userLogin(
        email:String,
        password:String
    ) = userRespository.userLogin(email,password)
   /*  var email:String?=null
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
    fun onNextTextClick(view: View){
          Intent(view.context,HomeActivity::class.java).also {
              view.context.startActivity(it)
          }
    }*/
}