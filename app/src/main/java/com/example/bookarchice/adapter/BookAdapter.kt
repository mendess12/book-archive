package com.example.bookarchice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookarchice.R
import com.example.bookarchice.model.Book

class BookAdapter(private val bookList: List<Book>,private val listener : Listener) :
    RecyclerView.Adapter<BookAdapter.MyViewHolder>() {

    interface Listener {
        fun onItemClick(bookList: Book)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(bookList: Book, listener: Listener) {
            itemView.setOnClickListener {
                listener.onItemClick(bookList)
            }
        }
        var bookName: TextView = view.findViewById(R.id.recycler_item_book_name)
        var bookAuthor: TextView = view.findViewById(R.id.recycler_item_book_author)
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
        holder.bind(book,listener)
        holder.bookName.text = book.bookName
        holder.bookAuthor.text = book.bookAuthor
        holder.itemView.setOnClickListener {
            listener.onItemClick(book)
        }
    }
}