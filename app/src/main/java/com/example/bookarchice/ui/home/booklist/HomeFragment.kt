package com.example.bookarchice.ui.home.booklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookarchice.R
import com.example.bookarchice.adapter.BookAdapter
import com.example.bookarchice.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        bookAdapter = BookAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = bookAdapter
        observeLiveData()

        binding.homeScreenAddButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddBookFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.homeScreenToolBar.homeToolBarProfile.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToChangePasswordFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun observeLiveData() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                bookAdapter = BookAdapter(it)
                binding.recyclerView.adapter = bookAdapter
                bookAdapter.notifyDataSetChanged()
            } else {
                Snackbar.make(requireView(), "BookList is null", Snackbar.LENGTH_LONG).show()
            }
        }
        viewModel.getBookDataFromRepository()
    }

}