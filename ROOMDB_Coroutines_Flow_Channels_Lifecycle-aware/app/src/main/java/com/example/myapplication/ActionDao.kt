package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionDao {
    @Insert
    suspend fun insertAction(action: ActionEntity)

    @Query("SELECT * FROM actions ORDER BY id DESC LIMIT 1")
    fun getLastAction(): Flow<ActionEntity?>
}
