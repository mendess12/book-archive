package com.example.bookarchice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.bookarchice.databinding.RecyclerItemBinding
import com.example.bookarchice.model.Book
import java.util.Locale

// TODO Listener yerine nullable lambda function
class BookAdapter(private val listener: Listener) :
    RecyclerView.Adapter<BookAdapter.MyViewHolder>(), Filterable {

    var bookList: MutableList<Book> = arrayListOf()
    var filterList: MutableList<Book> = arrayListOf()

    interface Listener {
        fun onItemClick(bookList: Book)
    }

    inner class MyViewHolder(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookList: Book, listener: Listener) {
            itemView.setOnClickListener {
                listener.onItemClick(bookList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.filterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = this.filterList[position]
        val binding = holder.binding
        holder.bind(book, listener)
        binding.recyclerItemBookName.text = book.bookName
        binding.recyclerItemBookAuthor.text = book.bookAuthor
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