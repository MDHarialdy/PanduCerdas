package com.panducerdas.id.ui.user

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.panducerdas.id.ui.user.ai.AiFragment
import com.panducerdas.id.ui.user.home.UserHomeFragment
import com.panducerdas.id.ui.user.profile.UserProfileFragment

class UserPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3 // Jumlah total fragment
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserHomeFragment()
            1 -> UserProfileFragment()
            2 -> AiFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}
