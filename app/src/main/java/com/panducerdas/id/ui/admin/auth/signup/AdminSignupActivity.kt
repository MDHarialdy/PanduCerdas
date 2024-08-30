package com.panducerdas.id.ui.admin.auth.signup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.panducerdas.id.R
import com.panducerdas.id.data.ViewModelFactory
import com.panducerdas.id.data.database.AdminEntity
import com.panducerdas.id.databinding.ActivitySignupAdminBinding
import kotlin.math.log

class AdminSignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupAdminBinding
    private val viewModel by viewModels<AdminSignupViewModel> {ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener{
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val name = binding.nameEditText.text.toString()

            val adminEntity = AdminEntity(email, name, password)

            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
                viewModel.insertAdmin(adminEntity)
                Log.d("SignUpActivity", "Admin dengan nama ${name} terdaftar")
                Toast.makeText(this, "Admin dengan nama ${name} terdaftar", Toast.LENGTH_SHORT).show()
            }
        }


    }
}