package com.panducerdas.id.ui.admin.auth.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.panducerdas.id.R
import com.panducerdas.id.data.ViewModelFactory
import com.panducerdas.id.databinding.ActivityAdminBinding
import com.panducerdas.id.databinding.ActivityLoginAdminBinding

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginAdminBinding

    private val viewModel by viewModels<AdminLoginViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val admin = viewModel.getAdmin(email)
    }
}