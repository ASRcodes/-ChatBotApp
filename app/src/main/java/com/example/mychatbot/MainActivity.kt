package com.example.mychatbot

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mychatbot.databinding.ActivityMainBinding
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.sendBTN.setOnClickListener {
           val prompt = binding.prompt.text.toString()
            val generativeModel = GenerativeModel(
                modelName = "gemini-pro",
                apiKey = AI_API
            )

//            Showing the response in response textView
//            Suspend function 'generateContent' should be called only from a coroutine or another suspend function  for this use runBlocking
            runBlocking {
                val response = generativeModel.generateContent(prompt)
                binding.response.text = response.text
            }
        binding.prompt.text.clear()
        }
    }
}