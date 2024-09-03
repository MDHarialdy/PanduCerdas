package com.panducerdas.id.ui.user.soal

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.panducerdas.id.R
import java.util.*

class SoalUserFragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private var lockedButton: Button? = null
    private var tapCount = 0
    private var lastTapTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(requireContext(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_soal_user, container, false)

        setupButtons(view)
        setupGestureDetection(view)

        return view
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.getDefault()
        }
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }

    private fun setupButtons(view: View) {
        val buttonA: Button = view.findViewById(R.id.buttonA)
        val buttonB: Button = view.findViewById(R.id.buttonB)
        val buttonC: Button = view.findViewById(R.id.buttonC)
        val buttonD: Button = view.findViewById(R.id.buttonD)

        val buttons = listOf(buttonA, buttonB, buttonC, buttonD)

        for (button in buttons) {
            button.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        handleButtonTouch(v as Button)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun handleButtonTouch(button: Button) {
        if (lockedButton != null && lockedButton != button) {
            // Lepas lock dari tombol sebelumnya
            lockedButton?.isEnabled = true
        }

        if (lockedButton == button) {
            // Jika tombol yang sama disentuh, lepas lock
            lockedButton = null
            button.isEnabled = true
        } else {
            // Lock tombol yang baru disentuh
            lockedButton = button
            button.isEnabled = false
            tts.speak(button.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun setupGestureDetection(view: View) {
        val textDescription: TextView = view.findViewById(R.id.tv_desc_soal)
        val layout = view.findViewById<ViewGroup>(R.id.lower_layout)

        layout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastTapTime < 500) {
                    tapCount++
                    if (tapCount == 2) {
                        lockAnswerAndNext()
                        tapCount = 0
                    } else if (tapCount == 3) {
                        goToPreviousQuestion()
                        tapCount = 0
                    }
                } else {
                    tapCount = 1
                }
                lastTapTime = currentTime
            }
            true
        }

        textDescription.setOnTouchListener { _, event ->
            if (event.pointerCount == 2 && event.action == MotionEvent.ACTION_MOVE) {
                // Implementasi usap dua jari dari atas ke bawah
                showQuestion()
            }
            true
        }
    }

    private fun lockAnswerAndNext() {
        tts.speak("Jawaban terkunci, menuju soal berikutnya", TextToSpeech.QUEUE_FLUSH, null, null)
        // Implementasi navigasi ke soal berikutnya
    }

    private fun goToPreviousQuestion() {
        tts.speak("Kembali ke soal sebelumnya", TextToSpeech.QUEUE_FLUSH, null, null)
        // Implementasi navigasi ke soal sebelumnya
    }

    private fun showQuestion() {
        tts.speak("Menampilkan soal", TextToSpeech.QUEUE_FLUSH, null, null)
        // Implementasi menampilkan soal
    }
}
