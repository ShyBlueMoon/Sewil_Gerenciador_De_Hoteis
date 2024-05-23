package com.luanasilva.sewil.ui.agendamentomanutencao

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.luanasilva.sewil.R
import com.luanasilva.sewil.database.model.AreasManutencao
import com.luanasilva.sewil.database.model.ChamadosDAO
import com.luanasilva.sewil.database.model.DatabaseHelperChamados

import com.luanasilva.sewil.databinding.ActivityChamadoManutencaoBinding

class ChamadoManutencaoActivity : AppCompatActivity() {


    private lateinit var binding: ActivityChamadoManutencaoBinding

    private lateinit var boxArea1: CheckBox
    private lateinit var boxArea2: CheckBox
    private lateinit var boxArea3: CheckBox
    private lateinit var boxArea4: CheckBox
    private lateinit var textInputObservacoes: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChamadoManutencaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialize as views aqui
        boxArea1 = binding.boxArea1
        boxArea2 = binding.boxArea2
        boxArea3 = binding.boxArea3
        boxArea4 = binding.boxArea4
        textInputObservacoes = binding.textInputObservacoes

        salvar()
        voltar()

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Áreas
    var qualquerCheckVerdadeiro: Boolean = false

    private fun qualquerCheckBoxSelecionado(): Boolean {
        qualquerCheckVerdadeiro = binding.boxArea1.isChecked || binding.boxArea2.isChecked || binding.boxArea3.isChecked || binding.boxArea4.isChecked
        return qualquerCheckVerdadeiro
    }

    private fun salvarNoDB() {
        val chamadosDAO = ChamadosDAO(this)

        // Atualize a variável observacoes aqui
        val observacoes = textInputObservacoes.text.toString()

        val areas = listOf(
            Pair(boxArea1, AreasManutencao(1, "Quartos e corredores", observacoes)),
            Pair(boxArea2, AreasManutencao(2, "Piscina e Sala de Jogos", observacoes)),
            Pair(boxArea3, AreasManutencao(3, "Cozinha e Restaurante", observacoes)),
            Pair(boxArea4, AreasManutencao(4, "Recepção e Sala de Espera", observacoes))
        )

        for ((checkBox, area) in areas) {
            if (checkBox.isChecked) {
                chamadosDAO.salvar(area)
                Log.i("${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "CHAMADOMANUTENCAOActivity:: ${area.nomeArea} SELECIONADA")
            }
        }

        Toast.makeText(
            this,
            "Sucesso ao criar o chamado de manutenção no sistema.",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun salvar() {
        with(binding) {
            btnSalvar.setOnClickListener {
                qualquerCheckBoxSelecionado()
                if (qualquerCheckVerdadeiro) {
                    salvarNoDB()
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Nenhum checkbox foi selecionado",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun voltar() {
        with(binding) {
            btnCancelar.setOnClickListener {
                Log.i("${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "CHAMADOMANUTENCAOActivity:: botão voltar clicado")
                finish()
            }
        }
    }
}

