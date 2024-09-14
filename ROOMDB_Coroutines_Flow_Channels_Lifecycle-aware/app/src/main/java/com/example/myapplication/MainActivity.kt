package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Collect emitted numbers from Flow and display them
        lifecycleScope.launch {
            viewModel.numberFlow.collect { number ->
                binding.textView.text = "Current Number: $number"
            }
        }

        // Handle button click events using channels
        lifecycleScope.launch {
            viewModel.channel.consumeEach { action ->
                when (action) {
                    "increment" -> binding.textView.text = "Increment Button Clicked"
                    "decrement" -> binding.textView.text = "Decrement Button Clicked"
                }
            }
        }

        // Setup buttons to send actions to the channel
        binding.buttonIncrement.setOnClickListener {
            lifecycleScope.launch { viewModel.sendAction("increment") }
        }

        binding.buttonDecrement.setOnClickListener {
            lifecycleScope.launch { viewModel.sendAction("decrement") }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.channel.cancel() // Make sure to cancel the channel
    }
}
