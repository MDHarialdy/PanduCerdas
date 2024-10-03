package com.panducerdas.id.ui.user.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.panducerdas.id.data.database.UserExamEntity
import com.panducerdas.id.databinding.ItemUserBinding
import com.panducerdas.id.ui.user.soal.SoalUserActivity

class UserHomeAdapter(
    private val context: Context,
    private val itemList: ArrayList<UserExamEntity>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<UserHomeAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(itemList[position])

        // Handle auto-scrolling (loop) functionality
        if (position == itemList.size - 2) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // ViewHolder class
    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserExamEntity) {
            binding.tvUserExamCategoryName.text = item.UserExamCategory
            binding.tvUserExamName.text = item.UserExamName
            binding.tvUserExamDeadline.text = item.deadline.toString()
            binding.tvDescUserExam.text = item.Description

            binding.root.setOnClickListener {
                // Create an Intent to navigate to the target activity
                val intent = Intent(context, SoalUserActivity::class.java).apply {
                    // Pass any necessary data to the target activity
                    putExtra("examCategory", item.UserExamCategory)
                    putExtra("examName", item.UserExamName)
                    putExtra("examDeadline", item.deadline.toString())
                    putExtra("examDescription", item.Description)
                }
                context.startActivity(intent)
            }
        }
    }

    // Runnable for infinite scroll effect
    private val runnable = Runnable {
        itemList.addAll(itemList)  // Duplicate items to make the scrolling infinite
        notifyDataSetChanged()
    }

    fun cleanup() {
        viewPager2.removeCallbacks(runnable)
    }
}
