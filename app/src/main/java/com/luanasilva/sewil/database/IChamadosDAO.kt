package com.luanasilva.sewil.database

import com.luanasilva.sewil.database.model.AreasManutencao
// Interface responsável por implementar as funções obrigatórias a classes chamados

interface IChamadosDAO {

    fun salvar(nomeArea: AreasManutencao):Boolean
    fun remover(idArea: Int): Boolean
    fun listar(): List<AreasManutencao>
}