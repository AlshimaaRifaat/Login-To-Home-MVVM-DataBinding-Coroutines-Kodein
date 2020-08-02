package com.example.databindinglogin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindinglogin.data.network.MyApi
import com.example.databindinglogin.data.network.SafeApiRequest
import com.example.databindinglogin.data.network.model.AuthResponse

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRespository :SafeApiRequest() {
    suspend fun userLogin(email:String,password:String):AuthResponse {
        return apiRequest { MyApi().userLogin(email, password) }
    }
}