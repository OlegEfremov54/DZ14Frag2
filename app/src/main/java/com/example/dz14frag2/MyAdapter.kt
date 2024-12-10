package com.example.dz14frag2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (val textMess: MutableList<TextMes>) :
    RecyclerView.Adapter<MyAdapter.TextViewHolder>() {

    private var onTextClickListener: OnTextClickListener? = null

    interface OnTextClickListener {
        fun OnTextClick (textMes: TextMes, position: Int)
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countTV: TextView = itemView.findViewById(R.id.countTV)
        val textTV: TextView = itemView.findViewById(R.id.textTV)
        var dateTV: TextView = itemView.findViewById(R.id.dateTV)
        val checkBoxStartCB:CheckBox = itemView.findViewById(R.id.checkBoxStartCB)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return TextViewHolder(itemView)
    }

    override fun getItemCount() = textMess.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val textMes = textMess[position]
        holder.countTV.text = textMes.count.toString()
        holder.textTV.text = textMes.text
        holder.dateTV.text = textMes.date
        holder.checkBoxStartCB.isChecked = textMes.checkBoxStart
        holder.itemView.setOnClickListener {
            if (onTextClickListener != null) {
                onTextClickListener!!.OnTextClick(textMes, position)
            }
        }
    }

    fun setOnTextClickListener(onTextClickListener: OnTextClickListener) {
        this.onTextClickListener = onTextClickListener
    }
}