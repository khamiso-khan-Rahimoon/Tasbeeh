package com.example.tasbeeh

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tasbeeh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var counter = 0
    private var currentTasbeeh:String? = null
    lateinit var counterrtxt : TextView
    private var isMuted = false
    private lateinit var clickSound: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()




        counterrtxt = findViewById(R.id.counterTXT)
        clickSound = MediaPlayer.create(this, R.raw.click)


        binding.soundImg.setOnClickListener {
            isMuted = !isMuted // Toggle mute state
            if (isMuted) {
                binding.soundImg.setColorFilter(Color.RED) // Change to red when muted
            } else {
                binding.soundImg.clearColorFilter() // Clear color when unmuted
            }
        }

        binding.allahHuAkbarBTN.setOnClickListener {
            if (!isMuted) playClickSound()
            if (currentTasbeeh == null || currentTasbeeh == "Allah Hu Akber") {
                currentTasbeeh = "Allah Hu Akber"
                if (counter < 34) {
                    counter++
                    counterrtxt.text = counter.toString()
                } else {
                    Toast.makeText(this, "Limit reached for Allah Hu Akber", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                showResetPrompt()
            }
        }

        binding.alhamdullelahBTN.setOnClickListener {
            if (!isMuted) playClickSound()
            if (currentTasbeeh == null || currentTasbeeh == "Alhamdulillah") {
                currentTasbeeh = "Alhamdulillah"
                if (counter < 33) {
                    counter++
                    counterrtxt.text = counter.toString()
                } else {
                    Toast.makeText(this, "Limit reached for Alhamdulillah", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                showResetPrompt()
            }
        }

        binding.subhanAllahBTN.setOnClickListener {
            if (!isMuted) playClickSound()
            if (currentTasbeeh == null || currentTasbeeh == "Subhanallah") {
                currentTasbeeh = "Subhanallah"
                if (counter < 33) {
                    counter++
                    counterrtxt.text = counter.toString()
                } else {
                    Toast.makeText(this, "Limit reached for Subhanallah", Toast.LENGTH_SHORT).show()
                }
            } else {
                showResetPrompt()
            }
        }

        binding.wazifsbtn.setOnClickListener {
            if (!isMuted) playClickSound()
            if (currentTasbeeh == null || currentTasbeeh == "Wazifa") {
                currentTasbeeh = "Wazifa"
                counter++ // Wazifa has no limit
                counterrtxt.text = counter.toString()
            } else {
                showResetPrompt()
            }
        }
        binding.Duabtn.setOnClickListener {
            if (!isMuted) playClickSound()
            if (currentTasbeeh == null || currentTasbeeh == "Dua") {
                currentTasbeeh = "Dua"
                counter++
                counterrtxt.text = counter.toString()
            } else {
                showResetPrompt()
            }
        }

        binding.resettransparentbtn.setOnClickListener {
            if (!isMuted) playClickSound()
            counter = 0
            currentTasbeeh = null
            counterrtxt.text = counter.toString()
        }


    }

    private fun playClickSound() {
        if (clickSound.isPlaying) {
            clickSound.stop()
            clickSound.prepare() // Reset MediaPlayer if already playing
        }
        clickSound.start()
    }

    fun showResetPrompt() {
        Toast.makeText(this, "Please reset before switching Tasbeeh", Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        clickSound.release()
    }
}