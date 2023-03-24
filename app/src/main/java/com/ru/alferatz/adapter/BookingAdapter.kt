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
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ru.alferatz.R
import com.ru.alferatz.databinding.BookingContainerBinding
import com.ru.alferatz.databinding.FragmentBookingBinding
import com.ru.alferatz.enums.BookingStatus
import com.ru.alferatz.enums.IsBookingClear
import com.ru.alferatz.findTableNameById
import com.ru.alferatz.listOfTableImages
import com.ru.alferatz.listener.BookingListener
import com.ru.alferatz.model.dto.BookingDto
import com.ru.alferatz.model.dto.TableDto
import com.ru.alferatz.model.entity.BookingEntity
import com.ru.alferatz.model.entity.TableEntity
import com.ru.alferatz.ui.fragment.booking.BookingFragment
import com.ru.alferatz.ui.fragment.booking.SelectedBookingFragment
import java.time.LocalDateTime


class BookingAdapter(
    bookingsByDateTime_: List<BookingDto>,
    allTables_: ArrayList<TableEntity>,
    tablesByDateTime_: HashSet<TableDto>,
    bookingListener_: BookingListener,
    context_: Context,
    fragmentManager_: FragmentManager,
    parentBinding_: FragmentBookingBinding,
    bookingFragmentObject_: BookingFragment
) :
    RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    //TODO: пока что болванка, подумать как переделать
    private val bookingsByDateTime: List<BookingDto> = bookingsByDateTime_
    private val bookingsByDateTimeWithUniqueTables: List<BookingDto> =
        bookingsByDateTime_.distinctBy { i -> i.tableId }
    private val allTableEntities: ArrayList<TableEntity> = allTables_
    private val bookingFragmentObject = bookingFragmentObject_

    //private var bookings: List<BookingEntity> = bookingEntityList_
    private var bookedTablesAtSelectedTime: HashSet<TableDto> = tablesByDateTime_
    private var layoutInflater: LayoutInflater? = null
    var bookingListener: BookingListener = bookingListener_
    var context: Context = context_
    var fragmentManager: FragmentManager = fragmentManager_
    var parentBinding: FragmentBookingBinding = parentBinding_

    inner class BookingViewHolder(itemLayoutBinding: BookingContainerBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {
        private var itemLayoutBinding: BookingContainerBinding? = null

        init {
            this.itemLayoutBinding = itemLayoutBinding
        }

        fun bindBooking(bookingDto: BookingDto, tableName: String, position: Int) {
            //itemLayoutBinding?.bookingInfo = bookingEntity
//            itemLayoutBinding?.root.
//            itemLayoutBinding?.tableId = bookingEntity.tableId
            itemLayoutBinding!!.apply {
                if (bookingDto.id != -1L) {
                    table = allTableEntities.find { i -> i.name.equals(tableName) }
                    status = IsBookingClear.PARTIALLY_NOT_CLEAR
                } else {
                    table = allTableEntities.find { i -> i.name.equals(tableName) }
                    status = IsBookingClear.IS_CLEAR
                }
                executePendingBindings()
                val pictureId = listOfTableImages[position % listOfTableImages.size]
                //val bookingStatus = bookingsByDateTime[position].status.description
//
                Glide.with(context).load(ContextCompat.getDrawable(context, pictureId))
                    //.optionalFitCenter() // scale image to fill the entire ImageView
                    .transform(RoundedCorners(25))
                    .into(tableImage)
                when (status) {
                    IsBookingClear.IS_CLEAR -> statusIcon.setColorFilter(
                        context.resources.getColor(
                            R.color.color_green
                        )
                    )
                    IsBookingClear.PARTIALLY_NOT_CLEAR -> statusIcon.setColorFilter(
                        context.resources.getColor(
                            R.color.color_yellow
                        )
                    )
                    IsBookingClear.FULLY_NOT_CLEAR -> statusIcon.setColorFilter(
                        context.resources.getColor(
                            R.color.color_red
                        )
                    )
                }
                itemView.setOnClickListener {
                    val bundle =
                        bundleOf(
                            "PICTURE_ID" to position,
                            "TABLE_CAPACITY" to table!!.capacity,
                            "TABLE_NAME" to tableName
                        )
                    val fragment = SelectedBookingFragment()
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        val bookingBinding: BookingContainerBinding =
            DataBindingUtil.inflate(
                layoutInflater!!,
                R.layout.booking_container,
                parent,
                false
            )

        val bookingViewHolder = BookingViewHolder(bookingBinding)
        return bookingViewHolder
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        if (bookingsByDateTimeWithUniqueTables.find { i -> i.tableId!!.equals(position + 1) } != null) {
            holder.bindBooking(
                bookingsByDateTimeWithUniqueTables[position],
                findTableNameById(position + 1L),
                position
            )
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if ((position + 1) > 6) {
                    holder.bindBooking(
                        BookingDto(
                            -1L,
                            1L,
                            "",
                            "",
                            longArrayOf(),
                            BookingStatus.CREATED,
                            -1L,
                            0L,
                            ""
                        ), findTableNameById(5L), position
                    )
                } else {
                    holder.bindBooking(
                        BookingDto(
                            -1L,
                            1L,
                            "",
                            "",
                            longArrayOf(),
                            BookingStatus.CREATED,
                            -1L,
                            0L,
                            ""
                        ), findTableNameById(position + 1L), position
                    )
                }

            }
        }

//        val pictureId = listOfTableImages[position % listOfTableImages.size]
//        val bookingStatus = bookingsByDateTime[position].status.description
//
//        Glide.with(context).load(ContextCompat.getDrawable(context, pictureId))
//            //.optionalFitCenter() // scale image to fill the entire ImageView
//            .transform(RoundedCorners(25))
//            .into(holder.itemView.findViewById(R.id.table_image))
//
//
//        holder.itemView.findViewById<TextView>(R.id.table_number)
//            .text = "№${bookingsByDateTime[position].tableId}"
//        holder.itemView.findViewById<TextView>(R.id.table_people_count)
//            .text = bookingsByDateTime[position].tableId.toString()
//        holder.itemView.findViewById<TextView>(R.id.booking_status)
//            .text = bookingStatus
//        var statusIcon = holder.itemView.findViewById<ImageView>(R.id.status_icon)
//        when (bookingStatus) {
//            BookingStatus.CREATED.name -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_green))
//            BookingStatus.CONFIRMED.name -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_yellow))
//            BookingStatus.CANCELLED.name -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_red))
//            else -> statusIcon.setColorFilter(context.resources.getColor(com.ru.alferatz.R.color.color_green))
//        }
//        holder.itemView.setOnClickListener {
//            val bundle =
//                bundleOf("PICTURE_ID" to position, "TABLE_CAPACITY" to 1, "TABLE_NAME" to "")
//            val fragment = SelectedBookingFragment()
//            fragment.arguments = bundle
//            fragmentManager.beginTransaction()
//                .replace(
//                    (parentBinding.root.parent as View).id,
//                    fragment
//                )
//                .addToBackStack(null).commit()
//        }
    }

    override fun getItemCount(): Int {
        return listOfTableImages.size
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