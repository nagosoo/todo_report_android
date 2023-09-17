package com.eunji.todo_project_android.ui.report_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eunji.todo_project_android.databinding.ItemTodoReportBinding
import com.eunji.todo_project_android.model.Todo

class RecyclerViewAdapter(
    private val todoList: List<Todo>,
    private val stampClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<RecyclerViewAdapter.TodoReportViewHolder>() {

    inner class TodoReportViewHolder(private val binding: ItemTodoReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.editText.setText(todoList[adapterPosition].plan)
            binding.buttonStamp.setImageDrawable(todoList[adapterPosition].stamp)
            binding.buttonStamp.setOnClickListener {
                stampClickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoReportBinding.inflate(inflater, parent, false)
        return TodoReportViewHolder(binding)
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: TodoReportViewHolder, position: Int) = holder.bind()
}