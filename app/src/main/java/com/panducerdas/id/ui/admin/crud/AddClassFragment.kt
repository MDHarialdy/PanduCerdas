package com.panducerdas.id.ui.admin.crud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.panducerdas.id.R
import com.panducerdas.id.data.ViewModelFactory
import com.panducerdas.id.databinding.FragmentAddClassBinding
import com.panducerdas.id.databinding.FragmentHomeUserBinding
import com.panducerdas.id.ui.user.home.UserHomeAdapter
import com.panducerdas.id.ui.user.home.UserHomeViewModel

class  AddClassFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _binding: FragmentAddClassBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddClassBinding.inflate(inflater, container, false)
        return binding.root
    }
}