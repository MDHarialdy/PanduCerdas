package com.panducerdas.id.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class AdminExamEntity(
    @PrimaryKey(autoGenerate = true)
    val ExamId: Int? = null,
    val ExamClass: String,
    val ExamCategory: String,
    val ExamName: String,
)


@Entity
data class UserExamEntity(
    @PrimaryKey(autoGenerate = true)
    val ExamId: Int? = null,
    val ExamClass: String,
    val UserExamCategory: String,
    val UserExamName: String
)

@Entity
data class ClassEntity(
    @PrimaryKey
    val ClassId: Int,
    val ClassName: Int,
    val ClassKategori: String,
    val ClassCode: String,
)

@Entity
data class SoalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val answerA: String,
    val answerB: String,
    val answerC: String,
    val answerD: String
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


