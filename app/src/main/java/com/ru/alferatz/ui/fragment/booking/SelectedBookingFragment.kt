package com.ru.alferatz.ui.fragment.booking

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.ru.alferatz.adapter.TimeTableAdapter
import com.ru.alferatz.databinding.FragmentSelectedBookingBinding
import com.ru.alferatz.model.dto.TableDto
import com.ru.alferatz.model.request.AvailableTablesRequest
import com.ru.alferatz.model.request.CreateBookingRequest
import com.ru.alferatz.model.response.CreateBookingResponse
import com.ru.alferatz.model.response.TableWithSlotsResponse
import com.ru.alferatz.selectedDate
import com.ru.alferatz.selectedDateAsLocalDate
import com.ru.alferatz.selectedTime
import com.ru.alferatz.userPhoneNumber
import com.ru.alferatz.viewmodel.SelectedBookingViewModel
import java.time.LocalDate
import java.time.LocalDateTime


class SelectedBookingFragment : Fragment() {
    private lateinit var viewModel: SelectedBookingViewModel
    private var binding: FragmentSelectedBookingBinding? = null
    private var pictureId: Int = 0
    private var tableCapacity: Long = 0L
    private var tableName: String = ""
    private val listOfTimeIntervals = listOf(
        "00:30:00",
        "01:00:00",
        "01:30:00",
        "02:00:00",
        "02:30:00",
        "03:00:00",
        "03:30:00",
        "04:00:00",
        "04:30:00",
        "05:00:00",
        "05:30:00",
        "06:30:00",
        "07:00:00",
        "07:30:00",
        "08:00:00",
        "08:30:00",
        "09:00:00",
        "10:30:00",
        "11:00:00",
        "11:30:00",
        "12:00:00",
        "12:30:00",
        "13:00:00",
        "13:30:00",
        "14:00:00",
        "14:30:00",
        "15:00:00",
        "15:30:00",
        "16:00:00",
        "16:30:00",
        "17:00:00",
        "17:30:00",
        "18:00:00",
        "18:30:00",
        "19:00:00",
        "19:30:00",
        "20:00:00",
        "20:30:00",
        "21:00:00",
        "21:30:00",
        "22:00:00",
        "22:30:00",
        "23:00:00",
        "23:30:00",
    )
    private val listOfAvailableTables: ArrayList<TableDto> = ArrayList()
    private val listOfAvailableTimeIntervals = ArrayList<String>()
    private val listOfTableImages = listOf(
        com.ru.alferatz.R.drawable.table_1,
        com.ru.alferatz.R.drawable.table_2,
        com.ru.alferatz.R.drawable.table_3,
        com.ru.alferatz.R.drawable.table_4,
        com.ru.alferatz.R.drawable.table_5,
        com.ru.alferatz.R.drawable.table_7,
        com.ru.alferatz.R.drawable.table_8,
        com.ru.alferatz.R.drawable.table_9
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            pictureId = requireArguments().getInt("PICTURE_ID")
            tableCapacity = requireArguments().getLong("TABLE_CAPACITY")
            tableName = requireArguments().getString("TABLE_NAME").toString()
        }
        viewModel = ViewModelProvider(this)[SelectedBookingViewModel::class.javaObjectType]
        val request = AvailableTablesRequest(selectedDateAsLocalDate, tableCapacity)
        viewModel.getAvailableTables(tableCapacity, selectedDateAsLocalDate)
            .observe(requireActivity()) { response: TableWithSlotsResponse ->
                val availableTablesForCurrentTable: ArrayList<TableDto> =
                    response.tables.filter { i -> i.name == tableName } as ArrayList<TableDto>
                listOfAvailableTables.addAll(availableTablesForCurrentTable)

                //TODO: Добавить конвертацию из даты в строку
                //listOfAvailableTimeIntervals.add(listOfAvailableTables.get(0).availableStartTimes)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSelectedBookingBinding.inflate(inflater, container, false)
        createBindings()
        setUpPicture()
        return binding!!.root
    }

    private fun setUpPicture() {
        Glide.with(requireContext()).load(
            ContextCompat.getDrawable(
                requireContext(),
                listOfTableImages[pictureId]
            )
        )
            //.optionalFitCenter() // scale image to fill the entire ImageView
            .transform(GranularRoundedCorners(0F, 0F, 25F, 25F))
            .into(binding!!.selectedTableImage)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createBindings() {
        val timeAdapter =
            TimeTableAdapter(
                requireContext(),
                listOfTimeIntervals,
                listOfAvailableTimeIntervals,
                binding!!
            )
        binding!!.timeRecycler.run {
            setHasFixedSize(true)
            adapter = timeAdapter
        }
        binding!!.commentContainer.visibility = View.INVISIBLE
        binding!!.imageBack.setOnClickListener {
            selectedTime = ""
            if (parentFragmentManager.backStackEntryCount > 0) {
                parentFragmentManager.popBackStackImmediate()
            }
        }
        binding!!.buttonCancel.setOnClickListener {
            binding!!.apply {
                commentContainer.visibility = View.INVISIBLE
                selectedTime = ""
            }
        }
        binding!!.apply {
            buttonOk.setOnClickListener {
                //TODO: валидацию количества участников
                val userName = nameEditText.text.toString()
                val phone = userPhoneNumber
                val participants =
                    if (participantsCount.text.toString() != "") participantsCount.text.toString()
                        .toLong() else 0
                val tableName = "Стол ${pictureId + 1}"

                // TODO: заменить на selectedDate
                val strDatewithTime = "${LocalDate.now()}T${selectedTime}"

                val timeFrom = LocalDateTime.parse(strDatewithTime)
                val comment = editTextComment.text.toString()
                val createBookingRequest: CreateBookingRequest = CreateBookingRequest(
                    userName,
                    phone,
                    participants,
                    tableName,
                    timeFrom,
                    comment
                )
                if (participantsCount.text.toString().isBlank() || participantsCount.text.toString()
                        .isEmpty() ||
                    nameEditText.text.toString().isBlank() || nameEditText.text.toString().isEmpty()
                ) {
                    Toast.makeText(context, "Не все обязательные поля заполнены", Toast.LENGTH_LONG)
                        .show()
                    return@setOnClickListener
                }
                var checkResponse = false
                viewModel.createBooking(createBookingRequest)
                    .observe(requireActivity()) { response: CreateBookingResponse? ->
                        response?.let {
                            Toast.makeText(
                                context,
                                "Бронирование успешно создано!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } ?: run {
                            checkResponse = true
                        }
                    }
                if (checkResponse) {
                    Toast.makeText(
                        context,
                        "Возникли проблемы во время бронирования",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                binding!!.apply {
                    commentContainer.visibility = View.INVISIBLE
                    selectedTime = ""
                }
            }
        }
    }

}