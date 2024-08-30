package com.panducerdas.id.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.panducerdas.id.data.database.ExamEntity
import java.time.LocalDate

object DummyDataExam {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDummyData(): List<ExamEntity> {
        return listOf(
            ExamEntity(1,"PPKN", "Ulangan PPKN Semester 1"),
            ExamEntity(1,"Biologi", "Ulangan Biologi Semester 1"),
            ExamEntity(1,"Matematika", "Latihan Matematika ke 2"),
            ExamEntity(1,"Fisika", "Latihan Fisika Bab 3"),
            ExamEntity(1,"Kimia", "Praktikum Kimia Asam Basa"),
            ExamEntity(1,"Sejarah", "Tugas Sejarah Perang Dunia II"),
            ExamEntity(1,"Geografi", "Latihan Geografi Wilayah dan Tata Ruang"),
            ExamEntity(1,"Sosiologi", "Presentasi Sosiologi Sosial"),
            ExamEntity(1,"Ekonomi", "Tugas Ekonomi Mikro"),
            ExamEntity(1,"Bahasa Indonesia", "Puisi Bahasa Indonesia"),
            ExamEntity(1,"Bahasa Inggris", "Essay Bahasa Inggris"),
            ExamEntity(1,"Teknologi Informasi", "Proyek Teknologi Informasi Jaringan")
        )
    }
}