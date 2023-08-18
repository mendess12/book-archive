package com.example.bookarchice.ui.home.addbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookarchice.R
import com.example.bookarchice.databinding.FragmentAddBookBinding
import com.example.bookarchice.model.Book
import com.example.bookarchice.util.showSnackBar
import com.google.firebase.auth.FirebaseAuth

class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val args: AddBookFragmentArgs by navArgs()
    private val viewModel: AddBookViewModel by viewModels()
    private var bookArgs: Book? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBookBinding.bind(view)
        bookArgs = args.book


        if (bookArgs == null) {
            binding.addBookScreenAddButton.setOnClickListener {
                setBookData()
                observeLiveData()
            }
        } else {
            binding.apply {
                binding.addBookScreenAddButton.visibility = View.INVISIBLE
                addBookScreenBookName.setText(args.book?.bookName.toString())
                binding.addBookScreenBookAuthor.setText(args.book?.bookAuthor)
                addBookScreenPageNumber.setText(args.book?.pageNumber)
                addBookScreenBookType.setText(args.book?.bookType)
                addBookScreenBookLanguage.setText(args.book?.bookLanguage)
                addBookScreenBookPublisher.setText(args.book?.bookPublisher)
                addBookScreenBookMessage.setText(args.book?.bookMessage)
                addBookScreenBookDate.setText(args.book?.bookDate)
            }
        }

        binding.addBookScreenToolBar.backToolBar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setBookData() {

        val name = binding.addBookScreenBookName.text.toString()
        val author = binding.addBookScreenBookAuthor.text.toString()
        val pageNumber = binding.addBookScreenPageNumber.text.toString()
        val type = binding.addBookScreenBookType.text.toString()
        val language = binding.addBookScreenBookLanguage.text.toString()
        val publisher = binding.addBookScreenBookPublisher.text.toString()
        val message = binding.addBookScreenBookMessage.text.toString()
        val date = binding.addBookScreenBookDate.text.toString()
        val uuid = FirebaseAuth.getInstance().currentUser?.uid

        val book =
            uuid?.let {
                Book(
                    name, author, pageNumber, type, language, publisher, message, date,
                    it
                )
            }
        if (book != null) {
            viewModel.addBookDataFromFirebase(book)
        }
    }

    private fun observeLiveData() {
        viewModel.addLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val action = AddBookFragmentDirections.actionAddBookFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                view?.showSnackBar("Failed!")
            }
        }
    }
}