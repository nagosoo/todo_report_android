package com.eunji.todo_project_android.ui.report_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eunji.todo_project_android.databinding.ActivityReportBinding

class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding
    private val adapter by lazy { RecyclerViewAdapter(List(10){"hi"}) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerview.adapter = adapter
    }

}