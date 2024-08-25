import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.panducerdas.id.data.ExamProperties
import com.panducerdas.id.ui.exam.ExamPagingSource
import kotlinx.coroutines.flow.Flow

class ExamViewModel : ViewModel() {

    val exams: Flow<PagingData<ExamProperties>> = Pager(
        config = PagingConfig(
            pageSize = 3,    // Menentukan ukuran halaman (jumlah item per halaman)
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ExamPagingSource() }
    ).flow.cachedIn(viewModelScope)
}
