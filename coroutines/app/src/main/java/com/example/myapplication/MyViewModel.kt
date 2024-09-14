package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            // Simulate network request or long-running task
            delay(2000)  // Simulating a network call delay
            _data.value = "Data loaded successfully!"
            _isLoading.value = false
        }
    }
}
