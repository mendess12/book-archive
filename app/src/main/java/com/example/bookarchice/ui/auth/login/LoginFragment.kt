package com.example.bookarchice.ui.auth.login

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        setOnClickMethod()
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
                viewModel.email = binding.loginScreenEmailEt.text.toString().trim()
                viewModel.password = binding.loginScreenPasswordEt.text.toString().trim()

                if (viewModel.email.isEmpty()) {
                    binding.loginScreenEmailEt.error = "Email required"
                    binding.loginScreenEmailEt.requestFocus()
                    return@setOnClickListener
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(viewModel.email).matches()) {
                    binding.loginScreenEmailEt.error = "Valid email required"
                    binding.loginScreenEmailEt.requestFocus()
                    return@setOnClickListener
                }
                if (viewModel.password.isEmpty() || viewModel.password.length < 6) {
                    binding.loginScreenPasswordEt.error = "6 char password required"
                    binding.loginScreenPasswordEt.requestFocus()
                    return@setOnClickListener
                }
                observeLiveData()
            }
        }
    }

    private fun observeLiveData() {
        viewModel.loginLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                Navigation.findNavController(requireView()).navigate(action)
            } else {
                Snackbar.make(requireView(), "Check your email and password!", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
        viewModel.getLoginDataFromRepository()
    }
}