package com.ru.alferatz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ru.alferatz.R
import com.ru.alferatz.allTableEntityList
import com.ru.alferatz.databinding.CurrentBookingContainerBinding
import com.ru.alferatz.listOfTableImages
import com.ru.alferatz.model.dto.BookingDto
import com.ru.alferatz.model.dto.TableDto
import com.ru.alferatz.model.entity.TableEntity

class CurrentBookingTableAdapter(
    currentBookingList_: ArrayList<BookingDto>,
    tableEntityList_: ArrayList<TableEntity>,
    context_: Context
) :
    RecyclerView.Adapter<CurrentBookingTableAdapter.CurrentBookingViewHolder>() {

    //private var events: List<Event> = events_
    //var eventsListener: EventListener = eventsListener_
    private var layoutInflater: LayoutInflater? = null
    private var currentBookingList = currentBookingList_
    private var context = context_

    inner class CurrentBookingViewHolder(itemLayoutBinding: CurrentBookingContainerBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {
        private var itemLayoutBinding: CurrentBookingContainerBinding? = null

        init {
            this.itemLayoutBinding = itemLayoutBinding
        }

        fun bindEvent(booking: BookingDto) {
            itemLayoutBinding?.currentBooking = booking
            itemLayoutBinding?.bookedTableEntity =
                allTableEntityList.find { i -> i.id.equals(booking.tableId) }
            val pictureId = listOfTableImages[(booking.tableId!! - 1).toInt()]
            Glide.with(context).load(ContextCompat.getDrawable(context, pictureId))
                //.optionalFitCenter() // scale image to fill the entire ImageView
                .transform(RoundedCorners(25))
                .into(itemLayoutBinding!!.tableImage)
            itemLayoutBinding?.executePendingBindings()
//            if (itemLayoutBinding?.root != null)
//                itemView.setOnClickListener {
//                    eventsListener.onEventClicked(event)
//                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentBookingViewHolder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        val eventBinding: CurrentBookingContainerBinding =
            DataBindingUtil.inflate(
                layoutInflater!!,
                R.layout.current_booking_container,
                parent,
                false
            )
        return CurrentBookingViewHolder(eventBinding)
    }

    override fun onBindViewHolder(holder: CurrentBookingViewHolder, position: Int) {
        holder.bindEvent(currentBookingList[position])
    }

    override fun getItemCount(): Int {
        return currentBookingList.size
    }
}