package com.palash.hall_sensor_example.repositories

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class HallSensorRepository @Inject constructor(private val sensorManager: SensorManager): SensorEventListener {

    private val _hallSensorData = MutableLiveData<Float>()
    val hallSensorData: LiveData<Float> = _hallSensorData

    private var hallSensor: Sensor? = null

    init {
        hallSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        hallSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD) {
            val magneticFieldStrength = event.values[0]
            _hallSensorData.postValue(magneticFieldStrength)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // Handle accuracy changes if needed
    }

    fun unregisterSensorListener() {
        sensorManager.unregisterListener(this)
    }
}