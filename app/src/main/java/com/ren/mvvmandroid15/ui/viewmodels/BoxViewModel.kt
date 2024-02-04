package com.ren.mvvmandroid15.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ren.mvvmandroid15.data.models.Box
import com.ren.mvvmandroid15.data.repositories.BoxRepository

class BoxViewModel : ViewModel() {

    private val boxRepository = BoxRepository()

    private val _boxesLiveData = MutableLiveData<BoxUiState<List<Box>>>()
    val boxLiveData: LiveData<BoxUiState<List<Box>>> = _boxesLiveData

    private val _editBoxLiveData = MutableLiveData<EditBox>()
    val editBoxLiveData: LiveData<EditBox> = _editBoxLiveData

    init {
        Log.i("ViewModel", "initialize")
        getBoxes()
    }

    fun getBoxes() {
        android.os.Handler().postDelayed(
            {
                val boxes = boxRepository.getBoxes()
                if (boxes.size <= 10) {
                    _boxesLiveData.value =
                        BoxUiState(isLoading = false, success = boxes)
                } else {
                    _boxesLiveData.value =
                        BoxUiState(isLoading = false, error = "very large amount of data")
                }
            },
            3000
        )
    }

    fun addBox(box: Box) {
        boxRepository.addBox(box)
    }

    fun passBoxToEdit(index: Int, box: Box) {
        _editBoxLiveData.value = EditBox(
            index = index,
            box = box
        )
    }

    fun editBox(index: Int, box: Box) {
        boxRepository.editBox(index, box)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "cleared")
    }
}

data class EditBox(
    val index: Int,
    val box: Box
)

data class BoxUiState<T>(
    val isLoading: Boolean = true,
    val error: String? = null,
    val success: T? = null
)