package com.smartoffice.client.ui.main_ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.smartoffice.client.R
import com.smartoffice.client.api.model.room.RoomsResponse
import com.smartoffice.client.databinding.RoomsFragmentBinding
import com.smartoffice.client.ui.main_ui.home.adapters.OnRoomListener
import com.smartoffice.client.ui.main_ui.home.adapters.RoomsListAdapter
import com.smartoffice.client.ui.main_ui.viewmodel.RoomViewModel

class RoomsFragment : Fragment() {
    // Объявление переменной для ViewBinding
    private lateinit var binding: RoomsFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var roomsListAdapter: RoomsListAdapter
    //TODO: private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Инициализация ViewBinding
        binding = RoomsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //swipeRefreshLayout = binding!!.swipeRefresh
        roomsListAdapter = RoomsListAdapter(
            emptyList(),
            object : OnRoomListener{
                override fun onRoomClick(data: RoomsResponse?) {
                    println(data!!.roomName)
                    startRoomInformFragment(data)
                }
            }
        )
        getAllRooms(roomsListAdapter)
        binding.listRooms.adapter = roomsListAdapter
    }

    private fun startRoomInformFragment(data: RoomsResponse?) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, RoomInformFragment(data))
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllRooms(adapter: RoomsListAdapter) {
        val viewModel = ViewModelProvider(this)[RoomViewModel::class.java]
        viewModel.fetchRooms(adapter)
    }

}