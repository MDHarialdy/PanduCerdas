package com.panducerdas.id.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.panducerdas.id.data.database.ExamEntity
import java.time.LocalDate

object DummyDataExam {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDummyData(): List<ExamEntity> {
        return listOf(
            ExamEntity(1,"PPKN", "Ulangan PPKN Semester 1", LocalDate.of(2024, 7, 4)),
            ExamEntity(1,"Biologi", "Ulangan Biologi Semester 1", LocalDate.of(2024, 7, 5)),
            ExamEntity(1,"Matematika", "Latihan Matematika ke 2", LocalDate.of(2024, 7, 6)),
            ExamEntity(1,"Fisika", "Latihan Fisika Bab 3", LocalDate.of(2024, 7, 7)),
            ExamEntity(1,"Kimia", "Praktikum Kimia Asam Basa", LocalDate.of(2024, 7, 8)),
            ExamEntity(1,"Sejarah", "Tugas Sejarah Perang Dunia II", LocalDate.of(2024, 7, 9)),
            ExamEntity(1,"Geografi", "Latihan Geografi Wilayah dan Tata Ruang", LocalDate.of(2024, 7, 10)),
            ExamEntity(1,"Sosiologi", "Presentasi Sosiologi Sosial", LocalDate.of(2024, 7, 11)),
            ExamEntity(1,"Ekonomi", "Tugas Ekonomi Mikro", LocalDate.of(2024, 7, 12)),
            ExamEntity(1,"Bahasa Indonesia", "Puisi Bahasa Indonesia", LocalDate.of(2024, 7, 13)),
            ExamEntity(1,"Bahasa Inggris", "Essay Bahasa Inggris", LocalDate.of(2024, 7, 14)),
            ExamEntity(1,"Teknologi Informasi", "Proyek Teknologi Informasi Jaringan", LocalDate.of(2024, 7, 15))
        )
    }
}