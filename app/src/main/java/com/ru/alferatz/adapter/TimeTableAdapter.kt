package com.ru.alferatz.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ru.alferatz.R
import com.ru.alferatz.databinding.BookingContainerBinding
import com.ru.alferatz.databinding.FragmentSelectedBookingBinding
import com.ru.alferatz.databinding.TimeTableContainerBinding
import com.ru.alferatz.enums.BookingStatus
import com.ru.alferatz.model.entity.BookingEntity
import com.ru.alferatz.selectedTime

class TimeTableAdapter(
    context_: Context,
    timeIntervals: List<String>,
    availableTimeIntervals: List<String>,
    selectedBookingBinding_:FragmentSelectedBookingBinding
) :
    RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder>() {

    private var layoutInflater: LayoutInflater? = null
    var context: Context = context_
    private val listOfTimeIntervals = timeIntervals
    private val listOfAvailableIntervals = availableTimeIntervals
    private val selectedBookingBinding = selectedBookingBinding_
    private val listOfTableImages = listOf(
        com.ru.alferatz.R.drawable.table_1,
        com.ru.alferatz.R.drawable.table_2,
        com.ru.alferatz.R.drawable.table_3,
        com.ru.alferatz.R.drawable.table_4,
        com.ru.alferatz.R.drawable.table_5,
        com.ru.alferatz.R.drawable.table_7,
        com.ru.alferatz.R.drawable.table_8,
        com.ru.alferatz.R.drawable.table_9
    )

    inner class TimeTableViewHolder(itemLayoutBinding: TimeTableContainerBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {
        private var itemLayoutBinding: TimeTableContainerBinding? = null

        init {
            this.itemLayoutBinding = itemLayoutBinding
        }

        fun bindTimeTable() {
            //itemLayoutBinding?.bookingInfo = bookingEntity
//            itemLayoutBinding?.root.
//            itemLayoutBinding?.tableId = bookingEntity.tableId
            itemLayoutBinding?.executePendingBindings()
//            if (itemLayoutBinding?.root != null)
//                itemView.setOnClickListener {
//                    bookingListener.onEventClicked(bookingEntity)
//                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        val bookingBinding: TimeTableContainerBinding =
            DataBindingUtil.inflate(
                layoutInflater!!,
                com.ru.alferatz.R.layout.time_table_container,
                parent,
                false
            )
        val bookingViewHolder = TimeTableViewHolder(bookingBinding)
        return bookingViewHolder
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        holder.bindTimeTable()
        holder.itemView.findViewById<TextView>(R.id.time_text)
            .text = listOfTimeIntervals[position]
        val statusIcon = holder.itemView.findViewById<ImageView>(R.id.time_status_icon)
        when (listOfAvailableIntervals.contains(listOfTimeIntervals[position])) {
            false -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_green))
            true -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_red))
            else -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_yellow))
        }
        holder.itemView.apply {
            setOnClickListener {
                selectedBookingBinding.commentContainer.visibility = View.VISIBLE
            }
            selectedTime = findViewById<TextView>(R.id.time_text).text.toString()
        }
    }

    override fun getItemCount(): Int {
        return listOfTimeIntervals.size
    }
}