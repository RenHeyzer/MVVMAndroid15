package com.ren.mvvmandroid15.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ren.mvvmandroid15.data.models.Box
import com.ren.mvvmandroid15.databinding.FragmentBoxBinding
import com.ren.mvvmandroid15.ui.adapters.BoxesAdapter
import com.ren.mvvmandroid15.ui.viewmodels.BoxViewModel

class BoxFragment : Fragment() {

    private var _binding: FragmentBoxBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<BoxViewModel>()
    private val boxesAdapter = BoxesAdapter(::onItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Fragment", "onCreate")
    }

    private fun onItemClick(box: Box) {
        Log.i("onItemClick", "click")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        addNewBox()
        subscribeToBoxes()
    }

    private fun initialize() {
        binding.rvBoxes.adapter = boxesAdapter
    }

    private fun addNewBox() {
        binding.fabAdd.setOnClickListener {
            val newBox = Box("Ocean Box", "#FF22B9FD")
            viewModel.addBox(newBox)
        }
    }

    private fun subscribeToBoxes() {
        viewModel.boxLiveData.observe(viewLifecycleOwner) { boxUiState ->
            boxUiState?.let {
                binding.progressBar.isVisible = it.isLoading

                if (it.success != null) {
                    boxesAdapter.setBoxes(it.success)
                } else {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Fragment", "onDestroy")
    }
}