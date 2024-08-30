package com.panducerdas.id.ui.user.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.panducerdas.id.R
import com.panducerdas.id.data.ViewModelFactory
import com.panducerdas.id.databinding.ActivityLoginAdminBinding
import com.panducerdas.id.databinding.ActivityLoginUserBinding
import com.panducerdas.id.ui.admin.AdminActivity
import com.panducerdas.id.ui.admin.auth.login.AdminLoginViewModel
import com.panducerdas.id.ui.admin.auth.signup.AdminSignupActivity
import com.panducerdas.id.ui.user.UserActivity
import com.panducerdas.id.ui.user.auth.signup.UserSignupActivity

class UserLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginUserBinding

    private val viewModel by viewModels<UserLoginViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){viewModel.getUser(email, password).observe(this, Observer{ userList ->
                if (userList.isNullOrEmpty()){
                    Toast.makeText(this, "Email Atau Password Salah", Toast.LENGTH_SHORT).show()
                } else {
                    val user = userList[0].UserName
                    Toast.makeText(this, "Selamat Datang $user", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, UserActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
            }
        }

        binding.btnDaftarUserClickable.setOnClickListener{
            startActivity(Intent(this, UserSignupActivity::class.java))
            finish()
        }

    }
}