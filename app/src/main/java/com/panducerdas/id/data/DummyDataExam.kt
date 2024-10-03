package com.panducerdas.id.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.panducerdas.id.data.database.AdminExamEntity
import com.panducerdas.id.data.database.UserExamEntity
import java.time.LocalDate

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
    fun getDummyDataUser(): ArrayList<UserExamEntity> {
        return arrayListOf(
            UserExamEntity(null, examClassDummy, "PPKN", "Ulangan PPKN Semester 1", "Ulangan ini mencakup materi Pendidikan Pancasila dan Kewarganegaraan (PPKN) yang telah diajarkan selama semester 1. Fokus ulangan ini adalah pemahaman tentang konsep-konsep dasar kewarganegaraan dan Pancasila.", LocalDate.of(2024, 9, 20)),
            UserExamEntity(null, examClassDummy, "Biologi", "Ulangan Biologi Semester 1", "Ulangan ini menilai pemahaman siswa tentang materi Biologi yang dipelajari selama semester 1. Topik yang diuji termasuk sel, ekosistem, dan genetika.", LocalDate.of(2024, 9, 22)),
            UserExamEntity(null, examClassDummy, "Matematika", "Latihan Matematika ke 2", "Latihan ini berfokus pada penerapan konsep aljabar dan geometri dasar. Latihan ini diharapkan membantu siswa meningkatkan keterampilan penyelesaian soal.", LocalDate.of(2024, 9, 25)),
            UserExamEntity(null, examClassDummy, "Fisika", "Latihan Fisika Bab 3", "Latihan ini menitikberatkan pada pemahaman siswa tentang konsep fisika terkait dengan hukum gerak Newton dan energi kinetik.", LocalDate.of(2024, 9, 27)),
            UserExamEntity(null, examClassDummy, "Kimia", "Praktikum Kimia Asam Basa", "Praktikum ini melibatkan eksperimen mengenai sifat-sifat asam dan basa, termasuk pengujian pH dan reaksi netralisasi.", LocalDate.of(2024, 9, 28)),
            UserExamEntity(null, examClassDummy, "Sejarah", "Tugas Sejarah Perang Dunia II", "Tugas ini memerlukan analisis mendalam tentang penyebab dan dampak Perang Dunia II, serta kontribusi negara-negara besar dalam perang tersebut.", LocalDate.of(2024, 10, 1)),
            UserExamEntity(null, examClassDummy, "Geografi", "Latihan Geografi Wilayah dan Tata Ruang", "Latihan ini mencakup studi tentang pembagian wilayah dan perencanaan tata ruang, dengan fokus pada pengelolaan sumber daya alam dan lingkungan.", LocalDate.of(2024, 10, 3)),
            UserExamEntity(null, examClassDummy, "Sosiologi", "Presentasi Sosiologi Sosial", "Siswa diminta untuk membuat presentasi tentang fenomena sosial, dengan penekanan pada struktur sosial, mobilitas sosial, dan perubahan sosial.", LocalDate.of(2024, 10, 5)),
            UserExamEntity(null, examClassDummy, "Ekonomi", "Tugas Ekonomi Mikro", "Tugas ini meminta siswa untuk menganalisis konsep-konsep ekonomi mikro, seperti penawaran, permintaan, dan harga pasar.", LocalDate.of(2024, 10, 7)),
            UserExamEntity(null, examClassDummy, "Bahasa Indonesia", "Puisi Bahasa Indonesia", "Tugas ini memerlukan penulisan puisi dalam Bahasa Indonesia yang menggambarkan pemahaman siswa tentang teknik sastra seperti metafora, personifikasi, dan ironi.", LocalDate.of(2024, 10, 10)),
            UserExamEntity(null, examClassDummy, "Bahasa Inggris", "Essay Bahasa Inggris", "Siswa diminta untuk menulis esai dalam Bahasa Inggris mengenai topik pilihan, yang mencakup struktur esai yang baik dan penggunaan tata bahasa yang tepat.", LocalDate.of(2024, 10, 12)),
            UserExamEntity(null, examClassDummy, "Teknologi Informasi", "Proyek Teknologi Informasi Jaringan", "Proyek ini melibatkan perancangan jaringan komputer sederhana, di mana siswa harus memahami konsep-konsep dasar jaringan dan implementasi praktis.", LocalDate.of(2024, 10, 15))
        )
    }

}