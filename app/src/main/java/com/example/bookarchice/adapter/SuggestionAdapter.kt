package com.example.bookarchice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookarchice.databinding.SuggestionRecyclerItemBinding
import com.example.bookarchice.model.SuggestionBook

class SuggestionAdapter(private val suggestionList: List<SuggestionBook>) :
    RecyclerView.Adapter<SuggestionAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: SuggestionRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SuggestionRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return suggestionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val suggestion = suggestionList[position]
        val binding = holder.binding
        binding.suggestionRecyclerItemBookName.text = suggestion.name
        binding.suggestionRecyclerItemBookAuthor.text = suggestion.author
        binding.suggestionRecyclerItemBookSubject.text = suggestion.subject
        binding.suggestionRecyclerItemBookType.text = suggestion.type
    }
}