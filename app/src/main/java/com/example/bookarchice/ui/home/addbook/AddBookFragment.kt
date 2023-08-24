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
import com.example.bookarchice.util.extensions.showOrHide
import com.example.bookarchice.util.showSnackBar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val args: AddBookFragmentArgs by navArgs()
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


        val book = args.book
        if (book == null) {
            binding.addBookScreenAddButton.setOnClickListener {
                setBookData()
                observeLiveData()
            }
        } else {
            binding.apply {
                with(book){
                    addBookScreenBookName.setText(bookName.toString())
                    addBookScreenBookAuthor.setText(bookAuthor)
                    addBookScreenPageNumber.setText(pageNumber)
                    addBookScreenBookType.setText(bookType)
                    addBookScreenBookLanguage.setText(bookLanguage)
                    addBookScreenBookPublisher.setText(bookPublisher)
                    addBookScreenBookMessage.setText(bookMessage)
                    addBookScreenBookDate.setText(bookDate)
                }
            }
            binding.addBookScreenAddButton.showOrHide(false)
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
        val uuid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val book = Book(name, author, pageNumber, type, language, publisher, message, date, uuid)
        viewModel.addBookDataFromFirebase(book)
    }

    private fun observeLiveData() {
        viewModel.addLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigateUp()
            } else {
                view?.showSnackBar("Failed!")
            }
        }
    }
}