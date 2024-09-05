package com.panducerdas.id.ui.user.soal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.panducerdas.id.databinding.ActivitySoalUserBinding

class SoalUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySoalUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySoalUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Soal Ujian"
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }
}
