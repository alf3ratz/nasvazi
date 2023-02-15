package com.ru.alferatz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ru.alferatz.R
import com.ru.alferatz.databinding.TimePagerContainerBinding

class TimePagerAdapter(context_: Context) :
    RecyclerView.Adapter<TimePagerAdapter.TimePagerViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    var context: Context = context_
    private val listOfTimeIntervals = listOf("12:30", "14:30", "16:30", "18:30", "20:30", "22:30")

    inner class TimePagerViewHolder(itemContainerSliderImageBinding: TimePagerContainerBinding) :
        RecyclerView.ViewHolder(itemContainerSliderImageBinding.root) {
        private var binding: TimePagerContainerBinding? = null

        init {
            this.binding = itemContainerSliderImageBinding
        }

        fun bindSliderImage() {
            binding?.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimePagerViewHolder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        val sliderImageBinding: TimePagerContainerBinding = DataBindingUtil.inflate(
            layoutInflater!!, R.layout.time_pager_container, parent, false
        )
        return TimePagerViewHolder(sliderImageBinding)
    }

    override fun onBindViewHolder(holder: TimePagerViewHolder, position: Int) {
        holder.bindSliderImage()
        holder.itemView.findViewById<TextView>(R.id.time_text)
            .text = listOfTimeIntervals[position]

    }

    override fun getItemCount(): Int {
        return listOfTimeIntervals!!.size
    }
}