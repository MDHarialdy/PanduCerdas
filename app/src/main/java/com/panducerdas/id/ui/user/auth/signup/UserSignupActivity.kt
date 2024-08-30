package com.panducerdas.id.ui.user.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.panducerdas.id.R
import com.panducerdas.id.data.ViewModelFactory
import com.panducerdas.id.data.database.AdminEntity
import com.panducerdas.id.data.database.UserEntity
import com.panducerdas.id.databinding.ActivityLoginUserBinding
import com.panducerdas.id.databinding.ActivitySignupAdminBinding
import com.panducerdas.id.databinding.ActivitySignupUserBinding
import com.panducerdas.id.ui.admin.auth.login.AdminLoginActivity
import com.panducerdas.id.ui.admin.auth.signup.AdminSignupViewModel
import com.panducerdas.id.ui.user.auth.login.UserLoginActivity

class UserSignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupUserBinding
    private val viewModel by viewModels<UserSignupViewModel> { ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener{
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val name = binding.nameEditText.text.toString()

            val userEntity = UserEntity(email, name, password)

            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
                viewModel.insertUser(userEntity)
                Log.d("SignUpActivity", "Admin dengan nama ${name} terdaftar")

                binding.apply {
                    emailEditText.setText("")
                    nameEditText.setText("")
                    passwordEditText.setText("")
                }

                Toast.makeText(this, "Admin dengan nama ${name} terdaftar", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this, UserLoginActivity::class.java))
            finish()
        }


    }
}