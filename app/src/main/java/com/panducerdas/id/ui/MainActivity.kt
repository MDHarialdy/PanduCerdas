package com.panducerdas.id.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.panducerdas.id.R
import com.panducerdas.id.databinding.ActivityMainBinding
import com.panducerdas.id.ui.admin.AdminActivity
import com.panducerdas.id.ui.admin.auth.login.AdminLoginActivity
import com.panducerdas.id.ui.user.auth.login.UserLoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUser.setOnClickListener{
            startActivity(Intent(this, UserLoginActivity::class.java))
        }

        binding.btnAdmin.setOnClickListener{
            startActivity(Intent(this, AdminLoginActivity::class.java))
        }
    }
}