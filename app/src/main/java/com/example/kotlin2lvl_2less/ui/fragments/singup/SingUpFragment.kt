package com.example.kotlin2lvl_2less.ui.fragments.singup

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.kotlin2lvl_2less.R
import com.example.kotlin2lvl_2less.databinding.FragmentSingUpBinding
import com.example.kotlin2lvl_2less.utils.PreferenceHelper
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class SingUpFragment : Fragment() {

    private lateinit var binding: FragmentSingUpBinding
    private var auth: FirebaseAuth? = null
    private var storedVerificationId: String? = ""
    private lateinit var resendingToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingUpBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() = with(binding) {
        btnGetcode.setOnClickListener {
            if (etNumber.text.isEmpty()) {
                etNumber.error = "pls number"
            } else {
                startPhoneNumberVerification(etNumber.text.toString())
                btnGetcode.isVisible = false
                btnEnter.isVisible = true
            }
        }

        btnEnter.setOnClickListener {
            if (etNumber.text.isEmpty()) {
                etNumber.error = "pls number"
            } else {
                verifyPhoneNumberWithCode(storedVerificationId, etCode.text.toString())
                val preferenceHelper = PreferenceHelper()
                preferenceHelper.unit(requireContext())
                preferenceHelper.saveRegistration = false
            }
        }
    }

    private fun singInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.noteAppFragment)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(requireContext(), "Registration is not", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        singInWithPhoneAuthCredential(credential)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private var callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            singInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.e("tag", "error")
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            storedVerificationId = verificationId
            resendingToken = token
        }
    }
}