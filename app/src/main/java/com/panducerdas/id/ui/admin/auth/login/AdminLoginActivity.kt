package com.panducerdas.id.ui.admin.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.panducerdas.id.data.ViewModelFactory
import com.panducerdas.id.databinding.ActivityLoginAdminBinding
import com.panducerdas.id.ui.admin.AdminActivity
import com.panducerdas.id.ui.admin.auth.signup.AdminSignupActivity

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginAdminBinding

    private val viewModel by viewModels<AdminLoginViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.btnLogin.setOnClickListener {
           val email = binding.etEmail.text.toString()
           val password = binding.etPassword.text.toString()

           if (email.isNotEmpty() && password.isNotEmpty()){viewModel.getAdmin(email, password).observe(this, Observer{ adminList ->
                   if (adminList.isNullOrEmpty()){
                       Toast.makeText(this, "Email Atau Password Salah", Toast.LENGTH_SHORT).show()
                   } else {
                       val admin = adminList[0].AdminName
                       Toast.makeText(this, "Selamat Datang $admin", Toast.LENGTH_SHORT).show()
                       val intent = Intent(this, AdminActivity::class.java)
                       startActivity(intent)
                       finish()
                   }
               })
           }
       }

        binding.btnDaftarClickable.setOnClickListener{
            startActivity(Intent(this,AdminSignupActivity::class.java))
            finish()
        }

    }
}