package com.ru.alferatz.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ru.alferatz.R
import com.ru.alferatz.activityBinding
import com.ru.alferatz.databinding.FragmentCurrentBookingBinding
import com.ru.alferatz.databinding.FragmentInfoBinding

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
        }
        return binding!!.root
    }

}