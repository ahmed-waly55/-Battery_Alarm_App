package com.example.batteryalarmapp.ui.viewmodel

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BatteryViewModel(application: Application) : AndroidViewModel(application) {
    private val _isBatteryHigh = MutableLiveData<Boolean>(true)
    val isBatteryHigh: LiveData<Boolean> get() = _isBatteryHigh

    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val level = it.getIntExtra("level", 0)
                val scale = it.getIntExtra("scale", 100)
                val batteryPct = level * 100 / scale
                _isBatteryHigh.postValue(batteryPct >= 20)
            }
        }
    }

    init {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        getApplication<Application>().registerReceiver(batteryReceiver, filter)
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<Application>().unregisterReceiver(batteryReceiver)
    }
}