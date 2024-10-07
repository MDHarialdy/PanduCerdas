package com.panducerdas.id.ui.user

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.panducerdas.id.R
import com.panducerdas.id.databinding.ActivityUserBinding
import com.panducerdas.id.util.destroyPorcupine
import com.panducerdas.id.util.initPorcupine
import com.panducerdas.id.util.pausePorcupine

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
//    private lateinit var gestureDetector: GestureDetector
//    private var isScrolling = false // Flag to track scrolling state

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation
        val viewPager: ViewPager2 = binding.userViewpager

        //command voice init
        initPorcupine(this@UserActivity)

        val adapter = UserPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1

        // Disable swipe by default to force gesture-based navigation
        viewPager.isUserInputEnabled = false

        // Setup BottomNavigation to synchronize with ViewPager2
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_home_user -> {
                    viewPager.setCurrentItem(0, false) // Switch to Home without animation
                }
                R.id.aiFragment -> {
                    viewPager.setCurrentItem(1, false) // Switch to AI Fragment without animation
                }
                R.id.fragment_profile_user -> {
                    viewPager.setCurrentItem(2, false) // Switch to Profile without animation
                }
            }
            true
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                navView.menu.getItem(position).isChecked = true
            }
        })


        // Setup gesture detection to control ViewPager2 navigation
//        setupGestures(viewPager)
//    }



//    private fun setupGestures(viewPager: ViewPager2) {
//        // Initialize GestureDetector with a custom listener
//        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
//            override fun onScroll(
//                e1: MotionEvent?, e2: MotionEvent,
//                distanceX: Float, distanceY: Float
//            ): Boolean {
//                // Check if two fingers are used and not already scrolling
//                if (e2.pointerCount == 2 && !isScrolling) {
//                    isScrolling = true // Set scrolling state to true
//
//                    // Scroll down (next item)
//                    if (distanceY < 0) {
//                        val nextItem = viewPager.currentItem + 1
//                        if (nextItem < viewPager.adapter?.itemCount ?: 0) {
//                            viewPager.setCurrentItem(nextItem, true)
//                        }
//                    }
//                    // Scroll up (previous item)
//                    else if (distanceY > 0) {
//                        val previousItem = viewPager.currentItem - 1
//                        if (previousItem >= 0) {
//                            viewPager.setCurrentItem(previousItem, true)
//                        }
//                    }
//                }
//                return super.onScroll(e1, e2, distanceX, distanceY)
//            }
//        })

//        // Set an onTouchListener on the root layout to detect gestures
//        binding.userViewpager.setOnTouchListener { _, event ->
//            if (event.action == MotionEvent.ACTION_UP) {
//                // Reset scrolling state when the user lifts their fingers
//                isScrolling = false
//            }
//
//            gestureDetector.onTouchEvent(event)
//            true
//        }
    }


    override fun onPause() {
        super.onPause()
        pausePorcupine()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyPorcupine()
    }
}
