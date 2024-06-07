package com.luanasilva.sewil.ui.agendamentomanutencao

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.luanasilva.sewil.R
import com.luanasilva.sewil.database.model.ChamadosDAO
import com.luanasilva.sewil.database.model.DatabaseHelperChamados
import com.luanasilva.sewil.databinding.FragmentAgendamentoManutencaoBinding


class AgendamentoManutencaoFragment : Fragment() {
    //Declarar variáveis aqui
    private lateinit var binding: FragmentAgendamentoManutencaoBinding
    private lateinit var listChamadosView: ListView
    private lateinit var adapter: ChamadosAdapter




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgendamentoManutencaoBinding.inflate(inflater, container, false)

        //Inicializar variáveis aqui dentro do View, antes do Return da função
        listChamadosView = binding.listAreas
        novoChamado()



        return binding.root
    }




    override fun onResume() {
        super.onResume()
        listChamadosView.setOnItemLongClickListener { parent, view, position, id ->
            val chamado = adapter.getItem(position)
            if (chamado != null) {
                exibirAlertDialog(chamado.idArea, position)
            }
            true
        }

        exibirListaChamados()

    }

    private fun exibirListaChamados() {

        Log.i(
            "${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}",
            "AGENDAMENTOMANUTENCAOFragment:: Sucesso chamar exibirListaChamados()."
        )
        listChamadosView = binding.listAreas
        val chamadosDAO = ChamadosDAO(activity as Context)
        val listaChamados = chamadosDAO.listar()


        adapter = ChamadosAdapter(requireContext(), listaChamados)
        listChamadosView.adapter = adapter
        adapter.notifyDataSetChanged()


    }

    private fun novoChamado() {
        with(binding) {
            novoChamado.setOnClickListener() {
                val intent = Intent(activity, ChamadoManutencaoActivity::class.java)
                startActivity(intent)
                Log.i(
                    "${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}",
                    "AGENDAMENTOMANUTENCAOFragment:: Novo chamado clicado."
                )
            }
        }
    }

    private fun exibirAlertDialog(idArea:Int, position:Int):Boolean{

        val alertBuilder = AlertDialog.Builder(requireActivity())
        alertBuilder.setTitle("Exclusão de Item")
        alertBuilder.setMessage("Você tem certeza que quer excluir o item selecionado?")
        alertBuilder.setPositiveButton("Sim") { dialog, _ ->
            val chamadosDAO = ChamadosDAO(activity as Context)

            if (chamadosDAO.remover(idArea)) {
                Toast.makeText(activity, "Item excluído com sucesso", Toast.LENGTH_LONG).show()
                adapter.remove(adapter.getItem(position))
                exibirListaChamados()
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

}
