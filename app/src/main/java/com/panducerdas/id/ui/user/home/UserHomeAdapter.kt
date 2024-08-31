package com.panducerdas.id.ui.user.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.panducerdas.id.data.database.UserExamEntity
import com.panducerdas.id.databinding.ItemUserBinding

class UserHomeAdapter : PagingDataAdapter<UserExamEntity, UserHomeAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserExamEntity) {
            binding.tvExamCategoryName.text = item.UserExamCategory
            binding.tvExamName.text = item.UserExamName
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserExamEntity>() {
            override fun areItemsTheSame(oldItem: UserExamEntity, newItem: UserExamEntity): Boolean {
                return oldItem.ExamId == newItem.ExamId
            }

            override fun areContentsTheSame(oldItem: UserExamEntity, newItem: UserExamEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}
