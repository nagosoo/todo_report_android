package com.eunji.todo_project_android.ui.report_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.eunji.todo_project_android.AppApplication
import com.eunji.todo_project_android.databinding.FragmentStampBottomSheetDialogBinding
import com.eunji.todo_project_android.repository.TodoRepositoryImpl
import com.eunji.todo_project_android.room.TodoDatabase
import com.eunji.todo_project_android.util.StampUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StampBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentStampBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    private val db = TodoDatabase.getInstance(AppApplication.applicationContext())
    private val repository = TodoRepositoryImpl(db)
    private val viewModel by lazy { ViewModelProvider(requireActivity(), TodoViewModelFactory(repository))[ReportViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStampBottomSheetDialogBinding.inflate(inflater, container, false)
        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView() {
        binding.recyclerview.adapter = StampRecyclerViewAdapter(StampUtil.stampList) { stampIndex ->
             viewModel.setStamp(stampIndex)
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
        binding.recyclerview.addItemDecoration(GridSpaceItemDecoration(4, 6))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}