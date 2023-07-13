package com.example.bookarchice.ui.home.suggestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookarchice.R
import com.example.bookarchice.adapter.SuggestionAdapter
import com.example.bookarchice.databinding.FragmentSuggestionBookBinding
import com.example.bookarchice.util.showSnackBar

class SuggestionBookFragment : Fragment() {

    private lateinit var binding: FragmentSuggestionBookBinding
    private lateinit var suggestionAdapter: SuggestionAdapter
    private val viewModel: SuggestionBookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_suggestion_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSuggestionBookBinding.bind(view)

        suggestionAdapter = SuggestionAdapter(emptyList())
        binding.suggestionHomeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.suggestionHomeRecyclerView.adapter = suggestionAdapter
        observeLiveData()

        binding.suggestionScreenToolBar.backToolBar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeLiveData() {
        viewModel.suggestionLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                suggestionAdapter = SuggestionAdapter(it)
                binding.suggestionHomeRecyclerView.adapter = suggestionAdapter
                suggestionAdapter.notifyDataSetChanged()
            } else {
                view?.showSnackBar("BookList is null")
            }
        }
        viewModel.getSuggestionBookDataFromRepository()
    }
}