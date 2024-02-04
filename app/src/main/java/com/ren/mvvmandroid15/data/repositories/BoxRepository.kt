package com.ren.mvvmandroid15.data.repositories

import com.ren.mvvmandroid15.data.models.Box

class BoxRepository {

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

    fun getBoxes() = boxes

    fun addBox(box: Box) {
        boxes.add(box)
    }

    fun editBox(index: Int, box: Box) {
        boxes[index] = box
    }
}