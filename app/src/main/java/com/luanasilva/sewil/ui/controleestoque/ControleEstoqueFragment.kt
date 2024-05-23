package com.luanasilva.sewil.ui.controleestoque

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.luanasilva.sewil.MainActivity
import com.luanasilva.sewil.R
import com.luanasilva.sewil.databinding.FragmentControleEstoqueBinding
import java.text.FieldPosition


class ControleEstoqueFragment : Fragment() {

    private lateinit var btnItem: Button
    private lateinit var binding: FragmentControleEstoqueBinding

    private lateinit var lstEstoque: ListView
    private lateinit var estoqueAdapter: EstoqueAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentControleEstoqueBinding.inflate(inflater, container, false)
        val view = inflater.inflate(
            R.layout.fragment_controle_estoque, container, false
        )
        val db =  EstoqueRecordsSQLiteDatabase(requireContext())
        lstEstoque= view.findViewById(R.id.estoque_lista)
        estoqueAdapter = EstoqueAdapter(requireContext(),db)
        lstEstoque.adapter = estoqueAdapter
        //lstEstoque.setOnItemClickListener()//edição




        lstEstoque.setOnItemLongClickListener { parent, view, position, id ->
            /*val mostarToast = */
            exibirAlertDialog(position,db)



            /*db.removerItemEstoque(estoqueAdapter.getItem(position) as ItemEstoque)
            estoqueAdapter.notifyDataSetChanged()
            exibirAlertDialog(position,db)
            true*/


            }

        btnItem = view.findViewById(R.id.btn_Item)
        btnItem.setOnClickListener {
            val intent = Intent(activity, AddItemEstoqueActivity::class.java)
            startActivity(intent)
        }
        binding = FragmentControleEstoqueBinding.inflate(inflater, container, false)

        return view
    }
    private fun exibirAlertDialog(position: Int, db:EstoqueRecordsSQLiteDatabase):Boolean{
        val alertBuilder = AlertDialog.Builder(requireActivity())
        alertBuilder.setTitle("Exclusão de Item")
        alertBuilder.setMessage("Você tem certeza que quer excluir o item selecionado?")
        alertBuilder.setPositiveButton("Sim"){dialog,posicao ->
            db.removerItemEstoque(estoqueAdapter.getItem(position)as ItemEstoque)
            estoqueAdapter.notifyDataSetChanged()
            true
        }
        alertBuilder.setNegativeButton("CANCELAR"){dialog, posicao ->
            dialog.dismiss()
        }
        val alertDialog = alertBuilder.create()
        alertDialog.show()
        return true
    }

    override fun onResume(){
        super.onResume()
        estoqueAdapter.notifyDataSetChanged()
    }
}