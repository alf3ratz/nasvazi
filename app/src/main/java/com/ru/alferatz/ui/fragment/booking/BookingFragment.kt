package com.ru.alferatz.ui.fragment.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ru.alferatz.R
import com.ru.alferatz.adapter.BookingAdapter
import com.ru.alferatz.databinding.FragmentBookingBinding
import com.ru.alferatz.listener.BookingListener
import com.ru.alferatz.model.entity.BookingEntity

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookingFragment : Fragment(), BookingListener {

    private var _binding: FragmentBookingBinding? = null
    var bookingEntityList: ArrayList<BookingEntity> = ArrayList()
    private lateinit var bookingAdapter: BookingAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    companion object {
        const val BOOKING_KEY = "showBookingPage"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var booking = BookingEntity()
        booking.id=1L
        booking.tableId=1L
        booking.participants=3L

        bookingEntityList = arrayListOf(booking)
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        bookingAdapter = BookingAdapter(bookingEntityList, this)
        _binding!!.weatherRecyclerView.run {
            setHasFixedSize(true)
            adapter = bookingAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            //findNavController().navigate(R.id.action_AuthFragment_to_confirmFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onEventClicked(booking: BookingEntity) {
        binding.weatherRecyclerView.setOnClickListener {
            val bundle = bundleOf(BOOKING_KEY to "check")
            findNavController().navigate(R.id.action_bookingFragment_to_currentBookingFragment2, bundle)
        }
//        binding.weatherRecyclerView.findNavController().navigate(R.id.action_bookingFragment_to_currentBookingFragment2, bund)
//        holder.item.setOnClickListener {
//            val bundle = bundleOf(USERNAME_KEY to myDataset[position])
//
//            holder.item.findNavController().navigate(
//                R.id.action_leaderboard_to_userProfile,
//                bundle)
//        }
    }
}