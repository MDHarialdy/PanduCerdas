package com.panducerdas.id.ui.admin

import ExamAdapter
import ExamViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.panducerdas.id.databinding.FragmentAdminBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AdminFragment : Fragment() {

    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExamViewModel
    private lateinit var adapter: ExamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and Adapter
        adapter = ExamAdapter()
        binding.rvAdminFragment.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAdminFragment.adapter = adapter

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(ExamViewModel::class.java)

        // Collect data from ViewModel and submit to adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.exams.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
