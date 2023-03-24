package com.ru.alferatz.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ru.alferatz.R
import com.ru.alferatz.activityBinding
import com.ru.alferatz.databinding.FragmentAuthBinding
import com.ru.alferatz.model.request.AuthRequest
import com.ru.alferatz.model.response.AuthResponse
import com.ru.alferatz.viewmodel.AuthViewModel
import com.ru.alferatz.viewmodel.CurrentBookingViewModel
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: AuthViewModel

//    private var storedVerificationId: String? = ""
//    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
//    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private val binding get() = _binding!!

    companion object {
        private const val TAG = "AuthFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.javaObjectType]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        auth = Firebase.auth
//        auth.setLanguageCode("ru")
//        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                Log.d(TAG, "onVerificationCompleted:$credential")
//                signInWithPhoneAuthCredential(credential)
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                Log.w(TAG, "onVerificationFailed", e)
//
//                if (e is FirebaseAuthInvalidCredentialsException) {
//                    // Invalid request
//                } else if (e is FirebaseTooManyRequestsException) {
//                    // The SMS quota for the project has been exceeded
//                }
//
//                // Show a message and update the UI
//            }
//
//            override fun onCodeSent(
//                verificationId: String,
//                token: PhoneAuthProvider.ForceResendingToken
//            ) {
//                // The SMS verification code has been sent to the provided phone number, we
//                // now need to ask the user to enter the code and then construct a credential
//                // by combining the code with a verification ID.
//                Log.d(TAG, "onCodeSent:$verificationId")
//
//                // Save verification ID and resending token so we can use them later
//                storedVerificationId = verificationId
//                resendToken = token
//                val bundle = bundleOf("verificationId" to storedVerificationId)
//                //findNavController().navigate(R.id.action_AuthFragment_to_confirmFragment, bundle)
//            }
//        }
        activityBinding.bottomNav.visibility = View.INVISIBLE
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        binding.apply {
            buttonSend.setOnClickListener {
                var phoneNumber = inputPhoneNumber.text.toString()
                val name = inputName.text.toString()
                if (phoneNumber.length == 11 && phoneNumber.startsWith("8")) {
                    phoneNumber = phoneNumber.replaceFirst("8", "+7")
                } else if ((phoneNumber.length != 12) && phoneNumber.startsWith("+7")
                    || (phoneNumber.length != 11) && phoneNumber.startsWith("8")
                ) {
                    Toast.makeText(context, "Неправильный формат номера", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                if (name.isEmpty() || name.isBlank()) {
                    Toast.makeText(context, "Имя не может быть пустым", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                val numberWithoutCode = phoneNumber.substringAfter("+7")
                val operatorCode = numberWithoutCode.substring(0, 3)
                var number = numberWithoutCode.substring(3, 10)
                number =
                    "${number.substring(0, 3)}-${number.substring(3, 5)}-${number.substring(5, 7)}"
                phoneNumber = "+7 $operatorCode $number"
                startPhoneNumberVerification(phoneNumber, name)
            }
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startPhoneNumberVerification(phoneNumber: String, name: String) {
        viewModel.login(AuthRequest(phoneNumber, "USER", name, 0L))
            .observe(requireActivity()) { response: AuthResponse ->
                response.let {
                    if (response.status == "SUCCESS") {
                        val fragment = ConfirmFragment()
                        val bundle =
                            bundleOf(
                                "CODE" to response.code,
                                "USER_ID" to response.userId,
                            )
                        fragment.arguments = bundle
                        parentFragmentManager.beginTransaction()
                            .replace(
                                (binding.root.parent as View).id,
                                fragment
                            )
                            .addToBackStack(null).commit()
                    } else {
                        Toast.makeText(
                            context,
                            "Что-то пошло не так",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.run {
                    Toast.makeText(
                        context,
                        "Не удалось отправить код подтверждения",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@observe
                }
            }
    }

//    private fun startPhoneNumberVerification(phoneNumber: String) {
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)       // Phone number to verify
//            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//            .setActivity(requireActivity())                 // Activity (for callback binding)
//            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
//            .build()
//        PhoneAuthProvider.verifyPhoneNumber(options)
//    }

//    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String): PhoneAuthCredential{
//        // [START verify_with_code]
//        return PhoneAuthProvider.getCredential(verificationId!!, code)
//        // [END verify_with_code]
//    }

    // [START resend_verification]
//    private fun resendVerificationCode(
//        phoneNumber: String,
//        token: PhoneAuthProvider.ForceResendingToken?
//    ) {
//        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)       // Phone number to verify
//            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//            .setActivity(requireActivity())                 // Activity (for callback binding)
//            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
//        if (token != null) {
//            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
//        }
//        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
//    }
    // [END resend_verification]

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

//    private fun updateUI(user: FirebaseUser? = auth.currentUser) {
//
//    }
}