package com.example.wb_week_four.presentation.chatList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_week_four.domain.ChatData
import com.example.wb_week_four.databinding.ChatListItemBinding

class ChatListAdapter(private val onItemClick: (item: ChatData) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<ChatData, ChatListViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ChatListItemBinding.inflate(layoutInflater, parent, false)
        return ChatListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

class ChatListViewHolder(private val binding: ChatListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChatData, onItemClick: (item: ChatData) -> Unit) {
        binding.apply {
            tvChatName.text = item.chatName
            tvLastMessage.text = item.messages.last()
            if (item.logo == 1) {
                ivChatLogo.isVisible = true
                tvChatLogo.isVisible = false
            } else {
                ivChatLogo.isVisible = false
                tvChatLogo.isVisible = true
                tvChatLogo.text = item.chatName[0].toString().uppercase()
                tvChatLogo.setBackgroundColor(item.logoColor)
            }
            tvTime.text = item.time
            if (item.unreadMessages == 0) {
                cvUnreadMessageCount.isInvisible = true
            } else {
                tvCount.text = item.unreadMessages.toString()
            }
        }
        itemView.setOnClickListener { onItemClick(item) }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<ChatData>() {
    override fun areItemsTheSame(oldItem: ChatData, newItem: ChatData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ChatData, newItem: ChatData): Boolean =
        oldItem.chatName == newItem.chatName
}