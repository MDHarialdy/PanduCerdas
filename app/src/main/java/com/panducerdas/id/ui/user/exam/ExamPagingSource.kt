package com.panducerdas.id.ui.user.exam

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.panducerdas.id.data.DummyDataExam
import com.panducerdas.id.data.database.ExamEntity

class ExamPagingSource : PagingSource<Int, ExamEntity>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ExamEntity> {
        val page: Int = params.key ?: 1

        val pageSize = params.loadSize
        val start = (page - 1) * pageSize
        val end = minOf(start + pageSize, DummyDataExam.getDummyData().size)

        val data = DummyDataExam.getDummyData().subList(start, end)

        return LoadResult.Page(
            data = data,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (end == DummyDataExam.getDummyData().size) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ExamEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
