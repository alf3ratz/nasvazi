package com.ru.alferatz.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ru.alferatz.R
import com.ru.alferatz.activityBinding
import com.ru.alferatz.currentUserId
import com.ru.alferatz.databinding.FragmentMainBinding
import com.ru.alferatz.databinding.FragmentUserInfoBinding
import com.ru.alferatz.model.response.AggregateBonusByUserResponse
import com.ru.alferatz.viewmodel.AuthViewModel
import com.ru.alferatz.viewmodel.UserInfoViewModel


class UserInfoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentUserInfoBinding? = null
    private lateinit var viewModel: UserInfoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        viewModel = ViewModelProvider(this)[UserInfoViewModel::class.javaObjectType]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        binding!!.apply {
            buttonExit.setOnClickListener {
                val appSettingPrefs: SharedPreferences = requireActivity()
                    .getSharedPreferences("AppSettingPrefs", 0)
                val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
                sharedPrefsEdit.putBoolean("signIn", false)
                sharedPrefsEdit.putLong("userId", 0L)
                sharedPrefsEdit.apply()
                activityBinding.bottomNav.visibility = View.INVISIBLE
                parentFragmentManager.beginTransaction()
                    .replace(
                        (binding!!.root.parent as View).id,
                        AuthFragment()
                    )
                    .addToBackStack(null).commit()
            }
            loyalityCountText.text = loyalityCountText.text.toString() + getUserBonusNumber()
        }
        return binding!!.root
    }

    private fun getUserBonusNumber(): Long {
        var userBonusNumber: Long = 0L
        viewModel.aggregateByUser()
            .observe(requireActivity()) { response: AggregateBonusByUserResponse ->
                val value =
                    response.analysisBonusDtos.find { i -> i.userId == currentUserId }
               if (value != null){
                   userBonusNumber = value.qtyAdd
               }
            }
        return userBonusNumber
    }

}