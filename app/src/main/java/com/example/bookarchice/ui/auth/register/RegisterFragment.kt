package com.example.bookarchice.ui.auth.register

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
    }

    private fun setOnClickMethod() {
        binding.apply {
            registerScreenLoginTv.setOnClickListener {
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                Navigation.findNavController(it).navigate(action)
            }
            registerScreenRegisterButton.setOnClickListener {
                viewModel.email = binding.registerScreenEmailEt.text.toString().trim()
                viewModel.password = binding.registerScreenPasswordEt.text.toString().trim()
                viewModel.userName = binding.registerScreenUserNameEt.text.toString().trim()

                if (viewModel.email.isEmpty()) {
                    binding.registerScreenEmailEt.error = "Email required"
                    binding.registerScreenEmailEt.requestFocus()
                    return@setOnClickListener
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(viewModel.email).matches()) {
                    binding.registerScreenEmailEt.error = "Valid email required"
                    binding.registerScreenEmailEt.requestFocus()
                    return@setOnClickListener
                }
                if (viewModel.password.isEmpty() || viewModel.password.length < 6) {
                    binding.registerScreenPasswordEt.error = "6 char password required"
                    binding.registerScreenPasswordEt.requestFocus()
                    return@setOnClickListener
                }
                if (viewModel.userName.isEmpty()) {
                    binding.registerScreenUserNameEt.error = "User name required"
                    binding.registerScreenUserNameEt.requestFocus()
                    return@setOnClickListener
                }
                observeLiveData()
            }
        }
    }

    private fun observeLiveData() {
        viewModel.registerLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                findNavController().navigate(action)
            } else {
                view?.showSnackBar("Check your email, password and user name!")
            }
        }
        viewModel.getRegisterDataFromRepository()
    }
}