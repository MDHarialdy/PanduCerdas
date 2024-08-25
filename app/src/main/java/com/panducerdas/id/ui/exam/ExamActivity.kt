package com.panducerdas.id.ui.exam

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.panducerdas.id.R
import com.panducerdas.id.databinding.ActivityExamBinding

class ExamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}