package com.example.bookarchice.ui.home.booklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookarchice.R
import com.example.bookarchice.adapter.BookAdapter
import com.example.bookarchice.databinding.FragmentHomeBinding
import com.example.bookarchice.model.Book
import com.example.bookarchice.util.showSnackBar
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment(), BookAdapter.Listener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        bookAdapter = BookAdapter(this@HomeFragment)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = bookAdapter
        searchView()
        observeLiveData()

        binding.homeScreenAddButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddBookFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.homeScreenToolBar.homeToolBarProfile.setOnClickListener {
            getPopupMenu()
        }

        binding.homeScreenToolBar.homeToolBarStarBook.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSuggestionBookFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun searchView() {
        binding.homeScreenToolBar.homeToolBarSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    bookAdapter.filter.filter(newText)
                }
                return true
            }
        })
    }

    private fun observeLiveData() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                bookAdapter.updateData(it)
                binding.recyclerView.adapter = bookAdapter
                bookAdapter.notifyDataSetChanged()
            } else {
                view?.showSnackBar("BookList is null")
            }
        }
        viewModel.getBookDataFromRepository()
    }

    private fun getPopupMenu() {
        val popup = PopupMenu(requireContext(), binding.homeScreenToolBar.homeToolBarProfile)
        popup.menuInflater.inflate(R.menu.home_menu, popup.menu)
        popup.show()

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_change_password -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToChangePasswordFragment()
                    findNavController().navigate(action)
                    true
                }

                R.id.action_sign_out -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                    findNavController().navigate(action)
                    auth.signOut()
                    true
                }

                else -> false
            }
        }
    }

    override fun onItemClick(bookList: Book) {
        val action = HomeFragmentDirections.actionHomeFragmentToAddBookFragment(bookList)
        findNavController().navigate(action)
    }
}