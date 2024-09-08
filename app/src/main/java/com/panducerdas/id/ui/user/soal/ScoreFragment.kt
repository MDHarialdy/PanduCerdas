package com.panducerdas.id.ui.user.soal

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.GestureDetectorCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.panducerdas.id.R
import com.panducerdas.id.databinding.FragmentScoreBinding

class ScoreFragment : Fragment(),  GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener  {

    private lateinit var binding: FragmentScoreBinding
    private var _binding: FragmentScoreBinding? = null
    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

//        val scoreArgs by navArgs<>()
//        binding.scoreNumber.text = scoreArgs.score.toString()

        // Inisialisasi GestureDetector untuk mendeteksi ketukan dua kali dan geser dua jari
        gestureDetector = GestureDetectorCompat(requireContext(), this)
        gestureDetector.setOnDoubleTapListener(this)

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

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        TODO("NotAS yet implemented")
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        // Pengguna mengetuk dua kali untuk kembali ke StartSoalUserFragment
        findNavController().navigate(R.id.action_scoreFragment_to_startFragment)
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDown(p0: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onShowPress(p0: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLongPress(p0: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        TODO("Not yet implemented")
    }
}
