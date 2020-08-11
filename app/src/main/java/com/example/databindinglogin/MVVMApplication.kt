package com.example.databindinglogin

import android.app.Application
import com.example.databindinglogin.data.network.MyApi
import com.example.databindinglogin.data.network.NetworkConnectionInterceptor
import com.example.databindinglogin.data.repositories.QuotesRepository
import com.example.databindinglogin.data.repositories.UserRepository
import com.example.databindinglogin.ui.auth.AuthViewModelFactory
import com.example.databindinglogin.ui.home.Quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication :Application(),KodeinAware {
    override val kodein=Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from singleton { QuotesRepository(instance()) }
        bind() from provider{ QuotesViewModelFactory(instance()) }

    }

}