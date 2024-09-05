package com.panducerdas.id.ui.user.home

import com.panducerdas.id.ui.user.soal.SoalUserActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.panducerdas.id.data.database.UserExamEntity
import com.panducerdas.id.databinding.ItemUserBinding

class UserHomeAdapter(val context: Context) : PagingDataAdapter<UserExamEntity, UserHomeAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class UserViewHolder(val context: Context, private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserExamEntity) {
            binding.tvExamCategoryName.text = item.UserExamCategory
            binding.tvExamName.text = item.UserExamName
            binding.btnExamItemOpen.setOnClickListener{
                val intent = Intent(context, SoalUserActivity::class.java)
                context.startActivity(intent)
            }
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
