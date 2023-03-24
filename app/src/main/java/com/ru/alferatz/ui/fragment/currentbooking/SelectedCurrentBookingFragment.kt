package com.ru.alferatz.ui.fragment.currentbooking

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.ru.alferatz.databinding.FragmentSelectedCurrentBookingBinding
import com.ru.alferatz.enums.CancelStatus
import com.ru.alferatz.model.request.CancelBookingRequest
import com.ru.alferatz.model.response.CancelBookingResponse
import com.ru.alferatz.viewmodel.SelectedCurrentBookingViewModel


class SelectedCurrentBookingFragment : Fragment() {

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
    private lateinit var viewModel: SelectedCurrentBookingViewModel
    private var binding: FragmentSelectedCurrentBookingBinding? = null
    private var pictureId: Int = 0
    private var tableCapacity: Long = 0L
    private var tableName: String = ""
    private var tableTime: String = ""
    private var bookingId: Long = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            arguments?.let {
                pictureId = it.getInt("PICTURE_ID")
                tableCapacity = it.getLong("TABLE_CAPACITY")
                tableName = it.getString("TABLE_NAME").toString()
                tableTime = it.getString("TABLE_TIME").toString()
                bookingId = it.getLong("BOOKING_ID")
            }
        }
        viewModel = ViewModelProvider(this)[SelectedCurrentBookingViewModel::class.javaObjectType]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectedCurrentBookingBinding.inflate(inflater, container, false)
        createBindings()
        setUpPicture()
        return binding!!.root
    }

    private fun createBindings() {
        binding!!.apply {
            imageBack.setOnClickListener {
                if (parentFragmentManager.backStackEntryCount > 0) {
                    parentFragmentManager.popBackStackImmediate()
                }
            }
            buttonCancel.setOnClickListener {
                showDialog()
            }
            dateText.text = dateText.text.toString() + tableTime.substringBefore('-')
            timeText.text = timeText.text.toString() + tableTime.substringAfter('-')
        }
    }

    private fun setUpPicture() {
        Glide.with(requireContext()).load(
            ContextCompat.getDrawable(
                requireContext(),
                listOfTableImages[pictureId]
            )
        )
            .transform(GranularRoundedCorners(0F, 0F, 25F, 25F))
            .into(binding!!.selectedTableImage)
    }

    private fun showDialog() {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(context)
        builder1.setMessage("Вы точно хотите отменить бронирование?")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Да",
            DialogInterface.OnClickListener { dialog, id ->
                cancelBooking()
                dialog.cancel()
            })

        builder1.setNegativeButton(
            "Нет",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

    private fun cancelBooking() {
        viewModel.cancelBooking(CancelBookingRequest(bookingId))
            .observe(requireActivity()) { response: CancelBookingResponse ->
                response.let {
                    if (response.status.equals(CancelStatus.SUCCESS.name)) {
                        Toast.makeText(context, "Бронирование отменено", Toast.LENGTH_SHORT).show()
                    }
                }.run {
                    Toast.makeText(context, "Не удалось отменить бронирование", Toast.LENGTH_SHORT)
                        .show()
                }

            }
    }

}