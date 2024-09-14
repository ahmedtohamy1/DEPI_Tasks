package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val database = MyDatabase.getDatabase(application)
    private val numberDao = database.numberDao()
    private val actionDao = database.actionDao()

    val channel = Channel<String>(Channel.UNLIMITED)

    // Flow that emits increasing numbers and saves them to the database
    val numberFlow: Flow<Int> = flow {
        var currentNumber = 0
        numberDao.getLastNumber().collect { lastNumber ->
            currentNumber = (lastNumber?.number ?: 0) as Int
        }
        while (true) {
            emit(currentNumber++)
            numberDao.insertNumber(NumberEntity(number = currentNumber))
            kotlinx.coroutines.delay(1000) // Emit number every second
        }
    }

    fun sendAction(action: String) {
        viewModelScope.launch {
            channel.send(action)
            actionDao.insertAction(ActionEntity(action = action))
        }
    }

    override fun onCleared() {
        super.onCleared()
        channel.cancel()
    }
}
