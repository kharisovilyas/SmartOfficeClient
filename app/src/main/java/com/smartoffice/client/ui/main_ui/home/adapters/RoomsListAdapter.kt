package com.smartoffice.client.ui.main_ui.home.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.smartoffice.client.R
import com.smartoffice.client.api.model.room.RoomsResponse
import com.smartoffice.client.databinding.RoomItemBinding
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class RoomsListAdapter(
    private var items: List<RoomsResponse>,
    private val onRoomListener: OnRoomListener
) : RecyclerView.Adapter<RoomsListAdapter.RoomsHolder>() {

    class RoomsHolder(private val binding: RoomItemBinding, onRoomListener: OnRoomListener)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener
    {
        lateinit var data: RoomsResponse
        private val _onGroupListener: OnRoomListener = onRoomListener

        @SuppressLint("SetTextI18n")
        fun bind(data: RoomsResponse){
            this.data = data
            binding.roomNameTextView.text = data.roomName
            binding.floorTextView.text = "Этаж №${data.flor}"
            binding.capacityTextView.text = "Вметимость - ${data.capacity} человек"
            binding.workloadTextView.text = "Сейчас в кабинете ${data.workload} человек"
            binding.timeTextView.text = "dd.MM.yyyy HH:mm"
            binding.updateTimeTextView.text = "Дата обновления:"
        }

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            _onGroupListener.onRoomClick(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsHolder {
        return RoomsHolder(
            RoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onRoomListener
        )
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RoomsHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: LocalDateTime): String? {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return sdf.format(date)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItemsInStart(response: List<RoomsResponse>) {
        items = response.reversed()
        notifyDataSetChanged()
    }
}
interface OnRoomListener {
    fun onRoomClick(data: RoomsResponse?)
}