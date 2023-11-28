package com.smartoffice.client.ui.main_ui.home.inform_dialog

import android.app.Dialog
import androidx.fragment.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smartoffice.client.api.model.control.HVACControlRemoteRequest
import com.smartoffice.client.databinding.DialogUpdateHvacBinding
import com.smartoffice.client.ui.main_ui.viewmodel.control.UpdateHVACViewModel

class UpdateHVACDialog(
    private val hvacName: String
) : DialogFragment() {
    private lateinit var binding: DialogUpdateHvacBinding
    private lateinit var viewModel: UpdateHVACViewModel

    interface OnUpdateHVACListener {
        fun onUpdateHVAC(temperature: Int, humidity: Int)
    }

    private var onUpdateHVACListener: OnUpdateHVACListener? = null

    fun setOnUpdateHVACListener(listener: OnUpdateHVACListener) {
        onUpdateHVACListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogUpdateHvacBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[UpdateHVACViewModel::class.java]
        viewModel.fetchHvacData(hvacName)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.hvacData.observe(viewLifecycleOwner){
            binding.temperaturePicker.minValue = 0
            binding.temperaturePicker.maxValue = 100
            binding.temperaturePicker.value = it.temperature.toInt()
            binding.humidityPicker.minValue = 0
            binding.humidityPicker.maxValue = 100
            binding.humidityPicker.value = it.humidity.toInt()
        }

        binding.confirmButton.setOnClickListener {
            val newTemperature = binding.temperaturePicker.value
            val newHumidity = binding.humidityPicker.value
            updateRequestHVACData(newTemperature, newHumidity)
            onUpdateHVACListener?.onUpdateHVAC(newTemperature, newHumidity)
            dismiss()
        }
    }

    private fun updateRequestHVACData(newTemperature: Int, newHumidity: Int) {
        viewModel.updateHvacData(HVACControlRemoteRequest(
            roomName = "Cabinet #3",
            temperature = newTemperature.toDouble(),
            humidity = newHumidity.toDouble()
        ))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(false)
        }
    }
}
