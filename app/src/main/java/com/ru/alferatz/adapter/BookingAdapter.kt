package com.ru.alferatz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ru.alferatz.R
import com.ru.alferatz.databinding.BookingContainerBinding
import com.ru.alferatz.listener.BookingListener
import com.ru.alferatz.model.entity.BookingEntity

class BookingAdapter(events_: List<BookingEntity>, eventsListener_: BookingListener) :
    RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    private var events: List<BookingEntity> = events_
    private var layoutInflater: LayoutInflater? = null
    var eventsListener: BookingListener = eventsListener_

    inner class BookingViewHolder(itemLayoutBinding: BookingContainerBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {
        private var itemLayoutBinding: BookingContainerBinding? = null

        init {
            this.itemLayoutBinding = itemLayoutBinding
        }

        fun bindEvent(event: BookingEntity) {
            itemLayoutBinding?.bookingInfo = event
            itemLayoutBinding?.executePendingBindings()
            if (itemLayoutBinding?.root != null)
                itemView.setOnClickListener {
                    eventsListener.onEventClicked(event)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        val eventBinding: BookingContainerBinding =
            DataBindingUtil.inflate(layoutInflater!!, R.layout.booking_container, parent, false)
        val bookingViewHolder = BookingViewHolder(eventBinding)
        //bookingViewHolder.itemView
        return bookingViewHolder
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bindEvent(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }
}

//class MyAdapter(private val myDataset: Array<String>) :
//    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
//
//    // Provide a reference to the views for each data item
//    // Complex data items may need more than one view per item, and
//    // you provide access to all the views for a data item in a view holder.
//    // Each data item is just a string in this case that is shown in a TextView.
//    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)
//
//
//    // Create new views (invoked by the layout manager)
//    override fun onCreateViewHolder(parent: ViewGroup,
//                                    viewType: Int): ViewHolder {
//        // create a new view
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.list_view_item, parent, false)
//
//
//        return ViewHolder(itemView)
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        holder.item.findViewById<TextView>(R.id.user_name_text).text = myDataset[position]
//
//        holder.item.findViewById<ImageView>(R.id.user_avatar_image)
//            .setImageResource(listOfAvatars[position % listOfAvatars.size])
//
//        holder.item.setOnClickListener {
//            val bundle = bundleOf(USERNAME_KEY to myDataset[position])
//
//            holder.item.findNavController().navigate(
//                R.id.action_leaderboard_to_userProfile,
//                bundle)
//        }
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    override fun getItemCount() = myDataset.size
//
//    companion object {
//        const val USERNAME_KEY = "userName"
//    }
//}