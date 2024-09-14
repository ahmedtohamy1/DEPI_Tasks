package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    // Define a channel to send and receive user actions
    val channel = Channel<String>(Channel.UNLIMITED)

    // A flow that emits increasing numbers every second
    val numberFlow: Flow<Int> = flow {
        var number = 0
        while (true) {
            emit(number++)
            kotlinx.coroutines.delay(1000) // Emits a number every second
        }
    }

    // Send an action to the channel
    fun sendAction(action: String) {
        viewModelScope.launch {
            channel.send(action)
        }
    }

    // Cancel the channel
    fun cancelChannel() {
        channel.cancel()
    }
}
