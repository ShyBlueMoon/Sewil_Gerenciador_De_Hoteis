package com.luanasilva.sewil.ui.agendamentomanutencao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AgendamentoManutencaoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is a agendamento manutenção geral"
    }
    val text: LiveData<String> = _text
}