package com.ru.alferatz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ru.alferatz.*
import com.ru.alferatz.databinding.CurrentBookingContainerBinding
import com.ru.alferatz.databinding.FragmentCurrentBookingBinding
import com.ru.alferatz.model.dto.BookingDto
import com.ru.alferatz.model.dto.TableDto
import com.ru.alferatz.model.entity.TableEntity
import com.ru.alferatz.ui.fragment.booking.SelectedBookingFragment
import com.ru.alferatz.ui.fragment.currentbooking.SelectedCurrentBookingFragment

class CurrentBookingTableAdapter(
    currentBookingList_: ArrayList<BookingDto>,
    tableEntityList_: ArrayList<TableEntity>,
    context_: Context,
    fragmentManager_: FragmentManager,
    parentBinding_: FragmentCurrentBookingBinding
) :
    RecyclerView.Adapter<CurrentBookingTableAdapter.CurrentBookingViewHolder>() {

    //private var events: List<Event> = events_
    //var eventsListener: EventListener = eventsListener_
    private var layoutInflater: LayoutInflater? = null
    private var currentBookingList = currentBookingList_
    private var context = context_
    private var fragmentManager = fragmentManager_
    private var parentBinding = parentBinding_

    inner class CurrentBookingViewHolder(itemLayoutBinding: CurrentBookingContainerBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {
        private var itemLayoutBinding: CurrentBookingContainerBinding? = null

        init {
            this.itemLayoutBinding = itemLayoutBinding
        }

        fun bindBooking(booking: BookingDto, tableName: String, position: Int) {
            itemLayoutBinding?.currentBooking = booking
            itemLayoutBinding?.bookedTableEntity =
                allTableEntityList.find { i -> i.id.equals(booking.tableId) }
            val pictureId = listOfTableImages[(booking.tableId!! - 1).toInt()]
            Glide.with(context).load(ContextCompat.getDrawable(context, pictureId))
                .transform(RoundedCorners(15))
                .into(itemLayoutBinding!!.tableImage)
            itemLayoutBinding?.executePendingBindings()
            itemView.setOnClickListener {
                val bundle =
                    bundleOf(
                        "PICTURE_ID" to position,
                        "TABLE_CAPACITY" to 0L,
                        "TABLE_NAME" to tableName,
                        "TABLE_TIME" to "${booking.startTime[2]} ${convertMonthToString(booking.startTime[1])}-${booking.startTime[3]}:${booking.startTime[4]}",
                        "BOOKING_ID" to booking.id
                    )
                val fragment = SelectedCurrentBookingFragment()
                fragment.arguments = bundle
                fragmentManager.beginTransaction()
                    .replace(
                        (parentBinding.root.parent as View).id,
                        fragment
                    )
                    .addToBackStack(null).commit()
            }
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
        holder.bindBooking(
            currentBookingList[position],
            findTableNameById(position + 1L),
            position
        )
    }

    override fun getItemCount(): Int {
        return currentBookingList.size
    }
}