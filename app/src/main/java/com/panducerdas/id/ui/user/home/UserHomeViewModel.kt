package com.panducerdas.id.ui.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.panducerdas.id.data.database.ExamEntity
import com.panducerdas.id.data.database.UserExamEntity
import com.panducerdas.id.ui.user.exam.ExamPagingSource
import kotlinx.coroutines.flow.Flow

class UserHomeViewModel : ViewModel() {

    val exams: Flow<PagingData<UserExamEntity>> = Pager(
        config = PagingConfig(
            pageSize = 4,    // Menentukan ukuran halaman (jumlah item per halaman)
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UserPagingSource() }
    ).flow.cachedIn(viewModelScope)
}