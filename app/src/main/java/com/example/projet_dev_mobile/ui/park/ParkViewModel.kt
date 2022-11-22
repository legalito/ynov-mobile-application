package com.example.projet_dev_mobile.ui.park

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet_dev_mobile.model.Park

class ParkViewModel : ViewModel() {

    private val _park = MutableLiveData<List<Park>>().apply {
        value = ArrayList()
    }
    val parks: MutableLiveData<List<Park>> = _park
}