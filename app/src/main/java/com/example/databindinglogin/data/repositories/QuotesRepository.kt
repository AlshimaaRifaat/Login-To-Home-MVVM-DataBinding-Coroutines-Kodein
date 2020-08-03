package com.example.databindinglogin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindinglogin.data.network.MyApi
import com.example.databindinglogin.data.network.SafeApiRequest
import com.example.databindinglogin.data.network.model.AuthResponse
import com.example.databindinglogin.data.network.model.QuotesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository (private val api: MyApi) : SafeApiRequest() {

   private val quotes=MutableLiveData<List<QuotesResponse.Quote>>()

    private suspend fun fetchQuotes()
    {
        val response=apiRequest {api.getQuotes()  }
        quotes.postValue(response.quotes)
    }

     suspend fun getQuotes():LiveData<List<QuotesResponse.Quote>>
    {
        return withContext(Dispatchers.IO){
            fetchQuotes()
            return@withContext quotes
        }
    }
}