package com.example.bookarchice.ui.home.booklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.homeScreenAddButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddBookFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.homeScreenToolBar.homeToolBarProfile.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToChangePasswordFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

}