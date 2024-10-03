package com.panducerdas.id.ui.user.ai

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Vibrator
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.panducerdas.id.databinding.FragmentAiBinding
import java.util.Locale
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import com.panducerdas.id.BuildConfig
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AiFragment : Fragment(), GestureDetector.OnDoubleTapListener {

    private lateinit var binding: FragmentAiBinding
    private lateinit var tts: TextToSpeech
    private var isTtsReady = false
    private var sendDataJob: Job? = null
    private var currentWordIndex = 0
    private var responseWords: List<String> = listOf()
    private lateinit var gestureDetector: GestureDetector
    private lateinit var vibrator: Vibrator
    private val REQUEST_MICROPHONE_PERMISSION = 200
    private lateinit var speechRecognizer: SpeechRecognizer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        checkPermissionsAndInitialize()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        tts.stop()
        // Stop SpeechRecognizer
        if (::speechRecognizer.isInitialized) {
            speechRecognizer.stopListening()  // Stop listening if it's active
        }

        sendDataJob?.cancel()
    }

    private fun checkPermissionsAndInitialize() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.RECORD_AUDIO),
                REQUEST_MICROPHONE_PERMISSION)
        } else {
            initialize()
        }
    }


    //inisialisasi tts dan speech recognize
    private fun initialize() {
        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale("id", "ID")
                isTtsReady = true
                speak("Hai saya sang pandu, ketuk dua kali untuk menanyakan apa saja kepada saya")
            }
        }

        vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        gestureDetector = GestureDetector(requireContext(), GestureDetector.SimpleOnGestureListener())
        gestureDetector.setOnDoubleTapListener(this)

        //speech
        initializeSpeechRecognizer()

        // Set touch listener on layout to detect double taps
        binding.touchLayout.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    private fun speak(text: String) {
        if (isTtsReady) {
            responseWords = text.split(" ")
            currentWordIndex = 0
            updateDisplayedText()

            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun updateDisplayedText() {
        if (currentWordIndex < responseWords.size) {
            val sublist = responseWords.subList(
                currentWordIndex,
                minOf(currentWordIndex + 6, responseWords.size)
            )
            binding.tvResponseAi.text = sublist.joinToString(" ")
            currentWordIndex++

            viewLifecycleOwner.lifecycleScope.launch {
                kotlinx.coroutines.delay(400)
                updateDisplayedText()
            }
        }
    }

    fun chatAI(input: String) {
        if (!isConnectedToInternet()) {
            showNoInternetDialog()
            return
        }

        val sexuallySafety = SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.NONE)

        val api = BuildConfig.GEMINI_API_KEY.toString()
        val model = GenerativeModel(
            "gemini-1.5-flash",
            api,
            generationConfig = generationConfig {
                temperature = 1f
                topK = 64
                topP = 0.95f
                maxOutputTokens = 1000
                responseMimeType = "text/plain"
            },
            systemInstruction = content {
                text("your name is sang pandu mostly called pandu, your create by team pandu cerdas, Only make in paragraph, short and fast, " +
                        "maximum 100 words, make response in Bahasa Indonesia. make response like talk to children age 15 years old")
            },

            safetySettings = listOf(sexuallySafety)
        )

        sendDataJob = viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = model.generateContent(input)
                val responseText = response.text
                binding.tvResponseAi.text = responseText
                if (responseText != null) {
                    speak(responseText)
                }
            } catch (e: ResponseStoppedException) {
                // Tampilkan pesan error kepada pengguna
                val responseError = "Maaf, respons dihentikan karena alasan keamanan. Silakan coba pertanyaan lain."
                binding.tvResponseAi.text = responseError
                speak(responseError)
            } catch (e: Exception) {
                // Tangani exception lain
                binding.tvResponseAi.text = "Terjadi kesalahan. Coba lagi nanti."
                e.printStackTrace()
            }
        }
    }

    private fun isConnectedToInternet(): Boolean {
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun showNoInternetDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("No Internet")
            .setMessage("Internet connection is not available. Please check your connection and try again.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_MICROPHONE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initialize()
            } else {
                // Handle the case when permission is not granted
                AlertDialog.Builder(requireContext())
                    .setTitle("Permission Required")
                    .setMessage("Microphone permission is required to use voice input.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    private fun initializeSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                binding.tvHiUser.text = "Mendengarkan..."
            }

            override fun onBeginningOfSpeech() {
                // Do nothing
            }

            override fun onRmsChanged(rmsdB: Float) {
                // Do nothing
            }

            override fun onBufferReceived(buffer: ByteArray?) {
                // Do nothing
            }

            override fun onEndOfSpeech() {
                binding.tvHiUser.text = "Sedang memproses..."
            }

            override fun onError(error: Int) {
                binding.tvHiUser.text = "Error saat mengenali suara."
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (matches != null && matches.isNotEmpty()) {
                    val userInput = matches[0]
                    binding.tvHiUser.text = userInput
                    chatAI(userInput)  // Process the speech input using AI
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {
                // Do nothing
            }

            override fun onEvent(eventType: Int, params: Bundle?) {
                // Do nothing
            }
        })
    }

    private fun promptSpeechInput() {
        if (!isConnectedToInternet()) {
            showNoInternetDialog()
            return
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "id-ID")
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Silakan bicara...")

        speechRecognizer.startListening(intent)
    }


    override fun onDoubleTap(e: MotionEvent): Boolean {
        vibrator.vibrate(100)
        tts.stop()
        promptSpeechInput()
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent): Boolean {
        return false
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        return false
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        if (::speechRecognizer.isInitialized) {
            speechRecognizer.destroy()
        }
        super.onDestroy()
    }
}
