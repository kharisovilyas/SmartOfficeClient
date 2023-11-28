package com.smartoffice.client.ui.main_ui.home.inform_dialog

import androidx.fragment.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smartoffice.client.api.model.control.LightingControlRemoteRequest
import com.smartoffice.client.databinding.DialogUpdateLightingBinding
import com.smartoffice.client.ui.main_ui.viewmodel.control.UpdateHVACViewModel
import com.smartoffice.client.ui.main_ui.viewmodel.control.UpdateLightingViewModel

class UpdateLightingDialog(
    private val lightingName: String
) : DialogFragment() {
    private lateinit var binding: DialogUpdateLightingBinding
    private lateinit var viewModel: UpdateLightingViewModel

    interface OnUpdateLightingListener {
        fun onUpdateLighting(brightness: Int, temperature: Int)
    }

    private var onUpdateLightingListener: OnUpdateLightingListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogUpdateLightingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[UpdateLightingViewModel::class.java]
        viewModel.fetchLightingData(lightingName)

        // Наблюдайте за LiveData в ViewModel и обновляйте соответствующие TextView
        viewModel.lightingData.observe(viewLifecycleOwner) { lightingData ->
            binding.brightnessSeekBar.progress = lightingData.brightness
            binding.temperatureSeekBar.progress = lightingData.colorTemperature
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmButton.setOnClickListener {
            val newBrightness = binding.brightnessSeekBar.progress
            val newTemperature = binding.temperatureSeekBar.progress

            updateRequestLightingData(newBrightness, newTemperature)
            onUpdateLightingListener?.onUpdateLighting(newBrightness, newTemperature)
            dismiss()
        }
    }

    private fun updateRequestLightingData(newBrightness: Int, newTemperature: Int) {
        viewModel.updateLightingData(
            LightingControlRemoteRequest(
                controlName = lightingName,
                brightness = newBrightness,
                colorTemperature = newTemperature
            )
        )
    }
}
