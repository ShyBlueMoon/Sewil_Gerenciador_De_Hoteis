package com.luanasilva.sewil.ui.gestaofuncionarios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GestaoFuncionariosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui vai o fragmento de Gestao de Funcionarios"
    }
    val text: LiveData<String> = _text
}