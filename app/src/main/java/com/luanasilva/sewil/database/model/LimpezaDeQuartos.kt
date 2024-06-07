package com.luanasilva.sewil.database.model

data class LimpezaDeQuartos (
    val idNumeroQuarto: Int,
    val nomeTurno: String,
    val observacoesLimpezaQuartos: String = "Nenhuma informação extra informada"
)



