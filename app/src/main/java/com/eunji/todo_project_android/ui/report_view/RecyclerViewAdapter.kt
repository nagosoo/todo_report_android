package com.eunji.todo_project_android.ui.report_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.eunji.todo_project_android.databinding.ItemTodoReportBinding
import com.eunji.todo_project_android.model.Todo
import com.eunji.todo_project_android.util.StampUtil

class RecyclerViewAdapter(
    private val stampClickListener: (Int) -> Unit,
    private val setOnTextChanged: (String?, Int) -> Unit,
) : RecyclerView.Adapter<RecyclerViewAdapter.TodoReportViewHolder>() {


    var todoList: MutableList<Todo> = MutableList(10) { Todo() }

    inner class TodoReportViewHolder(private val binding: ItemTodoReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.editText.apply {
                setText(todoList[adapterPosition].plan)
                addTextChangedListener {
                    setOnTextChanged(it.toString(), adapterPosition)
                }
            }
            binding.buttonStamp.apply {
                setImageDrawable(todoList[adapterPosition].stampIndex?.let {
                    if (it == 0) null else StampUtil.stampList[it]
                })
                setOnClickListener {
                    stampClickListener(adapterPosition)
                }
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