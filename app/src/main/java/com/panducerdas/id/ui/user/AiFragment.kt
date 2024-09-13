package com.panducerdas.id.ui.user

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import com.panducerdas.id.databinding.FragmentAiBinding
import java.util.Locale

class AiFragment : Fragment(), GestureDetector.OnDoubleTapListener {

    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var binding: FragmentAiBinding
    private lateinit var tts: TextToSpeech
    private var isTtsReady = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale("id", "ID")
                isTtsReady = true

            }
        }

       gestureDetector.setOnDoubleTapListener(this)

    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        return false
    }

    override fun onDoubleTap(p0: MotionEvent): Boolean {
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        return false
    }

}