package com.luanasilva.sewil.ui.quartoslimpezamanutencao

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.luanasilva.sewil.R
import com.luanasilva.sewil.database.model.DatabaseHelperChamados
import com.luanasilva.sewil.database.model.LimpezasDAO
import com.luanasilva.sewil.databinding.FragmentQuartosLimpezaManutencaoBinding


class QuartosLimpezaManutencaoFragment : Fragment() {

    private lateinit var binding:FragmentQuartosLimpezaManutencaoBinding
    private lateinit var listLimpezaView:ListView
    private lateinit var adapter: LimpezasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val view = inflater.inflate(
            R.layout.fragment_quartos_limpeza_manutencao, container, false

        )
        binding = FragmentQuartosLimpezaManutencaoBinding.inflate(inflater, container, false)


        //FUNCOES AQUI
        novoChamadoLimpeza()
        listLimpezaView =  binding.listAreasLimpeza


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        listLimpezaView.setOnItemLongClickListener { parent, view, position, id ->
            val limpeza = adapter.getItem(position)
            if (limpeza != null) {
                exibirAlertDialog(limpeza.idNumeroQuarto, position)
            }

            true
        }

        exibirListaLimpeza()

    }

    private fun exibirListaLimpeza() {

        Log.i(
            "${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}",
            "QUARTOSLIMPEZAMANUTENCAOFragment:: Sucesso chamar exibirListaLimpeza()."
        )
        listLimpezaView = binding.listAreasLimpeza
        val limpezaDAO = LimpezasDAO(activity as Context)
        val listaLimpezas = limpezaDAO.listar()

        adapter = LimpezasAdapter(requireContext(),listaLimpezas)
        listLimpezaView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    private fun exibirAlertDialog(idNumeroQuarto:Int, position:Int):Boolean{
        val alertBuilder = AlertDialog.Builder(requireActivity())

        alertBuilder.setTitle("Exclusão de Item")
        alertBuilder.setMessage("Você tem certeza que quer excluir o item selecionado?")
        alertBuilder.setPositiveButton("Sim") { dialog, _ ->
            val limpezasDAO = LimpezasDAO(activity as Context)

            if (limpezasDAO.remover(idNumeroQuarto)) {
                Toast.makeText(activity, "Item excluído com sucesso", Toast.LENGTH_LONG).show()
                adapter.remove(adapter.getItem(position))
                exibirListaLimpeza()
            } else {
                Toast.makeText(activity, "Erro ao excluir o item", Toast.LENGTH_LONG).show()
            }
        }
        alertBuilder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertBuilder.create()
        alertDialog.show()
        return true
    }

    private fun novoChamadoLimpeza() {
        with(binding) {
            btnNovoChamadoLimpeza.setOnClickListener {
                startActivity(Intent(activity, NovoChamadoLimpezaActivity::class.java))
            }
        }
    }


}
