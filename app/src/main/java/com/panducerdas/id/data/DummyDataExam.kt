package com.panducerdas.id.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.panducerdas.id.data.database.AdminExamEntity
import com.panducerdas.id.data.database.UserExamEntity

object DummyDataExam {

    val examClassDummy = "r7Ede"

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDummyDataAdmin(): List<AdminExamEntity> {
        return listOf(
            AdminExamEntity(null,examClassDummy,"PPKN", "Ulangan PPKN Semester 1"),
            AdminExamEntity(null,examClassDummy,"Biologi", "Ulangan Biologi Semester 1"),
            AdminExamEntity(null,examClassDummy,"Matematika", "Latihan Matematika ke 2"),
            AdminExamEntity(null,examClassDummy,"Fisika", "Latihan Fisika Bab 3"),
            AdminExamEntity(null,examClassDummy,"Kimia", "Praktikum Kimia Asam Basa"),
            AdminExamEntity(null,examClassDummy,"Sejarah", "Tugas Sejarah Perang Dunia II"),
            AdminExamEntity(null,examClassDummy,"Geografi", "Latihan Geografi Wilayah dan Tata Ruang"),
            AdminExamEntity(null,examClassDummy,"Sosiologi", "Presentasi Sosiologi Sosial"),
            AdminExamEntity(null,examClassDummy,"Ekonomi", "Tugas Ekonomi Mikro"),
            AdminExamEntity(null,examClassDummy,"Bahasa Indonesia", "Puisi Bahasa Indonesia"),
            AdminExamEntity(null,examClassDummy,"Bahasa Inggris", "Essay Bahasa Inggris"),
            AdminExamEntity(null,examClassDummy,"Teknologi Informasi", "Proyek Teknologi Informasi Jaringan")
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDummyDataUser(): List<UserExamEntity> {
        return listOf(
            UserExamEntity(null, examClassDummy, "PPKN", "Ulangan PPKN Semester 1"),
            UserExamEntity(null, examClassDummy, "Biologi", "Ulangan Biologi Semester 1"),
            UserExamEntity(null, examClassDummy, "Matematika", "Latihan Matematika ke 2"),
            UserExamEntity(null, examClassDummy, "Fisika", "Latihan Fisika Bab 3"),
            UserExamEntity(null, examClassDummy, "Kimia", "Praktikum Kimia Asam Basa"),
            UserExamEntity(null, examClassDummy, "Sejarah", "Tugas Sejarah Perang Dunia II"),
            UserExamEntity(null, examClassDummy, "Geografi", "Latihan Geografi Wilayah dan Tata Ruang"),
            UserExamEntity(null, examClassDummy, "Sosiologi", "Presentasi Sosiologi Sosial"),
            UserExamEntity(null, examClassDummy, "Ekonomi", "Tugas Ekonomi Mikro"),
            UserExamEntity(null, examClassDummy, "Bahasa Indonesia", "Puisi Bahasa Indonesia"),
            UserExamEntity(null, examClassDummy, "Bahasa Inggris", "Essay Bahasa Inggris"),
            UserExamEntity(null, examClassDummy, "Teknologi Informasi", "Proyek Teknologi Informasi Jaringan")
        )
    }

}