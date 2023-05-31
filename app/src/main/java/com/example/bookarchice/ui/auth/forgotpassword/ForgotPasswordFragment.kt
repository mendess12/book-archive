package com.example.bookarchice.ui.auth.forgotpassword

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentForgotPasswordBinding
import com.example.bookarchice.util.showSnackBar

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPasswordBinding.bind(view)

        setOnClickMethod()
    }

    private fun setOnClickMethod() {
        binding.apply {
            forgotPasswordScreenToolBar.backToolBar.setOnClickListener {
                findNavController().popBackStack()
            }
            forgotPasswordScreenForgotPasswordButton.setOnClickListener {
                viewModel.email = binding.forgotPasswordScreenEmailEt.text.toString().trim()

                if (viewModel.email.isEmpty()) {
                    binding.forgotPasswordScreenEmailEt.error = "Email required"
                    binding.forgotPasswordScreenEmailEt.requestFocus()
                    return@setOnClickListener
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(viewModel.email).matches()) {
                    binding.forgotPasswordScreenEmailEt.error = "Valid email required"
                    binding.forgotPasswordScreenEmailEt.requestFocus()
                    return@setOnClickListener
                }
                observeLiveData()
            }
        }
    }

    private fun observeLiveData() {
        viewModel.forgotPasswordLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                view?.showSnackBar("Link sent to ${viewModel.email} this email. Please check your email!")
                val action =
                    ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
                findNavController().navigate(action)
            } else {
                view?.showSnackBar("Check your email")
            }
        }
        viewModel.getForgotPasswordDataFromRepository()
    }
}