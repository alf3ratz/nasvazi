package com.ru.alferatz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ru.alferatz.databinding.FragmentAuthBinding
import com.ru.alferatz.databinding.FragmentConfirmBinding


class ConfirmFragment : Fragment() {

    private var verificationId: String? = ""
    private var _binding: FragmentConfirmBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val TAG = "ConfirmFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        verificationId = arguments?.getString("verificationId")
        Log.i("auth_user", verificationId.toString())
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
            val credentials = verifyPhoneNumberWithCode(verificationId, code)
            signInWithPhoneAuthCredential(credentials)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                    findNavController().navigate(R.id.action_confirmFragment_to_MainFragment)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(context, "Введен неверный код", Toast.LENGTH_LONG).show()
                    }
                    // Update UI
                }
            }
    }

    private fun verifyPhoneNumberWithCode(
        verificationId: String?,
        code: String
    ): PhoneAuthCredential {
        // [START verify_with_code]
        return PhoneAuthProvider.getCredential(verificationId!!, code)
        // [END verify_with_code]
    }
}