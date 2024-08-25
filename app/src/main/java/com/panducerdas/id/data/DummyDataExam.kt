package com.panducerdas.id.data

import java.time.LocalDate

data class ExamProperties (
    val kategoriPelaran: String,
    val namaPelajaran: String,
    val tanggalDeadLine: LocalDate
)

object DummyDataExam {

    fun getDummyData(): List<ExamProperties> {
        return listOf(
            ExamProperties("PPKN", "Ulangan PPKN Semester 1", LocalDate.of(2024, 7, 4)),
            ExamProperties("Biologi", "Ulangan Biologi Semester 1", LocalDate.of(2024, 7, 5)),
            ExamProperties("Matematika", "Latihan Matematika ke 2", LocalDate.of(2024, 7, 6)),
            ExamProperties("Fisika", "Latihan Fisika Bab 3", LocalDate.of(2024, 7, 7)),
            ExamProperties("Kimia", "Praktikum Kimia Asam Basa", LocalDate.of(2024, 7, 8)),
            ExamProperties("Sejarah", "Tugas Sejarah Perang Dunia II", LocalDate.of(2024, 7, 9)),
            ExamProperties("Geografi", "Latihan Geografi Wilayah dan Tata Ruang", LocalDate.of(2024, 7, 10)),
            ExamProperties("Sosiologi", "Presentasi Sosiologi Sosial", LocalDate.of(2024, 7, 11)),
            ExamProperties("Ekonomi", "Tugas Ekonomi Mikro", LocalDate.of(2024, 7, 12)),
            ExamProperties("Bahasa Indonesia", "Puisi Bahasa Indonesia", LocalDate.of(2024, 7, 13)),
            ExamProperties("Bahasa Inggris", "Essay Bahasa Inggris", LocalDate.of(2024, 7, 14)),
            ExamProperties("Teknologi Informasi", "Proyek Teknologi Informasi Jaringan", LocalDate.of(2024, 7, 15))
        )
    }
}