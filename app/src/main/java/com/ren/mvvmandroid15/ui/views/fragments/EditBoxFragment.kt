package com.ren.mvvmandroid15.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ren.mvvmandroid15.data.models.Box
import com.ren.mvvmandroid15.databinding.FragmentDetailBinding
import com.ren.mvvmandroid15.databinding.FragmentEditBoxBinding
import com.ren.mvvmandroid15.ui.adapters.BoxesAdapter
import com.ren.mvvmandroid15.ui.viewmodels.BoxViewModel

class EditBoxFragment : Fragment() {

    private var _binding: FragmentEditBoxBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<BoxViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSavePressed()
        subscribeToEditBox()
    }

    private fun onSavePressed() = with(binding) {
        btnSave.setOnClickListener {
            val editBox = viewModel.editBoxLiveData.value
            val newBoxName = etBoxName.text.toString().trim()
            val newBoxColor = etBoxColor.text.toString().trim()
            if (editBox != null) {
                val box = Box(
                    text = newBoxName,
                    color = newBoxColor
                )
                viewModel.editBox(editBox.index, box)
            }
            findNavController().navigateUp()
        }
    }

    private fun subscribeToEditBox() = with(binding) {
        viewModel.editBoxLiveData.observe(viewLifecycleOwner) { editBox ->
            editBox?.let {
                etBoxName.setText(it.box.text)
                etBoxColor.setText(it.box.color)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}