package com.example.wb_week_four.domain

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

class Repository {

    companion object {
        const val CHATS_COUNT = 50
    }

    private var chatListLiveData: MutableLiveData<MutableList<ChatData>> =
        MutableLiveData(mutableListOf())

    fun getChats(): MutableLiveData<MutableList<ChatData>> {
        val list = mutableListOf<ChatData>()
        for (i in 1..CHATS_COUNT) {
            val messages = getMessages()
            list.add(
                ChatData(
                    i,
                    getRandomString(i),
                    getTime(),
                    getRandomCount(),
                    messages,
                    (0..1).random(),
                    getColor()
                )
            )
        }
        chatListLiveData.value?.addAll(list)
        return chatListLiveData
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(): String {
        return SimpleDateFormat("hh:${String.format("%02d", (0..59).random())}").format(Date())
    }

    private fun getRandomCount(): Int {
        return (0..50).random()
    }

    private fun getMessages(): MutableList<String> {
        val messages = mutableListOf<String>()
        for (i in 0..(0..50).random()) {
            messages.add(getRandomString((1..50).random()))
        }
        return messages
    }

    fun updateChats(): MutableLiveData<MutableList<ChatData>> {
        for (i in 1..(1..10).random()) {
            val index = (0 until CHATS_COUNT).random()
            val newMessagesCount = (0..20).random()
            for (j in 0..CHATS_COUNT / 3) {
                chatListLiveData.value?.get(index)?.messages?.add(getRandomString(getRandomCount()))
                chatListLiveData.value?.get(index)?.unreadMessages?.plus(newMessagesCount)
                chatListLiveData.value?.get(index)?.time = getTime()
            }
        }
        return chatListLiveData
    }

    private fun getColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}