package com.example.databindinglogin.data.network.model


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("isSuccessful")
    var isSuccessful: Boolean,
    @SerializedName("message")
    var message: String,
    @SerializedName("user")
    var user: User
) {
    data class User(
        @SerializedName("created_at")
        var createdAt: String,
        @SerializedName("email")
        var email: String,
        @SerializedName("email_verified_at")
        var emailVerifiedAt: Any,
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("updated_at")
        var updatedAt: String
    )
}