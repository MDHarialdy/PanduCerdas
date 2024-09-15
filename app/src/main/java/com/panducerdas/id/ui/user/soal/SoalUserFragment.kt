package com.panducerdas.id.ui.user.soal

import android.app.DirectAction
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.panducerdas.id.R
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
    private var soalIndex = 0
    private var jumlahSoal = 0
    private var jawabanTerkunci: String? = null
    private var tombolTerkunci: View? = null
    val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
    val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

    private var isTtsReady = false // Flag untuk melacak apakah TTS sudah siap
    private var isSoalLoaded = false // Flag untuk melacak apakah soal sudah dimuat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSoalUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Speech Reconigzer
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "id-ID")

        speechRecognizer.setRecognitionListener(object: RecognitionListener{
            override fun onReadyForSpeech(p0: Bundle?) {
                //nothing
            }

            override fun onBeginningOfSpeech() {
                //nothing
            }

            override fun onRmsChanged(p0: Float) {
                //nothing
            }

            override fun onBufferReceived(p0: ByteArray?) {
                //nothing
            }

            override fun onEndOfSpeech() {
                //nothing
            }

            override fun onError(p0: Int) {
                //nothing
            }

            override fun onResults(bundle: Bundle?) {
                val matches = bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (matches != null && matches.isNotEmpty()) {
                    val command = matches[0].lowercase(Locale.getDefault())

                    // Cek apakah pengguna menyebutkan pilihan jawaban
                    when {
                        command.contains("Memilih a") -> handleButtonPress("A", binding.tvAnswerA.text.toString(), binding.buttonA)
                        command.contains("Memilih b") -> handleButtonPress("B", binding.tvAnswerB.text.toString(), binding.buttonB)
                        command.contains("Memilih c") -> handleButtonPress("C", binding.tvAnswerC.text.toString(), binding.buttonC)
                        command.contains("Memilih d") -> handleButtonPress("D", binding.tvAnswerD.text.toString(), binding.buttonD)
                        command.contains("soal berikutnya") -> {
                            if (jawabanTerkunci == null) {
                                // Jika belum ada jawaban yang dipilih, bacakan pesan
                                val pesan = "Pilih jawaban terlebih dahulu."
                                tts.speak(pesan, TextToSpeech.QUEUE_FLUSH, null, null)
                            } else {
                                // Jika sudah ada jawaban, lanjut ke soal berikutnya
                                if (soalIndex + 1 < jumlahSoal) {
                                    soalIndex++
                                    jawabanTerkunci = null // Reset jawaban terkunci untuk soal berikutnya
                                    loadSoal() // Muat soal berikutnya
                                } else {
                                    findNavController().navigate(R.id.action_soalUserFragment_to_scoreFragment)
                                }
                            }
                        }
                        else -> {
                            val pesan = "Perintah tidak dikenal. Ulangi perintah."
                            tts.speak(pesan, TextToSpeech.QUEUE_FLUSH, null, null)
                        }
                    }
                }
            }


            override fun onPartialResults(p0: Bundle?) {
                //nothing
            }

            override fun onEvent(p0: Int, p1: Bundle?) {
                //nothing
            }

        })

        startListening()

//        ____________________________________________________________________________________


        // Inisialisasi TextToSpeech
        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale("id", "ID") // Set TTS ke bahasa Indonesia
                isTtsReady = true // Tandai bahwa TTS sudah siap
                checkIfReadyToSpeak() // Cek apakah kedua proses sudah selesai
            }
        }

        // Inisialisasi GestureDetector untuk mendeteksi gestur
        gestureDetector = GestureDetectorCompat(requireContext(), this)
        gestureDetector.setOnDoubleTapListener(this)

        // Set listener untuk gesture pada lower_layout
        binding.lowerLayoutGesture.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
            if (event.pointerCount == 2) {
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> {
                        // Deteksi gerakan dua jari
                        val deltaY = event.getY(1) - event.getY(0) // Menghitung pergerakan vertikal
                        if (deltaY > 12) {
                            // Jika usap ke bawah lebih dari 100px, bacakan ulang soal
                            bacakanSoalDanJawaban()
                        }
                    }
                }
            }
            true
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
                jumlahSoal = soalList.size
                // Update UI dengan soal dan jawaban
                updateUI(soalIndex+1, soalText, pilihanJawaban)

                isSoalLoaded = true // Tandai bahwa soal sudah dimuat
                checkIfReadyToSpeak() // Cek apakah kedua proses sudah selesai
            }
        }
    }

    private fun checkIfReadyToSpeak() {
        if (isTtsReady && isSoalLoaded) {
            // Jika TTS dan soal sudah siap, bacakan soal
            bacakanSoalDanJawaban()
        }
    }

    private fun startListening() {
        speechRecognizer.startListening(speechRecognizerIntent)
    }

    private fun updateUI(soalId: Int, soal: String, jawaban: List<String>) {
        binding.tvSoal.text = ("Soal ${soalId.toString()}/$jumlahSoal")
        binding.tvDescSoal.text = soal
        binding.tvAnswerA.text = jawaban[0]
        binding.tvAnswerB.text = jawaban[1]
        binding.tvAnswerC.text = jawaban[2]
        binding.tvAnswerD.text = jawaban[3]
    }

    private fun bacakanSoalDanJawaban() {
        if (soalText.isNotEmpty() && pilihanJawaban.isNotEmpty()) {
            val textToSpeak = "Soal ${soalIndex + 1}: $soalText. A: ${pilihanJawaban[0]}. B: ${pilihanJawaban[1]}. C: ${pilihanJawaban[2]}. D: ${pilihanJawaban[3]}."
            tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun setupButtonListeners() {
        // Listener untuk tombol jawaban A
        binding.buttonA.setOnClickListener {
            handleButtonPress("A", binding.tvAnswerA.text.toString(), binding.buttonA)
        }

        // Listener untuk tombol jawaban B
        binding.buttonB.setOnClickListener {
            handleButtonPress("B", binding.tvAnswerB.text.toString(), binding.buttonB)
        }

        // Listener untuk tombol jawaban C
        binding.buttonC.setOnClickListener {
            handleButtonPress("C", binding.tvAnswerC.text.toString(), binding.buttonC)
        }

        // Listener untuk tombol jawaban D
        binding.buttonD.setOnClickListener {
            handleButtonPress("D", binding.tvAnswerD.text.toString(), binding.buttonD)
        }
    }

    private fun handleButtonPress(pilihan: String, jawaban: String, tombolDipilih: View) {
        // Cek apakah ada tombol yang sudah terkunci sebelumnya
        if (tombolTerkunci != null && tombolTerkunci != tombolDipilih) {
            // Lepas kunci dari tombol sebelumnya
            tombolTerkunci?.isClickable = true
            (tombolTerkunci as AppCompatButton).setBackgroundResource(R.drawable.shape_button_fragment_soal_unselected)
        }

        // Kunci tombol yang baru dipilih
        tombolDipilih.isClickable = false
        tombolTerkunci = tombolDipilih // Simpan referensi tombol yang terkunci

        (tombolDipilih as AppCompatButton).setBackgroundResource(R.drawable.shape_button_fragment_soal_selected)
        // Simpan pilihan jawaban terkunci
        jawabanTerkunci = pilihan

        // Bacakan pilihan yang dipilih beserta jawabannya
        val textToSpeak = "memilih $pilihan, $jawaban."
        tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        return false
    }

    // Implementasi GestureListener dan DoubleTapListener
    override fun onDoubleTap(event: MotionEvent): Boolean {
        // Cek apakah sudah ada jawaban yang terkunci
        if (jawabanTerkunci == null) {
            // Jika belum ada jawaban yang dipilih, bacakan pesan
            val pesan = "Pilih jawaban terlebih dahulu."
            tts.speak(pesan, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            // Jika sudah ada jawaban, lanjut ke soal berikutnya atau ke ScoreFragment jika semua soal sudah terjawab
            if (soalIndex + 1 < jumlahSoal) {
                // Masih ada soal yang belum dijawab
                soalIndex++
                jawabanTerkunci = null // Reset jawaban terkunci untuk soal berikutnya
                loadSoal() // Muat soal berikutnya
            } else {

                findNavController().navigate(R.id.action_soalUserFragment_to_scoreFragment)
            }
        }
        return true
    }

    override fun onDown(event: MotionEvent): Boolean {
        return true
    }

    override fun onFling(
        e1: MotionEvent?, e2: MotionEvent,
        velocityX: Float, velocityY: Float
    ): Boolean {
        return false
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
