package com.example.databindinglogin.ui.home.Quotes

import androidx.lifecycle.ViewModel
import com.example.databindinglogin.data.repositories.QuotesRepository
import com.example.databindinglogin.util.lazyDeferred

class QuotesViewModel(quotesRepository: QuotesRepository):ViewModel(){

    val quotes by lazyDeferred{
        quotesRepository.getQuotes()
    }
}