package com.ru.alferatz.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ru.alferatz.R
import com.ru.alferatz.bookingListByDateTimeUtils
import com.ru.alferatz.databinding.FragmentBookingBinding
import com.ru.alferatz.databinding.TimePagerContainerBinding
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.selectedDate
import com.ru.alferatz.viewmodel.BookingViewModel

class TimePagerAdapter(
    context_: Context, timeIntervals: List<String>,
    viewModel_: BookingViewModel,
    activity_: FragmentActivity,
    bookingAdapter_: BookingAdapter
) :
    RecyclerView.Adapter<TimePagerAdapter.TimePagerViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    var context: Context = context_
    private val listOfTimeIntervals = timeIntervals
    private val viewModel: BookingViewModel = viewModel_
    private val activity: FragmentActivity = activity_
    private val bookingAdapter: BookingAdapter = bookingAdapter_

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
        holder.itemView.apply {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                setOnScrollChangeListener { _, _, _, _, _ ->
//                    viewModel.getBookingByDateTime("${selectedDate}T${listOfTimeIntervals[position]}")
//                        .observe(activity) { response: BookingResponse ->
//                            // TODO: подумать как обновить список на странице бронирований
//                            bookingListByDateTimeUtils.addAll(response.bookings)
//                            bookingAdapter.notifyDataSetChanged()
//                        }
//                }
//            }
//            setOnClickListener {
//                viewModel.getBookingByDateTime("${selectedDate}T${listOfTimeIntervals[position]}")
//                    .observe(activity) { response: BookingResponse ->
//                        // TODO: подумать как обновить список на странице бронирований
//                        bookingListByDateTimeUtils.addAll(response.bookings)
//                        bookingAdapter.notifyDataSetChanged()
//                    }
//            }
        }
    }

    override fun getItemCount(): Int {
        return listOfTimeIntervals!!.size
    }
}