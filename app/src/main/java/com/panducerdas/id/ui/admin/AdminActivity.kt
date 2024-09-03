package com.panducerdas.id.ui.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.panducerdas.id.R
import com.panducerdas.id.databinding.ActivityAdminBinding
import com.panducerdas.id.ui.admin.crud.AddExamFragment

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.admin_nav_host)
        navView.setupWithNavController(navController)

        binding.fabAdd.setOnClickListener {
            val addExamFragment = AddExamFragment()
            addExamFragment.show(supportFragmentManager, AddExamFragment::class.java.simpleName)
        }
    }
}
