package com.palash.hall_sensor_example.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.palash.hall_sensor_example.repositories.HallSensorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HallSensorViewModel @Inject constructor(
    private val hallSensorRepository: HallSensorRepository
) : ViewModel() {

    val hallSensorData: LiveData<Float>
        get() = hallSensorRepository.hallSensorData

    override fun onCleared() {
        hallSensorRepository.unregisterSensorListener()
        super.onCleared()
    }
}