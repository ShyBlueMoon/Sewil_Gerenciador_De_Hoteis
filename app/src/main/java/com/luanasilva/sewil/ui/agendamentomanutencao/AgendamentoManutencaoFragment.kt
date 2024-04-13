package com.luanasilva.sewil.ui.agendamentomanutencao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.luanasilva.sewil.R
import com.luanasilva.sewil.databinding.FragmentAgendamentoManutencaoBinding

class AgendamentoManutencaoFragment : Fragment() {

    private lateinit var boxArea1: CheckBox
    private lateinit var boxArea2: CheckBox
    private lateinit var boxArea3: CheckBox
    private lateinit var boxArea4: CheckBox
    private lateinit var textDescricao: TextView
    private lateinit var btnSalvar: Button
    private lateinit var btnManutenco: Button



    private lateinit var binding:FragmentAgendamentoManutencaoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(
            R.layout.fragment_agendamento_manutencao, container, false

        )

        boxArea1 = view.findViewById(R.id.box_area1)
        boxArea2 = view.findViewById(R.id.box_area2)
        boxArea3 = view.findViewById(R.id.box_area3)
        boxArea4 = view.findViewById(R.id.box_area4)
        textDescricao = view.findViewById(R.id.text_descricao)
        btnSalvar = view.findViewById(R.id.btn_salvar)
        btnManutenco = view.findViewById(R.id.btn_manutencao)




        binding = FragmentAgendamentoManutencaoBinding.inflate(inflater, container, false)
        return binding.root

    }


}