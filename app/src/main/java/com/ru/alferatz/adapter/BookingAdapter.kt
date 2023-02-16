package com.ru.alferatz.adapter


import android.content.Context
import android.icu.number.NumberFormatter.with
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ru.alferatz.R
import com.ru.alferatz.databinding.BookingContainerBinding
import com.ru.alferatz.enums.BookingStatus
import com.ru.alferatz.listener.BookingListener
import com.ru.alferatz.model.entity.BookingEntity


class BookingAdapter(
    bookings_: List<BookingEntity>,
    bookingListener_: BookingListener,
    context_: Context
) :
    RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    private var bookings: List<BookingEntity> = bookings_
    private var layoutInflater: LayoutInflater? = null
    var bookingListener: BookingListener = bookingListener_
    var context: Context = context_
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

    inner class BookingViewHolder(itemLayoutBinding: BookingContainerBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {
        private var itemLayoutBinding: BookingContainerBinding? = null

        init {
            this.itemLayoutBinding = itemLayoutBinding
        }

        fun bindBooking(bookingEntity: BookingEntity) {
            //itemLayoutBinding?.bookingInfo = bookingEntity
//            itemLayoutBinding?.root.
//            itemLayoutBinding?.tableId = bookingEntity.tableId
            itemLayoutBinding?.executePendingBindings()
            if (itemLayoutBinding?.root != null)
                itemView.setOnClickListener {
                    bookingListener.onEventClicked(bookingEntity)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        val bookingBinding: BookingContainerBinding =
            DataBindingUtil.inflate(
                layoutInflater!!,
                com.ru.alferatz.R.layout.booking_container,
                parent,
                false
            )
        val bookingViewHolder = BookingViewHolder(bookingBinding)
        return bookingViewHolder
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val pictureId = listOfTableImages[position % listOfTableImages.size]
        val bookingStatus = bookings[position].status.description
        holder.bindBooking(bookings[position])

        Glide.with(context).load(ContextCompat.getDrawable(context, pictureId))
            //.optionalFitCenter() // scale image to fill the entire ImageView
            .transform(RoundedCorners(25))
            .into(holder.itemView.findViewById(R.id.table_image))


        holder.itemView.findViewById<TextView>(R.id.table_number)
            .text = "№${bookings[position].tableId}"
        holder.itemView.findViewById<TextView>(R.id.table_people_count)
            .text = bookings[position].tableId.toString()
        holder.itemView.findViewById<TextView>(R.id.booking_status)
            .text = bookingStatus
        var statusIcon = holder.itemView.findViewById<ImageView>(R.id.status_icon)
        when (bookingStatus) {
            BookingStatus.CREATED.name -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_green))
            BookingStatus.CONFIRMED.name -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_yellow))
            BookingStatus.CANCELLED.name -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_red))
            else -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_green))
        }
//        holder.itemView.setOnClickListener {
//            val bundle = bundleOf("booking_adapter" to pictureId)
//            context.findNavController().navigate(
//                R.id.action_bookingFragment_to_currentBookingFragment2,
//                bundle
//            )
//        }
    }

    override fun getItemCount(): Int {
        return bookings.size
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