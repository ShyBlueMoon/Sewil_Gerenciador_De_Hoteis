package com.luanasilva.sewil.database

import com.luanasilva.sewil.database.model.AreasManutencao
import com.luanasilva.sewil.database.model.LimpezaDeQuartos

interface ILimpezasDAO {

    fun salvar(limpeza: LimpezaDeQuartos):Boolean
    fun remover(idQuartoNumero: Int): Boolean
    fun listar(): List<LimpezaDeQuartos>
}