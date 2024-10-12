package com.panducerdas.id.util

import ai.picovoice.porcupine.PorcupineManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.panducerdas.id.databinding.ActivityUserBinding
import java.io.File
import java.util.Locale

lateinit var porcupineManager: PorcupineManager
lateinit var tts: TextToSpeech
lateinit var binding: ActivityUserBinding

private var isListening = false



fun initPorcupine(context: Context) {

   binding = ActivityUserBinding.inflate(LayoutInflater.from(context))
    //init kebutuhan
    initializeSpeechRecognizer(context)
    tts = TextToSpeech(context){}

    val assetManager = context.assets
    val keywordPath = File(context.filesDir, "hai_pandu.ppn")

    assetManager.open("hai_pandu.ppn").use { inputStream ->
        keywordPath.outputStream().use { output ->
            inputStream.copyTo(output)
        }
    }

    porcupineManager = PorcupineManager.Builder()
        .setAccessKey("TAt3hEt5sgrkh0eTIZy3o1EmpVU28veJufR0pRH2yCMEVZ97u8zlVw==")
        .setKeywordPath(keywordPath.absolutePath)
        .setSensitivity(0.7f)  // Atur sensitivitas
        .setErrorCallback { error ->
            Log.e("Porcupine", "Error: ${error.message}")
        }.build(context) { keywordIndex ->
            if (keywordIndex >= 0) {
                binding.imgAiAnimation.visibility = View.VISIBLE
                respondToKeyword(context)
            }
        }

    // Mulai Porcupine untuk mendengarkan kata kunci
    porcupineManager.start()
}

// Fungsi untuk menghentikan pendeteksian sementara (onPause)
fun pausePorcupine() {
    try {
        if (::porcupineManager.isInitialized) {
            porcupineManager.stop()
            Log.d("Porcupine", "Porcupine paused.")
        }
    } catch (e: Exception) {
        Log.e("Porcupine", "Error stopping Porcupine: ${e.message}")
    }
}

// Fungsi untuk membersihkan resource saat aplikasi dihentikan (onDestroy)
fun destroyPorcupine() {
    try {
        if (::porcupineManager.isInitialized) {
            porcupineManager.delete()
            Log.d("Porcupine", "Porcupine destroyed.")
        }
    } catch (e: Exception) {
        Log.e("Porcupine", "Error deleting Porcupine: ${e.message}")
    }
}

// Fungsi untuk merespon saat kata kunci 'Pandu' terdeteksi
fun respondToKeyword(context: Context) {
    Log.d("Porcupine", "Kata kunci 'Pandu' terdeteksi!")
    val hai = "hai, silahkan bicara"
    initTTS(context, hai)
    promptSpeechInput()
}


//----------------------------------------------------------------//
//tts
var isTtsReady = false

fun initTTS(context: Context, text: String) {
    tts = TextToSpeech(context) { status ->
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale("id", "ID")
            isTtsReady = true
            checkIfReadyToSpeak(text)
        }
    }
}

fun checkIfReadyToSpeak(text: String) {
    if (isTtsReady) {
        bicara(text)
    }
}

fun bicara(text: String) {
    if (text.isNotEmpty()) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}

//-------------------------------------------------------------------//
//Speech Recognizer

lateinit var speechRecognizer: SpeechRecognizer

fun initializeSpeechRecognizer(context: Context) {
    speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    speechRecognizer.setRecognitionListener(object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) {
            isListening = true
            Log.d("SpeechRecognizer", "Ready for speech.")
            binding.imgAiAnimation.visibility = View.VISIBLE
        }

        override fun onBeginningOfSpeech() {
            Log.d("SpeechRecognizer", "User started speaking.")
        }

        override fun onRmsChanged(rmsdB: Float) {
            // Do nothing
        }

        override fun onBufferReceived(buffer: ByteArray?) {
            // Do nothing
        }

        override fun onEndOfSpeech() {
            Log.d("SpeechRecognizer", "Speech ended.")
            binding.imgAiAnimation.visibility = View.GONE
            isListening = false
        }

        override fun onError(error: Int) {
            Log.e("SpeechRecognizer", "Error: $error")
            when (error) {
                SpeechRecognizer.ERROR_AUDIO -> Log.e("SpeechRecognizer", "Audio recording error")
                SpeechRecognizer.ERROR_CLIENT -> Log.e("SpeechRecognizer", "Client side error")
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> Log.e("SpeechRecognizer", "Insufficient permissions")
                SpeechRecognizer.ERROR_NETWORK -> Log.e("SpeechRecognizer", "Network error")
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> Log.e("SpeechRecognizer", "Network timeout")
                SpeechRecognizer.ERROR_NO_MATCH -> Log.e("SpeechRecognizer", "No match found")
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> Log.e("SpeechRecognizer", "Recognition service busy")
                // Add other cases as needed
                else -> Log.e("SpeechRecognizer", "Unknown error: $error")
            }
            isListening = false
            binding.imgAiAnimation.visibility = View.GONE
        }

        override fun onResults(results: Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (matches != null && matches.isNotEmpty()) {
                when(matches[0]) {
                    "Belajar" -> {Log.d("onResult", "Belajar Detected")}
                    "Ujian" -> {Log.d("onResult", "Ujian Detected")}
                    "Utama" -> {Log.d("onResult", "Utama Detected")}
                }
            }
            Log.d("onResult", matches.toString())
            isListening = false
            binding.imgAiAnimation.visibility = View.GONE
        }

        override fun onPartialResults(partialResults: Bundle?) {
            // Do nothing
        }

        override fun onEvent(eventType: Int, params: Bundle?) {
            // Do nothing
        }
    })
}

fun promptSpeechInput() {
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "id-ID")

    speechRecognizer.startListening(intent)
}

fun stopListening() {
    if (isListening) {
        speechRecognizer.stopListening()
        isListening = false
        Log.d("SpeechRecognizer", "Listening stopped due to timeout.")
    }
}