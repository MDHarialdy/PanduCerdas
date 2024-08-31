package com.panducerdas.id.ui.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.panducerdas.id.R
import com.panducerdas.id.databinding.ActivityAdminBinding
import com.panducerdas.id.ui.admin.addClass.AddClassActivity

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.admin_nav_host)
        AppBarConfiguration.Builder(

            R.id.fragment_home_admin,
            R.id.fragment_profile_admin,
        ).build()

        navView.setupWithNavController(navController)

        binding.fabAdd.setOnClickListener{
            startActivity(Intent(this, AddClassActivity::class.java))
            finish()
        }
    }
}
