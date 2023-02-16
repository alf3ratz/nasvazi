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
        bookingEntityList = createBookingsList()
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        bookingAdapter = BookingAdapter(bookingEntityList, this, requireContext())
        timePagerAdapter = TimePagerAdapter(requireContext())
        _binding!!.bookingRecyclerView.run {
            setHasFixedSize(true)
            adapter = bookingAdapter
        }
        _binding!!.timePager.run {
            adapter = timePagerAdapter
            offscreenPageLimit = 4
            clipToPadding = false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(10))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
            setPageTransformer(compositePageTransformer)
        }
        _binding!!.timePicker.setColorFilter(R.color.color_red)


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
    }

    private fun createBookingsList():ArrayList<BookingEntity>{
        val booking1 = BookingEntity()
        booking1.id = 1L
        booking1.tableId = 1L
        booking1.participants = 3L

        val booking2 = BookingEntity()
        booking2.id = 2L
        booking2.tableId = 2L
        booking2.participants = 4L

        val booking3 = BookingEntity()
        booking3.id = 3L
        booking3.tableId = 3L
        booking3.participants = 5L

        val booking4 = BookingEntity()
        booking4.id = 4L
        booking4.tableId = 4L
        booking4.participants = 5L

        val booking5 = BookingEntity()
        booking5.id = 5L
        booking5.tableId = 5L
        booking5.participants = 5L

        val booking6 = BookingEntity()
        booking6.id = 6L
        booking6.tableId = 6L
        booking6.participants = 5L

        val booking7 = BookingEntity()
        booking7.id = 7L
        booking7.tableId = 7L
        booking7.participants = 5L

        val booking8 = BookingEntity()
        booking8.id = 8L
        booking8.tableId = 8L
        booking8.participants = 5L

        val booking9 = BookingEntity()
        booking9.id = 8L
        booking9.tableId = 8L
        booking9.participants = 5L

        return arrayListOf(booking1, booking2, booking3,booking4,booking5,booking6,booking7,booking8)
    }
}