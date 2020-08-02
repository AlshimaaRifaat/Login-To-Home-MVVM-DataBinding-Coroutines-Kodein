package com.example.databindinglogin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindinglogin.data.network.MyApi
import com.example.databindinglogin.data.network.model.AuthResponse

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRespository  {
    fun userLogin(email:String,password:String):LiveData<String>
    {
        val loginResonse=MutableLiveData<String>()
        MyApi().userLogin(email,password).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
          loginResonse.value=t.message
            }

            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>
            ) {
                if(response.isSuccessful) {
                    loginResonse.value = response.body()?.message
                }else{
                    loginResonse.value=response.errorBody()?.string()
                }
            }


        })
        return loginResonse
        }
}