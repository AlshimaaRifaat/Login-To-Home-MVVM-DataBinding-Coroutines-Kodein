package com.example.databindinglogin.auth

import com.example.databindinglogin.data.network.model.AuthResponse

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: AuthResponse.User)
    fun onFailure(message:String)
}