package com.luanasilva.sewil.ui.controleestoque

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.luanasilva.sewil.EstoqueRecordsSQLiteDatabase
import com.luanasilva.sewil.R
import com.luanasilva.sewil.databinding.FragmentControleEstoqueBinding

class ControleEstoqueFragment : Fragment() {

    private lateinit var btnItem: Button
    private lateinit var binding: FragmentControleEstoqueBinding
    //lateinit var db : EstoqueRecordsSQLiteDatabase
    //private lateinit var lstEstoque: ListView
    //private lateinit var estoqueAdapter: EstoqueAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentControleEstoqueBinding. inflate(inflater, container,  false)
        val view = binding.root
        //val view = inflater.inflate(
        //R.layout.fragment_controle_estoque, container, false)



        btnItem = view.findViewById(R.id.btn_Item)


        btnItem.setOnClickListener {
            val intent = Intent(activity, AddItemEstoqueActivity::class.java)
            startActivity(intent)
        }


       /*val db =  EstoqueRecordsSQLiteDatabase(requireContext())
        lstEstoque= view.findViewById(R.id.estoque_lista)
        estoqueAdapter = EstoqueAdapter(requireContext(),db)
        lstEstoque.adapter = estoqueAdapter*/

        //binding = FragmentControleEstoqueBinding.inflate(inflater, container, false)
        //return binding.root
        return view
    }

}