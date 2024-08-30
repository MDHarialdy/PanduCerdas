package com.panducerdas.id.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class ExamEntity(
    @PrimaryKey
    val ExamId: Int,
    val ExamCategory: String,
    val ExamName: String
)

@Entity
data class AdminEntity(
    @PrimaryKey
    val AdminEmail: String,
    val AdminName: String,
    val AdminPassword: String
)


