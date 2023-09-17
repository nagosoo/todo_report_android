package com.eunji.todo_project_android.ui.report_view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eunji.todo_project_android.R
import com.eunji.todo_project_android.databinding.ActivityReportBinding
import com.eunji.todo_project_android.model.Todo

class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding
    private val todoList = MutableList<Todo>(10) { Todo(plan = "hi") }
    private val adapter by lazy {
        RecyclerViewAdapter(todoList) {
            refreshRecyclerView(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        ContextCompat.getDrawable(this, R.drawable.divider_black_line)?.let { drawable ->
            val divider =
                DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            divider.setDrawable(drawable)
            binding.recyclerview.addItemDecoration(divider)
        }
        binding.recyclerview.adapter = adapter
    }

    private fun refreshRecyclerView(index: Int) {
        val bottomSheet = StampBottomSheetDialogFragment()
        bottomSheet.show(supportFragmentManager, null)

        supportFragmentManager
            .setFragmentResultListener("requestKey", this) { requestKey, bundle ->
                val result = bundle.getInt("bundleKey")


                val stampDrawableList = mutableListOf<Drawable>()
                val drawableIds = resources.obtainTypedArray(R.array.stamp_drawables)

                for (i in 0..46) {
                    drawableIds.getDrawable(i)?.let { drawable ->
                        stampDrawableList.add(drawable)
                    }
                }

                drawableIds.recycle()


                todoList[index].stamp = stampDrawableList[result]
                adapter.notifyDataSetChanged()
            }
    }
}