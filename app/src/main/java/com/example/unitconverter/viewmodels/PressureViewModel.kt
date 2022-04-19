package com.example.unitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitconverter.R
import java.lang.NumberFormatException

class PressureViewModel : ViewModel() {
    private val _scale: MutableLiveData<Int> = MutableLiveData(R.string.bar)

    val scale: LiveData<Int>
        get() = _scale

    fun setScale(value: Int) {
        _scale.value = value
    }

    private val _pressure: MutableLiveData<String> = MutableLiveData("")

    val pressure: LiveData<String>
        get() = _pressure

    fun getPressureAsFloat(): Float = (_pressure.value ?: "").let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setPressure(value: String) {
        _pressure.value = value
    }

    fun convert() = getPressureAsFloat().let {
        if (!it.isNaN())
            if (_scale.value == R.string.bar)
                it * 14.5038F
            else
                it / 14.5038F
        else
            Float.NaN
    }
}