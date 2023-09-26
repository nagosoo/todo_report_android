package com.eunji.todo_project_android.ui.report_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eunji.todo_project_android.AppApplication
import com.eunji.todo_project_android.R
import com.eunji.todo_project_android.databinding.ActivityReportBinding
import com.eunji.todo_project_android.model.Rating
import com.eunji.todo_project_android.model.Todo
import com.eunji.todo_project_android.repository.TodoRepositoryImpl
import com.eunji.todo_project_android.room.TodoDatabase
import com.eunji.todo_project_android.util.DateUtil
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding
    private lateinit var adapter: RecyclerViewAdapter

    private val db = TodoDatabase.getInstance(AppApplication.applicationContext())
    private val repository = TodoRepositoryImpl(db)
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            TodoViewModelFactory(repository)
        )[ReportViewModel::class.java]
    }
    private val date by lazy {
        intent.getStringExtra("date") ?: DateUtil.todayDate
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRating()
        setRecyclerView()
        observeData()
        onClickListener()

        binding.layoutTop.tvDate.text = "작성일자: $date"
        binding.edittextRating.addTextChangedListener {
            viewModel.rating.rating = it.toString()
        }
    }

    private fun setRating() {
        lifecycleScope.launch {
            this@ReportActivity.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getRating(date).collectLatest {
                    var rating = Rating(date = date)
                    it?.let {
                        rating = it
                    }
                    viewModel.rating = rating
                    binding.edittextRating.setText(rating.rating)
                }
            }
        }
    }

    private fun onClickListener() {
        binding.layoutTop.buttonSubmit.setOnClickListener {
            viewModel.saveTodos()
            viewModel.saveRating()
            onBackPressed()
        }
    }

    private fun observeData() {
        viewModel.stamp.observe(this) { stamp ->
            viewModel.index.value?.let {
                adapter.todoList = viewModel.todoList.value!!
                adapter.notifyItemChanged(it)
            }
        }
    }

    private fun setRecyclerView() {
        adapter = RecyclerViewAdapter(::setOnStampViewClicked, ::setOnPlanViewClicked)
        binding.recyclerview.adapter = adapter
        setRecyclerViewDivider()
        var list = MutableList(10) { Todo(date = date) }
        this.lifecycleScope.launch {
            this@ReportActivity.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTodos(date).collectLatest {
                    if (it.isNotEmpty()) {
                        list = it.toMutableList()
                    }
                    viewModel.setTodos(list)
                    adapter.todoList = list
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setRecyclerViewDivider() {
        ContextCompat.getDrawable(this, R.drawable.divider_black_line)?.let { drawable ->
            val divider =
                DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            divider.setDrawable(drawable)
            binding.recyclerview.addItemDecoration(divider)
        }
    }

    private fun setOnStampViewClicked(index: Int) {
        viewModel.setIndex(index)
        val bottomSheet = StampBottomSheetDialogFragment()
        bottomSheet.show(supportFragmentManager, null)
    }

    private fun setOnPlanViewClicked(text: String?, index: Int) {
        viewModel.setIndex(index)
        viewModel.setPlan(text ?: "")
    }
}