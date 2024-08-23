package com.example.recorderapp.recorder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.invoke.MutableCallSite

class StatusViewModel:ViewModel() {
    var isStatusDirectory : MutableLiveData <Boolean> = MutableLiveData()
    var isStatusGrabar : MutableLiveData <Boolean> = MutableLiveData()
    //crear el funcionamiento de observadores de los botones para cada accion por medio de viewModel
}