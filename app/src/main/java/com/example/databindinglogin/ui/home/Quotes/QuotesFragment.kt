package com.example.databindinglogin.ui.home.Quotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.databindinglogin.R
import com.example.databindinglogin.data.network.MyApi
import com.example.databindinglogin.data.network.NetworkConnectionInterceptor
import com.example.databindinglogin.data.network.model.QuotesResponse
import com.example.databindinglogin.data.repositories.QuotesRepository
import com.example.databindinglogin.data.repositories.UserRepository
import com.example.databindinglogin.ui.auth.AuthViewModel
import com.example.databindinglogin.ui.auth.AuthViewModelFactory
import com.example.databindinglogin.util.Coroutines
import com.example.databindinglogin.util.hide
import com.example.databindinglogin.util.show
import com.example.databindinglogin.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_quotes.*

/**
 * A simple [Fragment] subclass.
 */
class QuotesFragment : Fragment() {
    private lateinit var viewModel: QuotesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quotes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkConnectionInterceptor= NetworkConnectionInterceptor(requireContext())
        val myApi= MyApi(networkConnectionInterceptor)
        val userRepository= QuotesRepository(myApi)
        val factory= QuotesViewModelFactory(userRepository)
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)
        bindUI()
    }


    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
            progress_bar.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }


    private fun List<QuotesResponse.Quote>.toQuoteItem() : List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }
}
