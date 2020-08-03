package com.example.databindinglogin.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databindinglogin.R
import com.example.databindinglogin.data.network.MyApi
import com.example.databindinglogin.data.network.NetworkConnectionInterceptor
import com.example.databindinglogin.data.network.model.AuthResponse
import com.example.databindinglogin.data.repositories.UserRepository
import com.example.databindinglogin.databinding.ActivityMainBinding
import com.example.databindinglogin.util.hide
import com.example.databindinglogin.util.show
import com.example.databindinglogin.util.snackbar
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(),AuthListener {
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
        binding.viewmodel=viewModel
        viewModel.authListener=this
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
