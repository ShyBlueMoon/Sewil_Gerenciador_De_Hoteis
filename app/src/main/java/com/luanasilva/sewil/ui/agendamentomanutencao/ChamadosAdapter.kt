package com.luanasilva.sewil.ui.agendamentomanutencao

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.luanasilva.sewil.R
import com.luanasilva.sewil.database.model.AreasManutencao

class ChamadosAdapter(context: Context,
                      private val chamados: List<AreasManutencao>


) : ArrayAdapter<AreasManutencao>(context, 0, chamados) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_view_chamados, parent, false)

        val chamado = getItem(position)

        val textViewID: TextView = view.findViewById(R.id.textViewID)
        val textViewNomeArea: TextView = view.findViewById(R.id.textViewNomeArea)
        val textViewObservacoes: TextView = view.findViewById(R.id.textViewObservacoes)



        textViewID.text  = chamado?.idArea.toString()
        textViewNomeArea.text = chamado?.nomeArea
        textViewObservacoes.text = chamado?.observacoesArea



        return view
    }
}
