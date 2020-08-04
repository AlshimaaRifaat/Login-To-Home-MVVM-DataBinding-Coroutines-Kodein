package com.example.databindinglogin.ui.auth

import androidx.lifecycle.ViewModel
import com.example.databindinglogin.data.repositories.UserRepository

class AuthViewModel(private val userRespository: UserRepository) : ViewModel() {
    var authListener:AuthListener?=null

    suspend fun userLogin(
        email:String,
        password:String
    ) = userRespository.userLogin(email,password)

}