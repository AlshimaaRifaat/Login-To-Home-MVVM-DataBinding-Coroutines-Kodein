package com.example.databindinglogin.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.databindinglogin.R
import com.example.databindinglogin.data.network.model.AuthResponse
import com.example.databindinglogin.databinding.ActivityMainBinding
import com.example.databindinglogin.util.hide
import com.example.databindinglogin.util.show
import com.example.databindinglogin.util.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),AuthListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel=viewModel
        viewModel.authListener=this
    }

    override fun onStarted() {
      progress_bar.show()
    }

    override fun onSuccess(user: AuthResponse.User) {
        progress_bar.hide()
        toast("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)

    }
}
