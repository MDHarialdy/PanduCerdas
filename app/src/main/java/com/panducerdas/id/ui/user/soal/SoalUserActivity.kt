package com.panducerdas.id.ui.user.soal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.panducerdas.id.R
import com.panducerdas.id.databinding.ActivitySoalUserBinding

class SoalUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySoalUserBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_soal_user)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Soal Ujian"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

}