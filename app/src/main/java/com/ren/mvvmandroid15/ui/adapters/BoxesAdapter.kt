package com.ren.mvvmandroid15.ui.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ren.mvvmandroid15.data.models.Box
import com.ren.mvvmandroid15.databinding.ItemBoxBinding

class BoxesAdapter(
    private val onItemClick: (box: Box) -> Unit,
    private val onItemEditClick: (index: Int, box: Box) -> Unit
) : RecyclerView.Adapter<BoxesAdapter.BoxViewHolder>() {

    private var boxes = emptyList<Box>()

    fun setBoxes(boxes: List<Box>) {
        this.boxes = boxes
        notifyDataSetChanged()
    }

    inner class BoxViewHolder(private val binding: ItemBoxBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tvItemEdit.setTextColor(
                ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_pressed),
                        intArrayOf(-android.R.attr.state_pressed),
                    ),
                    intArrayOf(
                        Color.YELLOW,
                        Color.BLACK
                    )
                )
            )

            binding.root.setOnClickListener {
                onItemClick(boxes[adapterPosition])
            }

            binding.tvItemEdit.setOnClickListener {
                onItemEditClick(adapterPosition, boxes[adapterPosition])
            }
        }

        fun onBind(box: Box) = with(binding) {
            try {
                root.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor(box.color)))
            } catch (e: Exception) {
                Toast.makeText(root.context, "Такого цвета не существует", Toast.LENGTH_SHORT).show()
            }
            tvItemText.text = box.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxViewHolder {
        val binding = ItemBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoxViewHolder(binding)
    }

    override fun getItemCount() = boxes.size

    override fun onBindViewHolder(holder: BoxViewHolder, position: Int) {
        holder.onBind(boxes[position])
    }
}