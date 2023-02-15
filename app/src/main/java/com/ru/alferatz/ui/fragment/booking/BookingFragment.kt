package com.ru.alferatz.ui.fragment.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.ru.alferatz.R
import com.ru.alferatz.adapter.BookingAdapter
import com.ru.alferatz.adapter.TimePagerAdapter
import com.ru.alferatz.databinding.FragmentBookingBinding
import com.ru.alferatz.listener.BookingListener
import com.ru.alferatz.model.entity.BookingEntity
import kotlin.math.abs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookingFragment : Fragment(), BookingListener {

    private var _binding: FragmentBookingBinding? = null
    var bookingEntityList: ArrayList<BookingEntity> = ArrayList()
    private lateinit var bookingAdapter: BookingAdapter
    private lateinit var timePagerAdapter: TimePagerAdapter

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
        var booking1 = BookingEntity()
        booking1.id = 1L
        booking1.tableId = 1L
        booking1.participants = 3L

        var booking2 = BookingEntity()
        booking2.id = 2L
        booking2.tableId = 2L
        booking2.participants = 4L

        var booking3 = BookingEntity()
        booking3.id = 3L
        booking3.tableId = 3L
        booking3.participants = 5L

        bookingEntityList = arrayListOf(booking1, booking2, booking3)
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        bookingAdapter = BookingAdapter(bookingEntityList, this, requireContext())
        timePagerAdapter = TimePagerAdapter(requireContext())
        _binding!!.bookingRecyclerView.run {
            setHasFixedSize(true)
            adapter = bookingAdapter
        }
        _binding!!.timePager.run {
            adapter = timePagerAdapter
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
            setPageTransformer(compositePageTransformer)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onEventClicked(booking: BookingEntity) {
        binding.bookingRecyclerView.setOnClickListener {
            val bundle = bundleOf(BOOKING_KEY to "check")
            findNavController().navigate(
                R.id.action_bookingFragment_to_currentBookingFragment2,
                bundle
            )
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