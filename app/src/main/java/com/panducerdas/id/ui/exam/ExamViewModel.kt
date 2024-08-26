import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.panducerdas.id.data.database.ExamEntity
import com.panducerdas.id.ui.exam.ExamPagingSource
import kotlinx.coroutines.flow.Flow

class ExamViewModel : ViewModel() {

    val exams: Flow<PagingData<ExamEntity>> = Pager(
        config = PagingConfig(
            pageSize = 4,    // Menentukan ukuran halaman (jumlah item per halaman)
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ExamPagingSource() }
    ).flow.cachedIn(viewModelScope)
}
