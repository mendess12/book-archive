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
import com.example.bookarchice.util.showSnackBar
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordFragment : Fragment() {

    private lateinit var binding: FragmentChangePasswordBinding
    private val viewModel: ChangePasswordViewModel by viewModels()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

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
            val password = binding.changePasswordScreenPasswordEt.text.toString().trim()
            val newPassword = binding.changePasswordScreenNewPasswordEt.text.toString().trim()
            val retypeNewPassword =
                binding.changePasswordScreenRetypeNewPasswordEt.text.toString().trim()

            viewModel.getChangePasswordDataFromRepository(
                view,
                binding,
                password,
                newPassword,
                retypeNewPassword
            )
        }
        observeLiveData()

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
                view?.showSnackBar("Check your password,new password and retype new password")
            }
        }
    }
}