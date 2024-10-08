package com.panducerdas.id.util

import ai.picovoice.porcupine.PorcupineManager
import android.content.Context
import android.util.Log
import java.io.File

lateinit var porcupineManager: PorcupineManager

fun initPorcupine(context: Context) {
    val assetManager = context.assets
    val keywordPath = File(context.filesDir, "hai_pandu.ppn")

    assetManager.open("hai_pandu.ppn").use { inputStream ->
        keywordPath.outputStream().use { output ->
            inputStream.copyTo(output)
        }
    }

    porcupineManager = PorcupineManager.Builder()
        .setAccessKey("TAt3hEt5sgrkh0eTIZy3o1EmpVU28veJufR0pRH2yCMEVZ97u8zlVw==")  // Masukkan akses kunci Picovoice kamu
        .setKeywordPath(keywordPath.absolutePath)
        .setSensitivity(0.9f)  // Atur sensitivitas
        .setErrorCallback { error ->
            Log.e("Porcupine", "Error: ${error.message}")
        }.build(context) { keywordIndex ->
            if (keywordIndex >= 0) {
                respondToKeyword()
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
fun respondToKeyword() {
    Log.d("Porcupine", "Kata kunci 'Pandu' terdeteksi!")
}
