package com.luanasilva.sewil.ui.quartoslimpezamanutencao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.luanasilva.sewil.R
import com.luanasilva.sewil.databinding.FragmentQuartosLimpezaManutencaoBinding


class QuartosLimpezaManutencaoFragment : Fragment() {

    private lateinit var editQuarto: EditText
    private lateinit var editLimpeza: EditText
    private lateinit var btnSalvarLimpeza: Button
    private lateinit var btnConsultarLimpeza: Button




    private lateinit var binding:FragmentQuartosLimpezaManutencaoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(
            R.layout.fragment_quartos_limpeza_manutencao, container, false

        )

        /*editQuarto = view.findViewById(R.id.edit_quarto)
        editLimpeza = view.findViewById(R.id.edit_limpeza)
        btnSalvarLimpeza = view.findViewById(R.id.btn_salvar_limpeza)
        btnConsultarLimpeza = view.findViewById(R.id.btn_consultar_limpeza)*/



        binding = FragmentQuartosLimpezaManutencaoBinding.inflate(inflater, container, false)
        return binding.root
    }
}
