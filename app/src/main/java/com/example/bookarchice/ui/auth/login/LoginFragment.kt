package com.example.bookarchice.ui.auth.login

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentLoginBinding
import com.example.bookarchice.util.showSnackBar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

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
            loginScreenRegisterTv.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                Navigation.findNavController(it).navigate(action)
            }
            loginScreenForgotPasswordTv.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
                Navigation.findNavController(it).navigate(action)
            }
            loginScreenLoginButton.setOnClickListener {
                val email = binding.loginScreenEmailEt.text.toString().trim()
                val password = binding.loginScreenPasswordEt.text.toString().trim()

                if (isEligibleToLogin(binding, email, password)){
                    viewModel.getLoginDataFromRepository(email, password)
                }
            }
        }
    }

    private fun isEligibleToLogin(binding: FragmentLoginBinding, email: String, password: String): Boolean{
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
            if (it != null) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                view?.showSnackBar("Check your email and password!")
            }
        }
    }
}