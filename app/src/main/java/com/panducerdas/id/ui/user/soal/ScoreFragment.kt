package com.panducerdas.id.ui.user.soal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.panducerdas.id.R
import com.panducerdas.id.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private lateinit var binding: FragmentScoreBinding
    private var _binding: FragmentScoreBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

//        val scoreArgs by navArgs<>()
//        binding.scoreNumber.text = scoreArgs.score.toString()


        binding.returnmenu.setOnClickListener {
            val intent = Intent(this.context, SoalUserActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

//        val navController = findNavController()
//        navController.navigate(R.id.action_scoreFragment_to_startFragment)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // handle back event
            view?.findNavController()?.navigate(R.id.action_scoreFragment_to_startFragment)
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}