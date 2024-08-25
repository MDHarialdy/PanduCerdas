package com.panducerdas.id.ui.exam

import ExamAdapter
import ExamViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.panducerdas.id.databinding.ActivityExamBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ExamActivity : AppCompatActivity() {

    private lateinit var viewModel: ExamViewModel
    private lateinit var adapter: ExamAdapter
    private lateinit var binding: ActivityExamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi RecyclerView dan Adapter
        val recyclerView = binding.rvExamActivity
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExamAdapter()
        recyclerView.adapter = adapter


        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(ExamViewModel::class.java)


        lifecycleScope.launch {
            viewModel.exams.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}

