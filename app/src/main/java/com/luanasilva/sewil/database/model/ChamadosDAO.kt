package com.luanasilva.sewil.database.model

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.luanasilva.sewil.database.IChamadosDAO

class ChamadosDAO(context: Context): IChamadosDAO {
    //Classe responsavel por acessar e salvar/alterar os chamados da manutenção no BD


    private val escrita = DatabaseHelperChamados(context).writableDatabase
    private val leitura = DatabaseHelperChamados(context).readableDatabase



    //-----------------------------SALVAR--------------------------------------
    override fun salvar(area: AreasManutencao): Boolean {
        val valores = ContentValues()
        //ID_AREA é o nome da coluna e area.idArea é o valor que voce vai colocar
        valores.put("${DatabaseHelperChamados.ID_AREA}", area.idArea)
        valores.put("${DatabaseHelperChamados.NOME_AREA}", area.nomeArea)
        valores.put("${DatabaseHelperChamados.OBSERVACOES_AREA}", area.observacoesArea)

        try {

            escrita.insert(
                DatabaseHelperChamados.TABELA_CHAMADOS,
                null,
                valores
            )
            Log.i("{${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "CHAMADOSDAO::Sucesso ao salvar no BD")
        } catch (e: Exception) {
            Log.i("${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "CHAMADOSDAO::Erro ao salvar no BD")
            return false
        }
        return true
    }


    //-----------------------------REMOVER--------------------------------------
    override fun remover(idArea: Int): Boolean {
        val args = arrayOf(idArea.toString())
        return try {
            val result = escrita.delete(
                DatabaseHelperChamados.TABELA_CHAMADOS,
                "${DatabaseHelperChamados.ID_AREA} = ?",
                args
            )
            result > 0
        } catch (e: Exception) {
            Log.i("${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "CHAMADOSDAO:: erro ao remover do DB")
            false
        }
    }


    //----------------------------LISTAR----------------------------------------
    override fun listar(): List<AreasManutencao> {
        val listaAreasComChamados = mutableListOf<AreasManutencao>()
        val sql = "SELECT * FROM ${DatabaseHelperChamados.TABELA_CHAMADOS};"
        val cursor = leitura.rawQuery(sql, null)

        val indiceIdArea = cursor.getColumnIndex("${DatabaseHelperChamados.ID_AREA}")
        val indiceNomeArea = cursor.getColumnIndex("${DatabaseHelperChamados.NOME_AREA}")
        val indiceObservacoesArea = cursor.getColumnIndex("${DatabaseHelperChamados.OBSERVACOES_AREA}")

        while (cursor.moveToNext()) {

            val idArea = cursor.getInt(indiceIdArea)
            val nomeArea = cursor.getString(indiceNomeArea)
            val observacoesArea = cursor.getString(indiceObservacoesArea)

            val produto =
                listaAreasComChamados.add(
                    AreasManutencao(idArea, nomeArea, observacoesArea)
                )
        }
        return listaAreasComChamados
    }
}