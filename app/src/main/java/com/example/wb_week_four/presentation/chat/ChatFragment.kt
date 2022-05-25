package com.example.wb_week_four.presentation.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.wb_week_four.R
import com.example.wb_week_four.databinding.FragmentChatBinding
import com.example.wb_week_four.presentation.chatList.ChatListFragment
import com.example.wb_week_four.presentation.chatList.ChatListVM

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: ChatListVM
    private lateinit var fm: FragmentManager
//    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(ChatListVM::class.java)
        fm = requireActivity().supportFragmentManager
        initViews()
        return binding.root
    }

    private fun initViews() {
        val messageList = vm.getMessages()

        Log.i("dana", "$messageList")
        var chatData = vm.getChatData()
        vm.clickedItem.observe(viewLifecycleOwner) {
            chatData = it
        }
        binding.apply {
            topAppBar.title = chatData?.chatName
            topAppBar.setNavigationOnClickListener {
                fm.beginTransaction().replace(R.id.main_container, ChatListFragment()).commit()
            }
//            rvChat.adapter = chatAdapter
//            rvChat.layoutManager = LinearLayoutManager(context)
        }
//        chatAdapter = messageList?.let { ChatAdapter(it) }!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

