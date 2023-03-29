package com.ru.alferatz.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.ru.alferatz.R
import com.ru.alferatz.activityBinding
import com.ru.alferatz.adapter.ImageSliderAdapter
import com.ru.alferatz.databinding.FragmentCurrentBookingBinding
import com.ru.alferatz.databinding.FragmentInfoBinding
import com.ru.alferatz.listOfInfoImages

class InfoFragment : Fragment() {
    private var binding: FragmentInfoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        val adapter = ImageSliderAdapter(listOfInfoImages, requireContext())
        binding!!.apply {
            sliderViewPager.adapter = adapter
            sliderViewPager.offscreenPageLimit = 1
        }
        setupSliderIndicators(listOfInfoImages.size)
        binding!!.sliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentSliderIndicator(position)
            }
        })
        return binding!!.root
    }

    private fun setupSliderIndicators(count: Int) {
        val indicators: MutableList<ImageView> = mutableListOf<ImageView>().toMutableList()
        for (i in 0 until count) {
            indicators += ImageView(context)
        }
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in 0 until count) {
            indicators[i] = ImageView(requireContext())
            indicators[i].setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.background_slider_indicator_inactive
                )
            )
            indicators[i].layoutParams = layoutParams
            binding?.layoutSliderIndicators?.addView(indicators[i])
        }
        binding?.layoutSliderIndicators?.visibility = View.VISIBLE
        setCurrentSliderIndicator(0)
    }

    private fun setCurrentSliderIndicator(position: Int) {
        val childCount: Int? = binding?.layoutSliderIndicators?.childCount
        for (i in 0 until childCount!!) {
            val imageView: ImageView =
                binding?.layoutSliderIndicators?.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.background_slider_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.background_slider_indicator_inactive
                    )
                )
            }
        }
    }
}