package com.eunji.todo_project_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.eunji.todo_project_android.databinding.ActivityMainBinding
import com.eunji.todo_project_android.ui.report_view.ReportActivity
import com.eunji.todo_project_android.util.DateUtil
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = DateUtil.getTodayDate(year, month, dayOfMonth)
            val intent = Intent(this, ReportActivity::class.java)
            intent.putExtra("date"  , date)
            startActivity(intent)
        }
    }

}