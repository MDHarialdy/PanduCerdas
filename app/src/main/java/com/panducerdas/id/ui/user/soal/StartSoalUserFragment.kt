package com.panducerdas.id.ui.user.soal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_start_soal_user,container,false)

        binding.startBtn.setOnClickListener {
            val action = StartQuestionFragmentDirections.actionStartFragmentToQuizFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }
}