package com.panducerdas.id.ui.admin.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.panducerdas.id.data.database.ExamEntity
import com.panducerdas.id.databinding.ItemExamBinding

class AdminHomeAdapter : PagingDataAdapter<ExamEntity, AdminHomeAdapter.ExamViewHolder>(DIFF_CALLBACK) {

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
        fun bind(item: ExamEntity) {
            binding.tvExamCategoryName.text = item.ExamCategory
            binding.tvExamName.text = item.ExamName
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExamEntity>() {
            override fun areItemsTheSame(oldItem: ExamEntity, newItem: ExamEntity): Boolean {
                return oldItem.ExamId == newItem.ExamId
            }

            override fun areContentsTheSame(oldItem: ExamEntity, newItem: ExamEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}