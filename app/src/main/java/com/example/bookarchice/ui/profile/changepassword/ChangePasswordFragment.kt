package com.example.bookarchice.ui.profile.changepassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentChangePasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordFragment : Fragment() {

    private lateinit var binding: FragmentChangePasswordBinding
    private val viewModel: ChangePasswordViewModel by viewModels()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val message = "6 char password required"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChangePasswordBinding.bind(view)


        binding.changePasswordScreenChangeButton.setOnClickListener {
            viewModel.password = binding.changePasswordScreenPasswordEt.text.toString().trim()
            viewModel.newPassword = binding.changePasswordScreenNewPasswordEt.text.toString().trim()
            val retypeNewPassword =
                binding.changePasswordScreenRetypeNewPasswordEt.text.toString().trim()

            if (viewModel.password.isEmpty() || viewModel.password.length < 6) {
                binding.changePasswordScreenPasswordEt.error = message
                binding.changePasswordScreenPasswordEt.requestFocus()
                return@setOnClickListener
            }
            if (viewModel.newPassword.isEmpty() || viewModel.newPassword.length < 6) {
                binding.changePasswordScreenNewPasswordEt.error = message
                binding.changePasswordScreenNewPasswordEt.requestFocus()
                return@setOnClickListener
            }
            if (retypeNewPassword.isEmpty() || retypeNewPassword.length < 6) {
                binding.changePasswordScreenRetypeNewPasswordEt.error = message
                binding.changePasswordScreenRetypeNewPasswordEt.requestFocus()
                return@setOnClickListener
            }
            if (viewModel.newPassword != retypeNewPassword) {
                Snackbar.make(
                    requireView(),
                    "New password and retype new password not same!",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            viewModel.getChangePasswordDataFromRepository()
            observeLiveData()
        }

        binding.changePasswordScreenToolBar.backToolBar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeLiveData() {
        viewModel.changePasswordLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val action =
                    ChangePasswordFragmentDirections.actionChangePasswordFragmentToLoginFragment()
                findNavController().navigate(action)
                auth.signOut()
            } else {
                Snackbar.make(
                    requireView(),
                    "Check your password,new password and retype new password",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}