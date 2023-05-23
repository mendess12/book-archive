package com.example.bookarchice.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.loginScreenRegisterTv.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.loginScreenForgotPasswordTv.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.loginScreenLoginButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

}