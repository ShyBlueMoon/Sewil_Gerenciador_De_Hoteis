package com.luanasilva.sewil.ui.quartoslimpezamanutencao


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * TODO: document your custom view class.
 */
class QuartosLimpezaManutencaoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui vai o fragmento de limpeza e manutenção de quartos"
    }
    val text: LiveData<String> = _text
}