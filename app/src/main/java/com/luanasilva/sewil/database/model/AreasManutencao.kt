package com.luanasilva.sewil.database.model

data class AreasManutencao(
    //Classe responsável por informar os nomes das colunas da Tabela

    val idArea:Int,
    val nomeArea: String,
    val observacoesArea: String = "Nenhuma informação extra informada"

)
