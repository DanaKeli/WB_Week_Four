package com.example.wb_week_four.presentation.chatList

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wb_week_four.domain.ChatData
import com.example.wb_week_four.R
import com.example.wb_week_four.databinding.FragmentChatListBinding
import com.example.wb_week_four.presentation.chat.ChatFragment

class ChatListFragment : Fragment() {

    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!
    private lateinit var fm: FragmentManager
    private lateinit var vm: ChatListVM
    private lateinit var chatListAdapter: ChatListAdapter
    private var chatList: List<ChatData>? = listOf()

    private val onItemClick: (item: ChatData) -> Unit = {
        vm.onItemClick(it)
        fm.beginTransaction()
            .replace(R.id.main_container, ChatFragment())
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(ChatListVM::class.java)
        fm = requireActivity().supportFragmentManager
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatListAdapter = ChatListAdapter(onItemClick)
        chatList = vm.getChatList()
        chatListAdapter.submitList(chatList)

        val divider = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this.requireContext(), R.drawable.item_divider)
            ?.let { divider.setDrawable(it) }

        binding.apply {
            rvChatList.adapter = chatListAdapter
            rvChatList.layoutManager = LinearLayoutManager(context)
            rvChatList.addItemDecoration(divider)
            swipeLayout.setOnRefreshListener {
                chatList = vm.updateChats()
                chatListAdapter.submitList(chatList)
                chatListAdapter.notifyDataSetChanged()
                swipeLayout.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}