package com.panducerdas.id.ui.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.UserExamEntity
import kotlinx.coroutines.flow.Flow

class UserHomeViewModel(val appRepository: AppRepository) : ViewModel() {

    val exams: Flow<PagingData<UserExamEntity>> = Pager(
        config = PagingConfig(
            pageSize = 4,    // Menentukan ukuran halaman (jumlah item per halaman)
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UserPagingSource() }
    ).flow.cachedIn(viewModelScope)
}