package com.luanasilva.sewil.database.model

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.luanasilva.sewil.database.ILimpezasDAO

class LimpezasDAO(context: Context): ILimpezasDAO {

    private val escrita = DatabaseHelperChamados(context).writableDatabase
    private val leitura = DatabaseHelperChamados(context).readableDatabase


    override fun salvar(limpeza: LimpezaDeQuartos): Boolean {
        val valores = ContentValues()
        valores.put("${DatabaseHelperChamados.ID_NUMERO_QUARTO}", limpeza.idNumeroQuarto)
        valores.put("${DatabaseHelperChamados.TURNO_LIMPEZA}", limpeza.nomeTurno)
        valores.put("${DatabaseHelperChamados.OBSERVACOES_LIMPEZA}", limpeza.observacoesLimpezaQuartos)

        try {

            escrita.insert(
                DatabaseHelperChamados.TABELA_LIMPEZA,
                null,
                valores
            )
            Log.i("{${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "LIMPEZADAO::Sucesso ao salvar no BD")
        } catch (e: Exception) {
            Log.i("${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "LIMPEZADAO::Erro ao salvar no BD")
            return false
        }
        return true

    }

    override fun remover(idQuartoNumero: Int): Boolean {
        val args = arrayOf(idQuartoNumero.toString())
        return try {
            val result = escrita.delete(
                DatabaseHelperChamados.TABELA_LIMPEZA,
                "${DatabaseHelperChamados.ID_NUMERO_QUARTO} = ?",
                args
            )
            result > 0
        } catch (e: Exception) {
            Log.i("${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "LIMPEZADAO:: erro ao remover LIMPEZA do DB")
            false
        }
    }

    override fun listar(): List<LimpezaDeQuartos> {
        val listaQuartosASeremLimpos = mutableListOf<LimpezaDeQuartos>()
        val sqlLimpeza = "SELECT * FROM ${DatabaseHelperChamados.TABELA_LIMPEZA};"
        val cursor = leitura.rawQuery(sqlLimpeza, null)

        val idNumeroQuarto = cursor.getColumnIndex("${DatabaseHelperChamados.ID_NUMERO_QUARTO}")
        val turnoLimpeza = cursor.getColumnIndex("${DatabaseHelperChamados.TURNO_LIMPEZA}")
        val indiceObservacoesLimpezaQuarto = cursor.getColumnIndex("${DatabaseHelperChamados.OBSERVACOES_LIMPEZA}")

        while (cursor.moveToNext()) {

            val idQuarto = cursor.getInt(idNumeroQuarto)
            val turnoQuarto = cursor.getString(turnoLimpeza)
            val observacoesLimpezaQuarto = cursor.getString(indiceObservacoesLimpezaQuarto)

            val limpezas =
                listaQuartosASeremLimpos.add(
                    LimpezaDeQuartos(idQuarto, turnoQuarto, observacoesLimpezaQuarto)
                )
        }
        return listaQuartosASeremLimpos
    }
}