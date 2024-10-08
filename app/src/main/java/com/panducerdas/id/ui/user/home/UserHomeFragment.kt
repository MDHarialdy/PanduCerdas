package com.panducerdas.id.ui.user.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.panducerdas.id.data.DummyDataExam
import com.panducerdas.id.data.database.UserExamEntity
import com.panducerdas.id.databinding.FragmentHomeUserBinding
import java.util.Locale

class UserHomeFragment : Fragment() {

    private var _binding: FragmentHomeUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var itemUser: ArrayList<UserExamEntity>
    private lateinit var adapter: UserHomeAdapter
    private lateinit var vibrator: Vibrator
    private lateinit var tts: TextToSpeech

    private var isTtsReady = false // Flag untuk melacak apakah TTS sudah siap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setupTransformers()
        initTextToSpeech()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        _binding = null
    }

    private fun initTextToSpeech() {
        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale("id", "ID") // Set bahasa ke Indonesia
                isTtsReady = true // Tandai bahwa TTS sudah siap
            }
        }
    }

    private fun init() {
        viewPager2 = binding.userHomeViewpager
        handler = Handler(Looper.myLooper()!!)
        itemUser = DummyDataExam.getDummyDataUser() // Pastikan data terisi
        adapter = UserHomeAdapter(requireContext(), itemUser, viewPager2)
        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Set page change callback to limit one page per gesture
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val selectedItem = itemUser[position]
                val itemName = selectedItem.UserExamName

                if (isTtsReady) {
                    tts.speak(itemName, TextToSpeech.QUEUE_FLUSH, null, null)
                }
                vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibrator.vibrate(100)
                viewPager2.isUserInputEnabled = true
            }
        })
    }



    private fun setupTransformers() {
        val transformers = CompositePageTransformer()

        transformers.addTransformer { page, position ->
            // Skala untuk slide yang tidak dipilih
            val scaleFactor = 0.85f + (1 - kotlin.math.abs(position)) * 0.1f
            page.scaleY = scaleFactor

            // Transparansi untuk slide yang tidak dipilih
            val alphaValue = 0.4f + (1 - kotlin.math.abs(position)) * 0.6f
            page.alpha = alphaValue
        }

        viewPager2.setPageTransformer(transformers)
    }
}
