package com.panducerdas.id.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExam(examProperties: ExamEntity)

    @Query("SELECT * from examentity WHERE ExamId = :examId")
    fun getExam(examId: Int): LiveData<List<ExamEntity>>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateExam(examProperties: ExamEntity)
}