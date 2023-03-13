package com.ru.alferatz.ui.fragment.booking

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.ru.alferatz.R
import com.ru.alferatz.adapter.BookingAdapter
import com.ru.alferatz.adapter.TimePagerAdapter
import com.ru.alferatz.bookingListByDateUtils
import com.ru.alferatz.databinding.FragmentBookingBinding
import com.ru.alferatz.listener.BookingListener
import com.ru.alferatz.model.dto.BookingDto
import com.ru.alferatz.model.dto.TableDto
import com.ru.alferatz.model.entity.BookingEntity
import com.ru.alferatz.model.entity.TableEntity
import com.ru.alferatz.model.response.BookingResponse
import com.ru.alferatz.model.response.TableEntityResponse
import com.ru.alferatz.selectedDate
import com.ru.alferatz.selectedDateAsLocalDate
import com.ru.alferatz.viewmodel.BookingViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class BookingFragment : Fragment(), BookingListener {
    private lateinit var viewModel: BookingViewModel
    private var bookingListByDate: List<BookingDto> = ArrayList()
    private var binding: FragmentBookingBinding? = null
    private lateinit var bookingAdapter: BookingAdapter
    private var tableList: ArrayList<TableDto> = ArrayList()
    private var allTablesList: ArrayList<TableEntity> = ArrayList()
    private val listOfTimeIntervals = listOf(
        "00:30:00",
        "02:30:00",
        "04:30:00",
        "06:30:00",
        "08:30:00",
        "10:30:00",
        "12:30:00",
        "14:30:00",
        "16:30:00",
        "18:30:00",
        "20:30:00",
        "22:30:00"
    )
    private val listOfMonths = listOf(
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря",
    )
    private var bookingEntityList: ArrayList<BookingEntity> = ArrayList()
    private lateinit var timePagerAdapter: TimePagerAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.

    companion object {
        const val BOOKING_KEY = "showBookingPage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BookingViewModel::class.javaObjectType]
        viewModel.getAllTables().observe(requireActivity()) { response: TableEntityResponse ->
            // TODO: пока что кол-во столов захардокожено, потом надо синхронизировать с тем, что в базе
            allTablesList.addAll(response.tables)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bookingEntityList = createBookingsList()
        binding = FragmentBookingBinding.inflate(inflater, container, false)
        bookingAdapter = BookingAdapter(
            bookingListByDate,
            bookingEntityList,
            tableList,
            this,
            requireContext(),
            parentFragmentManager,
            binding!!
        )
        timePagerAdapter = TimePagerAdapter(requireContext(), listOfTimeIntervals)
        binding!!.bookingRecyclerView.run {
            setHasFixedSize(true)
            adapter = bookingAdapter
        }
        bindTimePager()
        selectTimePagerElementByCurrentTime()
        binding!!.timePicker.setColorFilter(R.color.color_red)
        binding!!.textDate.apply {
            val mCalendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("yyyy-mm-dd")
            selectedDate = dateFormat.format(mCalendar.time)
            selectedDateAsLocalDate = LocalDate.parse(selectedDate)
            val day = mCalendar[Calendar.DAY_OF_MONTH]
            val month: String =
                mCalendar.getDisplayName(
                    Calendar.MONTH,
                    Calendar.LONG,
                    Locale.getDefault()
                ) as String
            text = "$day $month"
            setOnClickListener {
                val dpd =
                    DatePickerDialog(
                        requireContext(),
                        { _, _, monthOfYear, dayOfMonth ->
                            text = "$dayOfMonth ${listOfMonths[monthOfYear]}"
                        },
                        mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)
                    )
                dpd.show()
            }
            return binding!!.root
        }

    }

    private fun getBookingByDate() {
        viewModel.getBookingByDate(selectedDate).observe(requireActivity()) { t: BookingResponse ->
            bookingListByDate = t.bookings
            bookingListByDateUtils = bookingListByDate
            bookingAdapter.notifyDataSetChanged()
            bookingListByDate.forEach { i ->
                tableList.add(
                    TableDto(
                        findTableNameById(i.tableId),
                        i.tableId,
                        Collections.emptyList()
                    )
                )
            }
        }
    }

    private fun findTableNameById(tableId: Long): String {
        return allTablesList.find { i -> i.id == tableId }!!.name
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun createBookingsList(): ArrayList<BookingEntity> {
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

        return arrayListOf(
            booking1,
            booking2,
            booking3,
            booking4,
            booking5,
            booking6,
            booking7,
            booking8
        )
    }

    private fun bindTimePager() {
        binding!!.timePager.run {
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
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun selectTimePagerElementByCurrentTime() {
        val time = Calendar.getInstance().time.hours
//        val nextTimePagerElement =
//            listOfTimeIntervals.stream().filter { i ->  i. > time }.findFirst().get()
        binding!!.timePager.currentItem = time % listOfTimeIntervals.size - 2
    }


    override fun onEventClicked(booking: BookingEntity) {
        TODO("Not yet implemented")
    }
}