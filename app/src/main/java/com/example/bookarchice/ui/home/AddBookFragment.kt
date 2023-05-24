package com.example.bookarchice.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentAddBookBinding

class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBookBinding.bind(view)

        binding.addBookScreenToolBar.backToolBar.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}