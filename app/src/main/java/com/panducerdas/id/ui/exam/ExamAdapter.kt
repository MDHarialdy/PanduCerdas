import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.panducerdas.id.data.ExamProperties
import com.panducerdas.id.databinding.ItemExamBinding

class ExamAdapter : PagingDataAdapter<ExamProperties, ExamAdapter.ExamViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        val binding = ItemExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class ExamViewHolder(private val binding: ItemExamBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExamProperties) {
            binding.tvExamCategoryName.text = item.kategoriPelaran
            binding.tvExamName.text = item.namaPelajaran
            binding.tvExamDate.text = item.tanggalDeadLine.toString()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExamProperties>() {
            override fun areItemsTheSame(oldItem: ExamProperties, newItem: ExamProperties): Boolean {
                return oldItem.namaPelajaran == newItem.namaPelajaran
            }

            override fun areContentsTheSame(oldItem: ExamProperties, newItem: ExamProperties): Boolean {
                return oldItem == newItem
            }
        }
    }
}
