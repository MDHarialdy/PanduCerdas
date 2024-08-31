package com.panducerdas.id.ui.user.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.panducerdas.id.data.DummyDataExam
import com.panducerdas.id.data.database.UserExamEntity

class UserPagingSource : PagingSource<Int, UserExamEntity>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserExamEntity> {
        val page: Int = params.key ?: 1

        val pageSize = params.loadSize
        val start = (page - 1) * pageSize
        val end = minOf(start + pageSize, DummyDataExam.getDummyDataUser().size)

        val data = DummyDataExam.getDummyDataUser().subList(start, end)

        return LoadResult.Page(
            data = data,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (end == DummyDataExam.getDummyDataUser().size) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, UserExamEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}