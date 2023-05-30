package com.example.bookarchice.ui.home.addbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentAddBookBinding
import com.google.android.material.snackbar.Snackbar

class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val viewModel: AddBookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBookBinding.bind(view)

        binding.addBookScreenAddButton.setOnClickListener {
            setBookData()
            observeLiveData()
        }

        binding.addBookScreenToolBar.backToolBar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setBookData() {
        viewModel.apply {
            name = binding.addBookScreenBookName.text.toString()
            author = binding.addBookScreenBookAuthor.text.toString()
            pageNumber = binding.addBookScreenPageNumber.text.toString()
            type = binding.addBookScreenBookType.text.toString()
            language = binding.addBookScreenBookLanguage.text.toString()
            publisher = binding.addBookScreenBookPublisher.text.toString()
            message = binding.addBookScreenBookMessage.text.toString()
            date = binding.addBookScreenBookDate.text.toString()
        }
    }

    private fun observeLiveData() {
        viewModel.addLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val action = AddBookFragmentDirections.actionAddBookFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                Snackbar.make(
                    requireView(),
                    "Failed!",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }
        viewModel.addBookDataFromFirebase()
    }
}