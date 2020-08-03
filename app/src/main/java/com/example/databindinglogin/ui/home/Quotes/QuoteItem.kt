package com.example.databindinglogin.ui.home.Quotes

import com.example.databindinglogin.R
import com.example.databindinglogin.data.network.model.QuotesResponse
import com.example.databindinglogin.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: QuotesResponse.Quote
) : BindableItem<ItemQuoteBinding>(){

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}