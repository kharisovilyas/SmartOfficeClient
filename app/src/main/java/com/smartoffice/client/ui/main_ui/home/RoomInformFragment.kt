package com.smartoffice.client.ui.main_ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.smartoffice.client.api.model.room.RoomsResponse
import com.smartoffice.client.databinding.RoomFragmentBinding
import com.smartoffice.client.ui.main_ui.home.inform_dialog.UpdateHVACDialog
import com.smartoffice.client.ui.main_ui.home.inform_dialog.UpdateLightingDialog
import com.smartoffice.client.utils.QRCodeGenerator

class RoomInformFragment(private val data: RoomsResponse?) : Fragment() {
    lateinit var binding: RoomFragmentBinding


    private val CAMERA_PERMISSION_REQUEST_CODE = 100
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RoomFragmentBinding.inflate(inflater, container, false)

        binding.centerImage.setImageBitmap(
            genericQRCode()
        )

        binding.updateTemperature.setOnClickListener{
            startUpdateTempDialog(data!!)
        }

        binding.updateLighting.setOnClickListener {
            startUpdateLightingDialog(data!!)
        }

        binding.scanQRCode.setOnClickListener {
            scanQRCodeAndRequest()
        }

        return binding.root
    }

    private fun scanQRCodeAndRequest() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Разрешение уже предоставлено, начинайте работу с камерой
            startCamera()
        } else {
            // Разрешение не предоставлено, запросите у пользователя
            requestCameraPermission()
        }
    }
    private fun requestCameraPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            // Поясните пользователю, почему это разрешение необходимо
            // Можно показать диалог с объяснением
        } else {
            // Запрос разрешения
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Разрешение предоставлено, начинайте работу с камерой
                    startCamera()
                } else {
                    // Разрешение не предоставлено, обработайте ситуацию
                }
                return
            }
            // Другие requestCode, если они есть
        }
    }

    private fun startCamera() {
        // Ваш код для работы с камерой, например, запуск активности сканирования

    }
    private fun startInformOfRentalFragment() {
        TODO("Not yet implemented")
    }

    private fun startInformOfScheduleFragment() {
        TODO("Not yet implemented")
    }

    private fun startInformSmartSensorsDialog() {
        TODO("Not yet implemented")
    }

    private fun startAnotherActivity() {
        TODO("Not yet implemented")
    }

    private fun startUpdateLightingDialog(data: RoomsResponse?) {
        val updateLightingDialog = UpdateLightingDialog("Lighting 1")
        updateLightingDialog.show(requireActivity().supportFragmentManager, "UpdateLightingDialog")
    }

    private fun startUpdateTempDialog(data: RoomsResponse) {
        val updateHVACDialog = UpdateHVACDialog("HVAC Control 1")
        updateHVACDialog.setOnUpdateHVACListener(object : UpdateHVACDialog.OnUpdateHVACListener {
            override fun onUpdateHVAC(temperature: Int, humidity: Int) {
                // Обработайте новые значения температуры и влажности
                // Вы можете отправить их на сервер или выполнить другие действия
            }
        })
        updateHVACDialog.show(requireActivity().supportFragmentManager, "UpdateHVACDialog")
    }

    private fun genericQRCode(): Bitmap? {
        val uniqueData = System.currentTimeMillis().toString()
        return QRCodeGenerator.generateQRCode(uniqueData, 512, 512)
    }
}