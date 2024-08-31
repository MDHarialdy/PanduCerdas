package com.panducerdas.id.ui.admin.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.panducerdas.id.data.ViewModelFactory
import com.panducerdas.id.databinding.FragmentHomeAdminBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AdminHomeFragment : Fragment() {

    private var _binding: FragmentHomeAdminBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AdminHomeViewModel> { ViewModelFactory.getInstance(requireContext()) }

    private lateinit var adapter: AdminHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and Adapter
        adapter = AdminHomeAdapter()
        binding.rvAdminFragment.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAdminFragment.adapter = adapter

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
