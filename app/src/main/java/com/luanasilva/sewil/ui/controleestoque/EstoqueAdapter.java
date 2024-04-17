package com.luanasilva.sewil.ui.controleestoque;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luanasilva.sewil.R;

class EstoqueAdapter extends BaseAdapter {
    LayoutInflater inflater;
    EstoqueRecordsSQLiteDatabase db;

    public EstoqueAdapter(Context ctx, EstoqueRecordsSQLiteDatabase db) {
        this.inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getEstoque().size();
    }

    @Override
    public Object getItem(int position) {
        return db.getEstoque().get(position);
    }

    @Override
    public long getItemId(int position) {
        return db.getEstoque().get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_estoque_lista_item, null);
        ItemEstoque item = db.getEstoque().get(position);
        TextView txtEstoqueItem = convertView.findViewById(R.id.estoque_lista_item);
        TextView txtQtdItem = convertView.findViewById(R.id.qtd_lista_item);
        txtEstoqueItem.setText(item.getDescricao());
        txtQtdItem.setText(String.valueOf(item.getQtd()));
        return convertView;
    }
}

