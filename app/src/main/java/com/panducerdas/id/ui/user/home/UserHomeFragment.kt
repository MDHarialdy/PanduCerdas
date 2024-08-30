package com.panducerdas.id.ui.user.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.panducerdas.id.databinding.FragmentHomeUserBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserHomeFragment : Fragment() {

    private var _binding: FragmentHomeUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserHomeViewModel
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

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(UserHomeViewModel::class.java)

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
