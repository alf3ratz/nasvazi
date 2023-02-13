package com.ru.alferatz.ui.fragment.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
        TODO("Not yet implemented")
    }
}