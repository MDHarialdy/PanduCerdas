package com.panducerdas.id.ui.user.soal

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.panducerdas.id.R
import com.panducerdas.id.databinding.FragmentStartSoalUserBinding
import java.util.Locale

class StartSoalUserFragment : Fragment(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private lateinit var binding: FragmentStartSoalUserBinding
    private lateinit var tts: TextToSpeech
    private lateinit var gestureDetector: GestureDetectorCompat
    private var isTtsReady = false // Flag untuk melacak apakah TTS sudah siap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStartSoalUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi TextToSpeech
        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale("id", "ID") // Set TTS ke bahasa Indonesia
                isTtsReady = true // Tandai bahwa TTS sudah siap
                checkIfReadyToSpeak() // Cek apakah kedua proses sudah selesai
            }
        }

        // Inisialisasi GestureDetector untuk mendeteksi ketukan dua kali dan geser dua jari
        gestureDetector = GestureDetectorCompat(requireContext(), this)
        gestureDetector.setOnDoubleTapListener(this)

        binding.startSoalLayoutGesture.setOnTouchListener { v, event ->
            // Abaikan input jika TTS sedang berbicara
//            if (tts.isSpeaking) return@setOnTouchListener true
            gestureDetector.onTouchEvent(event)
            if (event.pointerCount == 2) {
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> {
                        // Deteksi gerakan dua jari
                        val deltaY = event.getY(1) - event.getY(0) // Menghitung pergerakan vertikal
                        if (deltaY > 12) {
                            // Jika usap ke bawah lebih dari 12px, bacakan ulang soal
                            bacakanTutor()
                        }
                    }
                }
            }
            true
        }

        // Set listener untuk gesture pada root layout
        binding.root.setOnTouchListener { _, event ->
            // Abaikan input jika TTS sedang berbicara
//            if (tts.isSpeaking) return@setOnTouchListener true
            gestureDetector.onTouchEvent(event)
        }

        // Bacakan instruksi ketika TTS siap
        checkIfReadyToSpeak()
    }

    private fun checkIfReadyToSpeak() {
        if (isTtsReady) {
            // Jika TTS sudah siap, bacakan instruksi
            bacakanTutor()
        }
    }

    private fun bacakanTutor() {
        val textToSpeak = "Sebelum kamu mengikuti ujian, dengarkan petunjuk ini baik-baik. " +
                "Cara memilih jawaban adalah dengan menekan tombol yang berada di bagian atas layar. " +
                "Setelah menekan tombol, tombol itu akan terkunci. " +
                "Jika sudah memilih jawaban, kamu bisa maju ke soal berikutnya dengan mengetuk dua kali di bagian bawah layar. " +
                "Jika kamu ingin mendengarkan soal dan jawaban lagi, geser dua jari ke bawah di layar. Ini akan membacakan ulang soal yang sedang kamu kerjakan. " +
                "Jika kamu sudah mengerti, ketuk dua kali untuk memulai soal pertama. Jika belum mengerti, geser dua jari ke bawah untuk mengulang instruksi."

        tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean = false

    override fun onDoubleTap(event: MotionEvent): Boolean {
        // Abaikan input jika TTS sedang berbicara
//        if (tts.isSpeaking) return true

        // Pengguna mengetuk dua kali untuk memulai soal pertama
        if (isTtsReady) {
            tts.speak("Mulai soal pertama.", TextToSpeech.QUEUE_FLUSH, null, null)
            // Navigasi ke soal pertama
            findNavController().navigate(R.id.action_StartSoalUserFragment_to_soalUserFragment)
        }
        return true
    }

    override fun onFling(
        e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float
    ): Boolean {
        // Abaikan input jika TTS sedang berbicara
//        if (tts.isSpeaking) return true

        // Deteksi geser dua jari ke bawah untuk mengulang instruksi
        if (e1 != null && e2 != null && e1.pointerCount == 2) {
            val deltaY = e2.y - e1.y
            if (deltaY > 12) {
                // Jika geser ke bawah lebih dari 12px, ulangi instruksi
                bacakanTutor()
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
    }

    // Fungsi GestureDetector lainnya (diimplementasikan sesuai kebutuhan, namun tidak dipakai di sini)
    override fun onShowPress(e: MotionEvent) {}
    override fun onSingleTapUp(e: MotionEvent): Boolean = false
    override fun onDown(e: MotionEvent): Boolean = true
    override fun onScroll(e1: MotionEvent?, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean = false
    override fun onLongPress(e: MotionEvent) {}
    override fun onDoubleTapEvent(e: MotionEvent): Boolean = false
}
