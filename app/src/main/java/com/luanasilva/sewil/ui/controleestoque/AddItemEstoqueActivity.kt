package com.luanasilva.sewil.ui.controleestoque

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luanasilva.sewil.EstoqueRecordsSQLiteDatabase
import com.luanasilva.sewil.ItemEstoque
import com.luanasilva.sewil.R





class AddItemEstoqueActivity : AppCompatActivity() {

    //lateinit var db: EstoqueRecordsSQLiteDatabase
    //private lateinit var txtIemDescricao: TextView


    //private lateinit var binding: AddItemEstoqueActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_item_estoque)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       /* val db =  EstoqueRecordsSQLiteDatabase(AddItemEstoqueActivity())
        txtIemDescricao = findViewById(R.id.txt_item_descricao)*/

    }

    /*fun addItem(){
        val itemEstoque = ItemEstoque()
        itemEstoque.descricao = txtIemDescricao.toString()
    }*/
}