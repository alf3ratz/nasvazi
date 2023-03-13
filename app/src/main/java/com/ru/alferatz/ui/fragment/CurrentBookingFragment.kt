package com.ru.alferatz.ui.fragment

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ru.alferatz.R
import com.ru.alferatz.databinding.FragmentCurrentBookingBinding
import com.ru.alferatz.viewmodel.CurrentBookingViewModel

class CurrentBookingFragment : Fragment() {
    private lateinit var viewModel: CurrentBookingViewModel
    private var binding: FragmentCurrentBookingBinding? = null
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
        //var currentBooking = viewModel.getCurrentBooking("89164765968")
        var currentBooking = null
        if(currentBooking == null){
            binding!!.descriptionContainer.visibility = View.INVISIBLE
            binding!!.imageContainer.visibility = View.INVISIBLE
            binding!!.emptyText.apply {
                visibility = View.VISIBLE
                text = "У ВАС НЕТ АКТИВНЫХ БРОНИРОВАНИЙ"
            }
        }else{
            binding!!.emptyText.visibility = View.INVISIBLE
        }
        return binding!!.root
    }
}