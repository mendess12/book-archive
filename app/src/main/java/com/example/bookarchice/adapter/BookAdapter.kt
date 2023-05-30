package com.example.bookarchice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bookarchice.R
import com.example.bookarchice.model.Book

class BookAdapter(private val bookList: List<Book>) :
    RecyclerView.Adapter<BookAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var bookName: TextView = view.findViewById(R.id.recycler_item_book_name)
        var bookAuthor: TextView = view.findViewById(R.id.recycler_item_book_author)
        var pageNumber: TextView = view.findViewById(R.id.recycler_item_book_page_number)
        var bookType: TextView = view.findViewById(R.id.recycler_item_book_type)
        var bookLanguage: TextView = view.findViewById(R.id.recycler_item_book_language)
        var bookPublisher: TextView = view.findViewById(R.id.recycler_item_book_publishing_house)
        var bookMessage: TextView = view.findViewById(R.id.recycler_item_book_message)
        var bookDate: TextView = view.findViewById(R.id.recycler_item_date)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraint_one)
        var expandableLayout: ConstraintLayout = itemView.findViewById(R.id.constraint_two)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = bookList[position]
        holder.bookName.text = book.bookName
        holder.bookAuthor.text = book.bookAuthor
        holder.pageNumber.text = book.pageNumber
        holder.bookType.text = book.bookType
        holder.bookLanguage.text = book.bookLanguage
        holder.bookPublisher.text = book.bookPublisher
        holder.bookMessage.text = book.bookMessage
        holder.bookDate.text = book.bookDate

        val isExpandable = book.expandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.constraintLayout.setOnClickListener {
            isAnyItemExpanded(position)
            book.expandable = !book.expandable
            notifyItemChanged(position, Unit)
        }
    }

    private fun isAnyItemExpanded(position: Int) {
        val temp = bookList.indexOfFirst {
            it.expandable
        }
        if (temp >= 0 && temp != position) {
            bookList[temp].expandable = false
            notifyItemChanged(temp, 0)
        }
    }

}