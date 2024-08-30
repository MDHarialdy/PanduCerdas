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
data class UserExamEntity(
    @PrimaryKey
    val UserExamId: Int,
    val UserExamCategory: String,
    val UserExamName: String
)

@Entity
data class AdminEntity(
    @PrimaryKey
    val AdminEmail: String,
    val AdminName: String,
    val AdminPassword: String
)

@Entity
data class UserEntity(
    @PrimaryKey
    val UserEmail: String,
    val UserName: String,
    val UserPassword: String
)


