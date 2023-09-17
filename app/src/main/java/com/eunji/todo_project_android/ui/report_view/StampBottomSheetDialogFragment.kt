package com.eunji.todo_project_android.ui.report_view

import android.graphics.drawable.Drawable
import android.os.Build.VERSION_CODES.S
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.eunji.todo_project_android.R
import com.eunji.todo_project_android.databinding.FragmentStampBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StampBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentStampBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
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
        binding.recyclerview.adapter = StampRecyclerViewAdapter(setStampList()){stampIndex->
            setFragmentResult("requestKey", bundleOf("bundleKey" to stampIndex))
        }
        binding.recyclerview.addItemDecoration(GridSpaceItemDecoration(4, 6))
    }

    private fun setStampList(): List<Drawable> {
        val stampDrawableList = mutableListOf<Drawable>()
        val drawableIds = resources.obtainTypedArray(R.array.stamp_drawables)

        for (i in 0..46) {
            drawableIds.getDrawable(i)?.let { drawable ->
                stampDrawableList.add(drawable)
            }
        }

        drawableIds.recycle()
        return stampDrawableList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}