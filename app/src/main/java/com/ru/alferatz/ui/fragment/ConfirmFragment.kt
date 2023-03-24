package com.ru.alferatz.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ru.alferatz.R
import com.ru.alferatz.currentUserId
import com.ru.alferatz.databinding.FragmentConfirmBinding
import com.ru.alferatz.model.request.ConfirmAuthRequest
import com.ru.alferatz.model.response.AuthResponse
import com.ru.alferatz.ui.fragment.booking.BookingFragment
import com.ru.alferatz.viewmodel.AuthViewModel
import com.ru.alferatz.viewmodel.SelectedCurrentBookingViewModel
import okhttp3.Response


class ConfirmFragment : Fragment() {

    private var verificationId: String? = ""
    private var _binding: FragmentConfirmBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private var userId: Long = 0L
    private var verificationCode: Long = 0L
    private lateinit var viewModel: AuthViewModel

    companion object {
        private const val TAG = "ConfirmFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            arguments?.let {
                verificationCode = it.getLong("CODE")
                userId = it.getLong("USER_ID")
            }
        }
        viewModel = ViewModelProvider(this)[AuthViewModel::class.javaObjectType]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonConfirm.setOnClickListener {
            val code = binding.inputCode.text.toString()
            if (code != verificationCode.toString()) {
                Toast.makeText(
                    context,
                    "Неверный код подтверждения",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            verifyPhoneNumberWithCode()
        }
    }

//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "signInWithCredential:success")
//                    val user = task.result?.user
//                    val appSettingPrefs: SharedPreferences = this.requireActivity()
//                        .getSharedPreferences("AppSettingPrefs", 0)
//                    val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
//                    sharedPrefsEdit.putBoolean("signIn", true)
//                    sharedPrefsEdit.apply()
//                    // findNavController().navigate(R.id.action_confirmFragment_to_MainFragment)
//                } else {
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
//                        Toast.makeText(context, "Введен неверный код", Toast.LENGTH_LONG).show()
//                    }
//                    // Update UI
//                }
//            }
//    }

    private fun verifyPhoneNumberWithCode() {
        viewModel.confirmAuth(ConfirmAuthRequest(userId, verificationCode))
            .observe(requireActivity()) { response: AuthResponse ->
                response.let {
                    if (response.status == "SUCCESS") {
                        currentUserId = userId
                        val appSettingPrefs: SharedPreferences = this.requireActivity()
                            .getSharedPreferences("AppSettingPrefs", 0)
                        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
                        sharedPrefsEdit.putBoolean("signIn", true)
                        sharedPrefsEdit.putLong("userId", userId)
                        sharedPrefsEdit.apply()
                        parentFragmentManager.beginTransaction()
                            .replace(
                                (binding.root.parent as View).id,
                                BookingFragment()
                            )
                            .addToBackStack(null).commit()
                    }
                }.run {
                    Toast.makeText(
                        context,
                        "Не удалось подтвердить аутентификацию",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@observe
                }
            }
    }
}