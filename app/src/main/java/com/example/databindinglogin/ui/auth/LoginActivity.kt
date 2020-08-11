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

import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.example.databindinglogin.ui.home.Quotes.QuotesFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.generic.instance
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware

class LoginActivity : AppCompatActivity(),AuthListener,KodeinAware {
    override val kodein by kodein()
    private val factory:AuthViewModelFactory by instance()

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val networkConnectionInterceptor=NetworkConnectionInterceptor(this)
        val myApi=MyApi(networkConnectionInterceptor)
        val userRepository=UserRepository(myApi)
        val factory=AuthViewModelFactory(userRepository)*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        viewModel.authListener=this
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
                    onSuccess(authResponse.user)
                    binding.rootLayout.snackbar(authResponse.message)
                    replaceFragment(QuotesFragment())
                }

            } catch (e: ApiException) {
                e.printStackTrace()
               onFailure(e.message.toString())

            } catch (e: NoInternetException) {
                e.printStackTrace()
                onFailure(e.message.toString())
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

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: AuthResponse.User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)

    }


}
