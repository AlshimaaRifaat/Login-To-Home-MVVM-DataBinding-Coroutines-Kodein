package com.example.databindinglogin.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.databindinglogin.R
import com.example.databindinglogin.data.network.MyApi
import com.example.databindinglogin.data.network.NetworkConnectionInterceptor
import com.example.databindinglogin.data.network.model.AuthResponse
import com.example.databindinglogin.data.repositories.UserRepository
import com.example.databindinglogin.databinding.ActivityMainBinding
import com.example.databindinglogin.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.example.databindinglogin.ui.home.Quotes.QuotesFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkConnectionInterceptor=NetworkConnectionInterceptor(this)
        val myApi=MyApi(networkConnectionInterceptor)
        val userRepository=UserRepository(myApi)
        val factory=AuthViewModelFactory(userRepository)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)

        binding.buttonSignIn.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        lifecycleScope.launch {
            try {
                val authResponse = viewModel.userLogin(email, password)
                if(authResponse.isSuccessful) {
                    binding.rootLayout.snackbar(authResponse.message)
                    replaceFragment(QuotesFragment())
                }

            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.root_layout,
                fragment
            )
            ?.commitNow()
    }


}
