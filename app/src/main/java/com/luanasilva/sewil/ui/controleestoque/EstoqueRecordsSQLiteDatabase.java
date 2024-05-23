package com.luanasilva.sewil.ui.controleestoque;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class EstoqueRecordsSQLiteDatabase {

    Context ctx;
    public static final String DATABASE_NAME = "sewil.db";
    public static final Integer DATABASE_VERSION = 13;
    private final SQLiteDatabase db;

    public EstoqueRecordsSQLiteDatabase(Context ctx) {
        this.ctx = ctx;
        db = new EstoqueRecordsSQLiteDatabaseHelper().getWritableDatabase();
    }



    //-----------------------------------ESTOQUE ------------------------------------------
    public static class EstoqueTable implements BaseColumns {
        public static final String TABLE_NAME = "Estoque";
        public static final String COLUMN_DESCRICAO = "descricao";
        public static final String COLUMN_QTD = "qtd";

        public static String getSQL() {
            return "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_DESCRICAO + " TEXT, " +
                    COLUMN_QTD + " INTEGER )";

        }
    }


    public Long addItem(ItemEstoque e) {
        ContentValues values = new ContentValues();
        values.put(EstoqueTable.COLUMN_DESCRICAO, e.getDescricao());
        values.put(EstoqueTable.COLUMN_QTD, e.getQtd());

        return db.insert(EstoqueTable.TABLE_NAME, null, values);
    }

    public ItemEstoque getItem(Long id) {
        String cols[] = {
                EstoqueTable._ID,
                EstoqueTable.COLUMN_DESCRICAO,
                EstoqueTable.COLUMN_QTD
                        };
        String args[] = {id.toString()};
        Cursor cursor = db.query(
                EstoqueTable.TABLE_NAME,
                cols,
                EstoqueTable._ID + "=?",
                args,
                null,
                null,
                EstoqueTable._ID
        );

        if (cursor.getCount() != 1) {
            return null;
        }

        cursor.moveToNext();

        ItemEstoque e = new ItemEstoque();
        e.setId(cursor.getLong(cursor.getColumnIndex(EstoqueTable._ID)));
        e.setDescricao(cursor.getString(cursor.getColumnIndex(EstoqueTable.COLUMN_DESCRICAO)));
        e.setQtd(cursor.getInt(cursor.getColumnIndex(EstoqueTable.COLUMN_QTD)));


        return e;
    }

    public List<ItemEstoque> getEstoque() {
        String cols[] = {
                EstoqueTable._ID,
                EstoqueTable.COLUMN_DESCRICAO,
                EstoqueTable.COLUMN_QTD
                        };
        Cursor cursor = db.query(
                EstoqueTable.TABLE_NAME,
                cols,
                null,
                null,
                null,
                null,
                EstoqueTable._ID
        );
        List<ItemEstoque> ItensEstoque = new ArrayList<>();
        ItemEstoque e;

        while (cursor.moveToNext()) {
            e = new ItemEstoque();
            e.setId(cursor.getLong(cursor.getColumnIndex(EstoqueTable._ID)));
            e.setDescricao(cursor.getString(cursor.getColumnIndex(EstoqueTable.COLUMN_DESCRICAO)));
            e.setQtd(cursor.getInt(cursor.getColumnIndex(EstoqueTable.COLUMN_QTD)));
            ItensEstoque.add(e);
        }

        return ItensEstoque;
    }

    public Integer removerItemEstoque(ItemEstoque t) {
        String args[] = {t.getId().toString()};

        return db.delete(
                EstoqueTable.TABLE_NAME,
                EstoqueTable._ID + "=?",
                args
        );
    }

    public Integer atualizarItemEstoque(ItemEstoque e) {
        String args[] = {e.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(EstoqueTable.COLUMN_DESCRICAO, e.getDescricao());
        values.put(EstoqueTable.COLUMN_QTD, e.getQtd());


        return db.update(
                EstoqueTable.TABLE_NAME,
                values,
                EstoqueTable._ID + "=?",
                args
        );
    }

    private class EstoqueRecordsSQLiteDatabaseHelper extends SQLiteOpenHelper {

        public EstoqueRecordsSQLiteDatabaseHelper() {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(EstoqueTable.getSQL());
            Log.d("LOGSEWIL","BANCO CRIADO. Tabelas Estoque e Manutenção criadas com sucesso.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + EstoqueTable.TABLE_NAME);

            onCreate(db);
            Log.d("LOGSEWIL","Tabelas dropadas com sucesso.");
        }
    }
}
