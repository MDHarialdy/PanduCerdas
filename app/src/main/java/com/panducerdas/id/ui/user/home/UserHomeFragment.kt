package com.panducerdas.id.ui.user.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.panducerdas.id.data.ViewModelFactory
import com.panducerdas.id.databinding.FragmentHomeUserBinding
import com.panducerdas.id.ui.admin.home.AdminHomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserHomeFragment : Fragment() {

    private var _binding: FragmentHomeUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UserHomeViewModel> { ViewModelFactory.getInstance(requireContext()) }
    private lateinit var adapter: UserHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and Adapter
        adapter = UserHomeAdapter()
        binding.rvUserFragment.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUserFragment.adapter = adapter


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
