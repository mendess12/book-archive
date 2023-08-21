package com.example.bookarchice.ui.auth.login

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentLoginBinding
import com.example.bookarchice.util.showSnackBar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        if (auth.currentUser != null) {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            findNavController().navigate(action)
        }
        setOnClickMethod()
        observeLiveData()
    }

    private fun setOnClickMethod() {
        binding.apply {
            // TODO ayri bir method -> yapıldı
            loginScreenRegisterTv.setOnClickListener {
                navigateToRegister()
            }

            // TODO ayri bir method -> yapıldı
            loginScreenForgotPasswordTv.setOnClickListener {
                navigateToForgotPassword()
            }

            // TODO click listeneri ayri bir methoda alalim -> yapıldı
            loginScreenLoginButton.setOnClickListener {
                loginButton()
            }
        }
    }

    private fun navigateToRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

    private fun navigateToForgotPassword() {
        val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigate(action)
    }

    private fun loginButton() {
        val email = binding.loginScreenEmailEt.text.toString().trim()
        val password = binding.loginScreenPasswordEt.text.toString().trim()

        if (isEligibleToLogin(binding, email, password)) {
            viewModel.getLoginDataFromRepository(email, password)
        }
    }

    private fun isEligibleToLogin(
        binding: FragmentLoginBinding,
        email: String,
        password: String
    ): Boolean {
        if (email.isEmpty()) {
            binding.loginScreenEmailEt.error = "Email required"
            binding.loginScreenEmailEt.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.loginScreenEmailEt.error = "Valid email required"
            binding.loginScreenEmailEt.requestFocus()
            return false
        }
        if (password.isEmpty() || password.length < 6) {
            binding.loginScreenPasswordEt.error = "6 char password required"
            binding.loginScreenPasswordEt.requestFocus()
            return false
        }

        return true
    }

    private fun observeLiveData() {
        viewModel.loginLiveData.observe(viewLifecycleOwner) {
            Log.e(TAG, "Result = $it")
            if (it != null) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                view?.showSnackBar("Check your email and password!")
            }
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}