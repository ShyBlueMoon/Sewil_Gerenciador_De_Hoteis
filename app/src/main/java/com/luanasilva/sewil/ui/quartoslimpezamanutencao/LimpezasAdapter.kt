package com.luanasilva.sewil.ui.quartoslimpezamanutencao

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.luanasilva.sewil.R
import com.luanasilva.sewil.database.model.LimpezaDeQuartos

class LimpezasAdapter(context: Context, private val limpezas:List<LimpezaDeQuartos>):ArrayAdapter<LimpezaDeQuartos>(context, 0, limpezas) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_view_limpeza, parent, false)

        val limpeza = getItem(position)

        val textViewIdNumeroQuarto: TextView = view.findViewById(R.id.textViewIDNumeroQuarto)
        val textViewTurno: TextView = view.findViewById(R.id.textViewTurno)
        val textViewObservacoesLimpeza: TextView = view.findViewById(R.id.textViewObservacoesLimpeza)


        textViewIdNumeroQuarto.text = limpeza?.idNumeroQuarto.toString()
        textViewTurno.text = limpeza?.nomeTurno
        textViewObservacoesLimpeza.text = limpeza?.observacoesLimpezaQuartos

        return view

    }
}