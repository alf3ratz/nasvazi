package com.ru.alferatz.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ru.alferatz.adapter.CurrentBookingTableAdapter
import com.ru.alferatz.allTableEntityList
import com.ru.alferatz.currentUserId
import com.ru.alferatz.databinding.FragmentCurrentBookingBinding
import com.ru.alferatz.model.dto.BookingDto
import com.ru.alferatz.model.request.BookingByUserRequest
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.viewmodel.CurrentBookingViewModel

class CurrentBookingFragment : Fragment() {
    private lateinit var viewModel: CurrentBookingViewModel
    private var binding: FragmentCurrentBookingBinding? = null
    private val bookingByUser: ArrayList<BookingDto> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CurrentBookingViewModel::class.javaObjectType]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrentBookingBinding.inflate(inflater, container, false)
        binding!!.apply {
            if (bookingByUser.isEmpty()) {
                currentBookingRecycler.visibility = View.INVISIBLE
                emptyText.visibility = View.VISIBLE
                emptyText.text = "У ВАС НЕТ АКТИВНЫХ БРОНИРОВАНИЙ"
            }
            emptyText.visibility = View.VISIBLE
            currentBookingRecycler.apply {
                adapter = CurrentBookingTableAdapter(bookingByUser, allTableEntityList, context)
            }
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBookingByUser(34L)
            .observe(requireActivity()) { response: BookingResponse ->
                bookingByUser.addAll(response.bookings)
                binding!!.currentBookingRecycler.apply {
                    adapter!!.notifyDataSetChanged()
                }
            }
    }
}