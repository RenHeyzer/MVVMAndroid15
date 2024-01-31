package com.ren.mvvmandroid15.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ren.mvvmandroid15.data.models.Box

class BoxViewModel : ViewModel() {

    private val _boxesLiveData = MutableLiveData<BoxUiState<List<Box>>>()
    val boxLiveData: LiveData<BoxUiState<List<Box>>> = _boxesLiveData

    private val boxes = mutableListOf(
        Box(text = "Black Box", color = "#FF000000"),
        Box(text = "White Box", color = "#FFFFFFFF"),
        Box(text = "Red Box", color = "#F44336"),
        Box(text = "Blue Box", color = "#3F51B5"),
        Box(text = "Green Box", color = "#4CAF50"),
        Box(text = "Orange Box", color = "#FF9800"),
        Box(text = "Pink Box", color = "#FD578F"),
        Box(text = "Yellow Box", color = "#FFEB3B"),
        Box(text = "Purple Box", color = "#9C27B0"),
        Box(text = "Lime Box", color = "#8BC34A"),
    )

    init {
        Log.i("ViewModel", "initialize")
        getBoxes()
    }

    private fun getBoxes() {
        android.os.Handler().postDelayed(
            {
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
        boxes.add(box)
        val newValue = boxLiveData.value?.copy(success = boxes)
        newValue?.let {
            _boxesLiveData.value = it
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "cleared")
    }
}

data class BoxUiState<T>(
    val isLoading: Boolean = true,
    val error: String? = null,
    val success: T? = null
)