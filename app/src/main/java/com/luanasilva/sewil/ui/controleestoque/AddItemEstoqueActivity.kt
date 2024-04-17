package com.luanasilva.sewil.ui.controleestoque

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luanasilva.sewil.R





class AddItemEstoqueActivity : AppCompatActivity() {

    private lateinit var btnAdicionar: Button
    private lateinit var db: EstoqueRecordsSQLiteDatabase
    private lateinit var txtIemDescricao: EditText
    private lateinit var txtQtd: EditText


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
        Log.d("LOGSEWIL","ANTES DE CRIAR O BANCO")
        val db =
            EstoqueRecordsSQLiteDatabase(
                baseContext
            )
        txtIemDescricao = findViewById(R.id.txt_item_descricao)
        txtQtd = findViewById(R.id.txt_qtd)

        btnAdicionar= findViewById(R.id.btn_adicionar)
        btnAdicionar.setOnClickListener {
            Log.d("LOGSEWIL","ESTOU LOGANDO")
            addItem()
        }
    }


    fun addItem(){
        val db =
            EstoqueRecordsSQLiteDatabase(
                baseContext
            )//método precisa ser instanciado no oncreate
        val itemEstoque = ItemEstoque()
        itemEstoque.descricao = txtIemDescricao.text.toString()
        itemEstoque.qtd = txtQtd.text.toString().toInt()
        db.addItem(itemEstoque)
        finish()
    }
}