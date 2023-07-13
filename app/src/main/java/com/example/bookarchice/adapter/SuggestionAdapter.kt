package com.example.bookarchice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookarchice.R
import com.example.bookarchice.model.SuggestionBook

class SuggestionAdapter(private val suggestionList: List<SuggestionBook>) :
    RecyclerView.Adapter<SuggestionAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.suggestion_recycler_item_book_name)
        var author: TextView = view.findViewById(R.id.suggestion_recycler_item_book_author)
        var subject: TextView = view.findViewById(R.id.suggestion_recycler_item_book_subject)
        var type: TextView = view.findViewById(R.id.suggestion_recycler_item_book_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.suggestion_recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return suggestionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val suggestion = suggestionList[position]
        holder.name.text = suggestion.name
        holder.author.text = suggestion.author
        holder.subject.text = suggestion.subject
        holder.type.text = suggestion.type
    }
}