package com.example.databindinglogin.data.network.model


import com.google.gson.annotations.SerializedName

data class QuotesResponse(
    @SerializedName("isSuccessful")
    var isSuccessful: Boolean,
    @SerializedName("quotes")
    var quotes: List<Quote>
) {
    data class Quote(
        @SerializedName("author")
        var author: String,
        @SerializedName("created_at")
        var createdAt: String?,
        @SerializedName("id")
        var id: Int,
        @SerializedName("quote")
        var quote: String,
        @SerializedName("thumbnail")
        var thumbnail: String,
        @SerializedName("updated_at")
        var updatedAt: String?
    )
}