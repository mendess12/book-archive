package com.example.bookarchice.ui.auth.forgotpassword

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentForgotPasswordBinding
import com.example.bookarchice.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewModel: ForgotPasswordViewModel by viewModels()
    private lateinit var email: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPasswordBinding.bind(view)

        setOnClickMethod()
        observeLiveData()
    }

    private fun setOnClickMethod() {
        binding.apply {
            forgotPasswordScreenToolBar.backToolBar.setOnClickListener {
                navigateToBack()
            }
            forgotPasswordScreenForgotPasswordButton.setOnClickListener {
                onPasswordForgot()
            }
        }
    }

    private fun navigateToBack() {
        findNavController().popBackStack()
    }

    private fun onPasswordForgot() {
        email = binding.forgotPasswordScreenEmailEt.text.toString().trim()

        if (isEligibleToForgotPassword(binding, email)) {
            viewModel.getForgotPasswordDataFromRepository(email)
        }
    }

    private fun isEligibleToForgotPassword(
        binding: FragmentForgotPasswordBinding,
        email: String
    ): Boolean {
        if (email.isEmpty()) {
            binding.forgotPasswordScreenEmailEt.error = "Email required"
            binding.forgotPasswordScreenEmailEt.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.forgotPasswordScreenEmailEt.error = "Valid email required"
            binding.forgotPasswordScreenEmailEt.requestFocus()
            return false
        }
        return true
    }

    private fun observeLiveData() {
        viewModel.forgotPasswordLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                view?.showSnackBar("Link sent to $email this email. Please check your email!")
                findNavController().popBackStack()
            } else {
                view?.showSnackBar("Check your email")
            }
        }
    }
}