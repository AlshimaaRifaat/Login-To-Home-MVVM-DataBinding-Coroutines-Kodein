package com.example.databindinglogin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindinglogin.data.network.MyApi
import com.example.databindinglogin.data.network.NetworkConnectionInterceptor
import com.example.databindinglogin.data.network.SafeApiRequest
import com.example.databindinglogin.data.network.model.AuthResponse




class UserRepository(private val api: MyApi) :SafeApiRequest() {

    suspend fun userLogin(email:String,password:String):AuthResponse {

        return apiRequest { api.userLogin(email, password) }
    }
}