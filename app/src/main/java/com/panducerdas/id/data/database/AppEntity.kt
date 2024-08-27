package com.panducerdas.id.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class ExamEntity(
    @PrimaryKey
    val ExamId: Int,
    val ExamCategory: String,
    val ExamName: String,
    val ExamDeadline: LocalDate
)

@Entity
data class AdminEntity(
    @PrimaryKey
    val AdminId: Int,
    val AdminName: String,
    val AdminEmail: String,
    val AdminPassword: String
)


