package com.example.wb_week_four.presentation.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_week_four.databinding.ChatItemBinding

class ChatAdapter(private val messageList: List<String>) : RecyclerView.Adapter<ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ChatItemBinding.inflate(layoutInflater)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int {
        return itemCount
    }
}

class ChatViewHolder(private val binding: ChatItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: String) {
        binding.apply {
            tvMessage.text = message
        }
    }
}