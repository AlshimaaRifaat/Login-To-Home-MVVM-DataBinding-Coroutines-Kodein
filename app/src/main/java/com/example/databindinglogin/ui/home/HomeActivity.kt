package com.example.databindinglogin.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.databindinglogin.R
import com.example.databindinglogin.ui.home.Quotes.QuotesFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(QuotesFragment())
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.homeContainer,
                fragment
            )
            ?.commitNow()
    }
}
