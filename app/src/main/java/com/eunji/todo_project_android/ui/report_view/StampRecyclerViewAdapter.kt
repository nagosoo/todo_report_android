package com.eunji.todo_project_android.ui.report_view

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eunji.todo_project_android.databinding.ItemStampBinding

class StampRecyclerViewAdapter(
    private val stampList: List<Drawable>,
    private val stampClickListener: (Int) -> Unit,
) :
    RecyclerView.Adapter<StampRecyclerViewAdapter.StampViewHolder>() {

    inner class StampViewHolder(private val binding: ItemStampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.buttonStamp.setImageDrawable(stampList[adapterPosition])
            binding.buttonStamp.setOnClickListener {
                stampClickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StampViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStampBinding.inflate(inflater, parent, false)

        return StampViewHolder(binding)
    }

    override fun getItemCount(): Int = stampList.size

    override fun onBindViewHolder(holder: StampViewHolder, position: Int) = holder.bind()

}