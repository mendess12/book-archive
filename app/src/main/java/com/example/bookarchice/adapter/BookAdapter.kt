package com.example.bookarchice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookarchice.R
import com.example.bookarchice.model.Book
import java.util.Locale

class BookAdapter(private val listener: Listener) :
    RecyclerView.Adapter<BookAdapter.MyViewHolder>(), Filterable {

    var bookList: MutableList<Book> = arrayListOf()
    var filterList: MutableList<Book> = arrayListOf()

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
        return this.filterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = this.filterList[position]
        holder.bind(book, listener)
        holder.bookName.text = book.bookName
        holder.bookAuthor.text = book.bookAuthor
        holder.itemView.setOnClickListener {
            listener.onItemClick(book)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                filterList = results.values as MutableList<Book>
                notifyDataSetChanged()
            }

            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredResults: List<Book?> = if (constraint.isEmpty()) {
                    this@BookAdapter.bookList
                } else {
                    getFilteredResults(constraint.toString().lowercase(Locale.getDefault()))
                }
                val results = FilterResults()
                results.values = filteredResults
                return results
            }
        }
    }

    private fun getFilteredResults(constraint: String?): List<Book> {
        val results: MutableList<Book> = ArrayList()
        for (item in this.bookList) {
            if (item.bookName!!.lowercase().contains(constraint!!)) {
                results.add(item)
            }
        }
        return results
    }

    fun updateData(book: List<Book>) {
        filterList.clear()
        filterList.addAll(book)
        this.bookList.clear()
        this.bookList.addAll(book)
        notifyDataSetChanged()
    }
}