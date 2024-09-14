package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {
    @Insert
    suspend fun insertNumber(number: NumberEntity)

    @Query("SELECT * FROM numbers ORDER BY id DESC LIMIT 1")
    fun getLastNumber(): Flow<NumberEntity?>
}
