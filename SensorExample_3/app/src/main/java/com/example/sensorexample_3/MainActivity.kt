package com.example.sensorexample_3

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity(), SensorEventListener {
    var sensorManager: SensorManager? = null
    var sensor: Sensor? = null
    var foco: Boolean = true
    var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this, sensor)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0]>1000) {
            if (foco){
                imageView?.setImageResource(R.drawable.apagado)
                foco = false
            } else {
                return
            }
        } else {
            foco = true
            imageView?.setImageResource(R.drawable.encendido)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

}