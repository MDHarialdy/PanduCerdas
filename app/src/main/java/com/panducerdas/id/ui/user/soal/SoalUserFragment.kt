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
import androidx.lifecycle.ViewModel
import com.panducerdas.id.data.ViewModelFactory
import com.panducerdas.id.databinding.FragmentSoalUserBinding
import java.util.*

class SoalUserFragment : Fragment(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private lateinit var binding: FragmentSoalUserBinding
    private lateinit var tts: TextToSpeech
    private lateinit var gestureDetector: GestureDetectorCompat
    private val soalViewModel by viewModels<SoalUserViewModel>{ViewModelFactory.getInstance(requireContext())}
    private var soalText: String = ""
    private var pilihanJawaban: List<String> = listOf()
    private var soalIndex = 0 // Indeks soal, dimulai dari 0
    private var jawabanTerkunci: String? = null
    private var tombolTerkunci: View? = null // Menyimpan referensi tombol yang sedang terkunci

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSoalUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi TextToSpeech
        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale("id", "ID") // Set TTS ke bahasa Indonesia
            }
        }

        // Inisialisasi GestureDetector untuk mendeteksi gestur
        gestureDetector = GestureDetectorCompat(requireContext(), this)
        gestureDetector.setOnDoubleTapListener(this) // Untuk mendeteksi double tap

        // Set listener untuk gesture pada lower_layout
        binding.lowerLayout.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }

        // Ambil data soal pertama dari ViewModel
        loadSoal()

        // Set listener untuk setiap tombol jawaban
        setupButtonListeners()
    }

    private fun loadSoal() {
        soalViewModel.getAllSoal().observe(viewLifecycleOwner) { soalList ->
            if (soalList != null && soalIndex < soalList.size) {
                val soal = soalList[soalIndex]
                soalText = soal.text
                pilihanJawaban = listOf(soal.answerA, soal.answerB, soal.answerC, soal.answerD)

                // Update UI dengan soal dan jawaban
                updateUI(soalText, pilihanJawaban)

                // Bacakan soal dan jawaban
                bacakanSoalDanJawaban()
            }
        }
    }

    private fun updateUI(soal: String, jawaban: List<String>) {
        binding.tvSoal.text = soal
        binding.tvAnswerA.text = jawaban[0]
        binding.tvAnswerB.text = jawaban[1]
        binding.tvAnswerC.text = jawaban[2]
        binding.tvAnswerD.text = jawaban[3]
    }

    private fun bacakanSoalDanJawaban() {
        val textToSpeak = "Soal: $soalText. Pilihan jawabannya adalah. A: ${pilihanJawaban[0]}. B: ${pilihanJawaban[1]}. C: ${pilihanJawaban[2]}. D: ${pilihanJawaban[3]}."
        tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun setupButtonListeners() {
        // Listener untuk tombol jawaban A
        binding.cvAnswerA.setOnClickListener {
            handleButtonPress("A", binding.tvAnswerA.text.toString(), binding.cvAnswerA)
        }

        // Listener untuk tombol jawaban B
        binding.cvAnswerB.setOnClickListener {
            handleButtonPress("B", binding.tvAnswerB.text.toString(), binding.cvAnswerB)
        }

        // Listener untuk tombol jawaban C
        binding.cvAnswerC.setOnClickListener {
            handleButtonPress("C", binding.tvAnswerC.text.toString(), binding.cvAnswerC)
        }

        // Listener untuk tombol jawaban D
        binding.cvAnswerD.setOnClickListener {
            handleButtonPress("D", binding.tvAnswerD.text.toString(), binding.cvAnswerD)
        }
    }

    private fun handleButtonPress(pilihan: String, jawaban: String, tombolDipilih: View) {
        // Cek apakah ada tombol yang sudah terkunci sebelumnya
        if (tombolTerkunci != null && tombolTerkunci != tombolDipilih) {
            // Lepas kunci dari tombol sebelumnya
            tombolTerkunci?.isClickable = true
        }

        // Kunci tombol yang baru dipilih
        tombolDipilih.isClickable = false
        tombolTerkunci = tombolDipilih // Simpan referensi tombol yang terkunci

        // Simpan pilihan jawaban terkunci
        jawabanTerkunci = pilihan

        // Bacakan pilihan yang dipilih beserta jawabannya
        val textToSpeak = "Anda memilih pilihan $pilihan, dengan jawaban: $jawaban."
        tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        return false
    }

    // Implementasi GestureListener dan DoubleTapListener
    override fun onDoubleTap(event: MotionEvent): Boolean {
        // Cek apakah ada soal berikutnya, jika ya, muat soal tersebut
        soalIndex++
        loadSoal() // Muat soal berikutnya
        return true
    }

    override fun onDown(event: MotionEvent): Boolean {
        return true
    }

    // Fungsi GestureDetector lainnya
    override fun onFling(
        e1: MotionEvent?, e2: MotionEvent,
        velocityX: Float, velocityY: Float
    ): Boolean {
        // Deteksi gesture dua jari ke bawah untuk bacakan soal ulang
        if (e1 != null && e2 != null) {
            val deltaY = e2.y - e1.y
            if (deltaY > 100 && e1.pointerCount == 2) {
                // Gesture dua jari ke bawah terdeteksi
                bacakanSoalDanJawaban() // Bacakan ulang soal
            }
        }
        return true
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }

    // Fungsi GestureDetector lainnya (diimplementasikan sesuai kebutuhan, namun tidak dipakai di sini)
    override fun onShowPress(e: MotionEvent) {}
    override fun onSingleTapUp(e: MotionEvent): Boolean = false
    override fun onScroll(e1: MotionEvent?, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean = false
    override fun onLongPress(e: MotionEvent) {}
    override fun onDoubleTapEvent(e: MotionEvent): Boolean = false
}
