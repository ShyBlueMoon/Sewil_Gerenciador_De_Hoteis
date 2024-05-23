package com.luanasilva.sewil.database.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelperChamados(context: Context): SQLiteOpenHelper(context, "sewilchamados.db", null, 1) {
    //Classe responsável por criar o BD  e a Tabela


    companion object {
        const val TABELA_CHAMADOS = "chamados"
        const val ID_AREA = "id_area"
        const val NOME_AREA = "titulo"
        const val OBSERVACOES_AREA = "observacoes_area"

        const val INFO_SEWIL_MANUTENCAO_DB = "logmanutencao"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("$INFO_SEWIL_MANUTENCAO_DB", "Executou o OnCreate")
        //É chamado uma única vez quando o usuário instala o aplicativo



        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_CHAMADOS (" +
                "$ID_AREA INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NOME_AREA VARCHAR(20), " +
                "$OBSERVACOES_AREA VARCHAR(175)" +
                ");"

        try {
            db?.execSQL(sql)
            Log.i("${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "DATABASEHELPERCHAMADOS:: Sucesso ao criar a tabela.")
        }catch(exception: Exception){
            exception.printStackTrace()
            Log.i("$INFO_SEWIL_MANUTENCAO_DB", "DATABASEHELPERCHAMADOS:: Erro ao criar a tabela!")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("$INFO_SEWIL_MANUTENCAO_DB", "DATABASEHELPERCHAMADOS::Executou o OnUpdate")
    }
}