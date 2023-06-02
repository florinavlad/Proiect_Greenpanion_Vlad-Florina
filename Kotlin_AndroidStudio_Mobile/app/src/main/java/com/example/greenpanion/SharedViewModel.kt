package com.example.greenpanion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val points = MutableLiveData<Int>()

    fun calculateTotalPoints(totalPoints: Int) {
        points.value = totalPoints
    }
}