package com.ru.alferatz.ui.fragment.currentbooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ru.alferatz.adapter.CurrentBookingTableAdapter
import com.ru.alferatz.allTableEntityList
import com.ru.alferatz.currentUserId
import com.ru.alferatz.databinding.FragmentCurrentBookingBinding
import com.ru.alferatz.enums.BookingStatus
import com.ru.alferatz.model.dto.BookingDto
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.viewmodel.CurrentBookingViewModel

class CurrentBookingFragment : Fragment() {
    private lateinit var viewModel: CurrentBookingViewModel
    private var binding: FragmentCurrentBookingBinding? = null
    private var bookingByUser: ArrayList<BookingDto> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CurrentBookingViewModel::class.javaObjectType]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrentBookingBinding.inflate(inflater, container, false)
        getCurrentBooking()
        binding!!.apply {
            //setVisibility()
            emptyText.visibility = View.INVISIBLE
            currentBookingRecycler.apply {
                adapter = CurrentBookingTableAdapter(
                    bookingByUser, allTableEntityList, context, parentFragmentManager, binding!!
                )
                setHasFixedSize(true)
            }
            currentBookingRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!currentBookingRecycler.canScrollVertically(1)) {
                        //if (currentPage <= totalAvailablePages) {
                        //    currentPage++
                        //getCurrentBooking()
                        //  }
                    }
                }
            })
            imageUpdate.setOnClickListener {
                getCurrentBooking()
            }
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getCurrentBooking() {
        viewModel.getBookingByUser(currentUserId).observe(requireActivity()) { response: BookingResponse ->
                bookingByUser =
                    response.bookings.filter { i -> i.status!! == BookingStatus.CREATED || i.status == BookingStatus.CONFIRMED } as ArrayList<BookingDto>
                bookingByUser.apply {
                    binding!!.currentBookingRecycler.apply {
                        adapter = CurrentBookingTableAdapter(
                            bookingByUser,
                            allTableEntityList,
                            context,
                            parentFragmentManager,
                            binding!!
                        )
                        adapter!!.notifyDataSetChanged()
                        setVisibility()
                    }
                }
            }
    }

    private fun setVisibility() {
        binding!!.apply {
            if (bookingByUser.isEmpty()) {
                currentBookingRecycler.visibility = View.INVISIBLE
                emptyText.visibility = View.VISIBLE
                emptyText.text = "У ВАС НЕТ АКТИВНЫХ БРОНИРОВАНИЙ"
            }
        }
    }
}