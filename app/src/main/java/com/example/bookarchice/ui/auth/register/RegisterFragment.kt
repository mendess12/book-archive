package com.example.bookarchice.ui.auth.register

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentRegisterBinding
import com.example.bookarchice.util.showSnackBar

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        setOnClickMethod()
        observeLiveData()
    }

    private fun setOnClickMethod() {
        binding.apply {
            registerScreenLoginTv.setOnClickListener {
                navigateToLogin()
            }
            registerScreenRegisterButton.setOnClickListener {
                registerButton()
            }
        }
    }

    private fun navigateToLogin() {
        findNavController().popBackStack()
    }

    private fun registerButton() {
        val email = binding.registerScreenEmailEt.text.toString().trim()
        val password = binding.registerScreenPasswordEt.text.toString().trim()
        val userName = binding.registerScreenUserNameEt.text.toString().trim()

        if (isEligibleToRegister(binding, email, password, userName)) {
            viewModel.getRegisterDataFromRepository(email, password)
        }
    }

    private fun isEligibleToRegister(
        binding: FragmentRegisterBinding,
        email: String,
        password: String,
        userName: String
    ): Boolean {
        if (email.isEmpty()) {
            binding.registerScreenEmailEt.error = "Email required"
            binding.registerScreenEmailEt.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.registerScreenEmailEt.error = "Valid email required"
            binding.registerScreenEmailEt.requestFocus()
            return false
        }
        if (password.isEmpty() || password.length < 6) {
            binding.registerScreenPasswordEt.error = "6 char password required"
            binding.registerScreenPasswordEt.requestFocus()
            return false
        }
        if (userName.isEmpty()) {
            binding.registerScreenUserNameEt.error = "User name required"
            binding.registerScreenUserNameEt.requestFocus()
            return false
        }
        return true
    }

    private fun observeLiveData() {
        viewModel.registerLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().popBackStack()
            } else {
                view?.showSnackBar("Check your email, password and user name!")
            }
        }
    }
}