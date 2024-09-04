package com.panducerdas.id.ui.user.soal

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.panducerdas.id.R
import java.util.*

class SoalUserFragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var textDescription: TextView
    private lateinit var buttonA: Button
    private lateinit var buttonB: Button
    private lateinit var buttonC: Button
    private lateinit var buttonD: Button
    private var lockedButton: Button? = null
    private var isTtsInitialized = false
    private var isAnswerLocked = false

    // Gesture detector instance
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(requireContext(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_soal_user, container, false)

        // Inisialisasi TextView dan Button
        textDescription = view.findViewById(R.id.tv_desc_soal)
        buttonA = view.findViewById(R.id.buttonA)
        buttonB = view.findViewById(R.id.buttonB)
        buttonC = view.findViewById(R.id.buttonC)
        buttonD = view.findViewById(R.id.buttonD)

        setupGestureDetection(view)

        // Set dummy data dan bacakan soal
        setDummyData()

        return view
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale("id", "ID")
            isTtsInitialized = true
            speakTextDescription() // Bacakan soal saat TTS siap
        }
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }

    private fun setupGestureDetection(view: View) {
        val layout = view.findViewById<ViewGroup>(R.id.lower_layout)
        val upperLayout = view.findViewById<ViewGroup>(R.id.upper_layout)

        // Gesture detector untuk mendeteksi swipe dan double-tap
        gestureDetector = GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if (e1 != null && e2 != null && e2.pointerCount == 1) {
                    // Hanya deteksi jika ada satu jari yang digunakan
                    handleSwipeGesture(e2)
                }
                return true
            }
        })

        // Menambahkan double-tap listener untuk lower layout
        gestureDetector.setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener {
            override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
                return false
            }

            override fun onDoubleTap(p0: MotionEvent): Boolean {
                if (isAnswerLocked) {
                    tts.speak("Menuju soal berikutnya", TextToSpeech.QUEUE_FLUSH, null, null)
                    // Implementasi untuk pindah ke soal berikutnya
                } else {
                    tts.speak("Harap memilih jawaban terlebih dahulu", TextToSpeech.QUEUE_FLUSH, null, null)
                }
                return true
            }

            override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
                return false
            }
        })

        upperLayout.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }

        layout.setOnTouchListener { _, event ->
            if (event.pointerCount == 2 && event.action == MotionEvent.ACTION_MOVE) {
                // Bacakan ulang soal dengan gesture dua jari
                speakTextDescription()
            }
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun handleSwipeGesture(event: MotionEvent) {
        val buttonLocations = listOf(buttonA, buttonB, buttonC, buttonD)

        // Cek jika gesture swipe mengenai salah satu tombol
        for (button in buttonLocations) {
            val location = IntArray(2)
            button.getLocationOnScreen(location)
            val buttonX = location[0]
            val buttonY = location[1]

            if (event.rawX >= buttonX && event.rawX <= buttonX + button.width &&
                event.rawY >= buttonY && event.rawY <= buttonY + button.height) {

                lockAnswer(button) // Kunci jawaban saat swipe mengenai tombol
                break
            }
        }
    }

    private fun lockAnswer(button: Button) {
        if (lockedButton != null && lockedButton != button) {
            lockedButton?.isEnabled = true // Lepas lock dari tombol sebelumnya
        }

        lockedButton = button
        lockedButton?.isEnabled = false
        isAnswerLocked = true

        tts.speak("Jawaban terkunci: ${button.text}. Jika yakin dengan jawaban ini, klik dua kali di bagian layar bawah.", TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun setDummyData() {
        textDescription.text = "Siti selalu membantu ibunya memasak di dapur. Ia suka memotong sayuran dan mencuci piring. Siti merasa senang bisa membantu ibunya.\nPertanyaan:\nApa yang dilakukan Siti di dapur?\nA. Membantu ibunya memasak.\nB. Membaca buku.\nC. Bermain mainan.\nD. Menonton TV"
    }

    private fun speakTextDescription() {
        tts.speak(textDescription.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
    }
}
