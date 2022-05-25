package com.example.wb_week_four.presentation.chatList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wb_week_four.domain.ChatData
import com.example.wb_week_four.domain.Repository
import kotlinx.coroutines.launch

class ChatListVM : ViewModel() {

    private val repository: Repository = Repository()
    val clickedItem: MutableLiveData<ChatData> = MutableLiveData()
    private var chatList: LiveData<MutableList<ChatData>> = MutableLiveData(mutableListOf())

    fun getChatList(): List<ChatData>? {
        if (chatList.value?.isEmpty() == true || chatList.value == null) {
            viewModelScope.launch {
                chatList = repository.getChats()
            }
        }
        return chatList.value?.sortedByDescending { it.time }
    }

    fun updateChats(): List<ChatData>? {
        viewModelScope.launch {
            chatList = repository.updateChats()
        }
        return chatList.value?.sortedByDescending { it.time }
    }

    fun getMessages(): List<String>? {
        return clickedItem.value?.messages
    }

    fun onItemClick(item: ChatData) {
        clickedItem.value = item
    }

    fun getChatData(): ChatData? {
        return clickedItem.value
    }
}