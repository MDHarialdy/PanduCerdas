package com.panducerdas.id.ui.user

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.panducerdas.id.R
import com.panducerdas.id.databinding.ActivityAdminBinding
import com.panducerdas.id.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation
        val viewPager: ViewPager2 = binding.userViewpager

        val adapter = UserPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_home_user -> {
                    viewPager.setCurrentItem(0, false) // Pindah ke Home tanpa animasi
                }
                R.id.aiFragment -> {
                    viewPager.setCurrentItem(1, false) // Pindah ke AI Fragment tanpa animasi
                }
                R.id.fragment_profile_user -> {
                    viewPager.setCurrentItem(2, false) // Pindah ke Profile tanpa animasi
                }
                else -> false
            }
            true
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                navView.menu.getItem(position).isChecked = true
            }
        })

//        val navController = findNavController(R.id.user_nav_host)
//        AppBarConfiguration.Builder(
//
//            R.id.fragment_home_user,
//            R.id.fragment_profile_user,
//            R.id.aiFragment
//        ).build()
//
//
//        navView.setupWithNavController(navController)

    }
}