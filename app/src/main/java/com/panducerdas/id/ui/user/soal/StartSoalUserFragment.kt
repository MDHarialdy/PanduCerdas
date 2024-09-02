package com.panducerdas.id.ui.user.soal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.panducerdas.id.R
import com.panducerdas.id.databinding.FragmentStartSoalUserBinding


class StartSoalUserFragment : Fragment() {

    private lateinit var binding: FragmentStartSoalUserBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStartSoalUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_StartSoalUserFragment_to_soalUserFragment)
        )
    }
}