package com.luanasilva.sewil.ui.quartoslimpezamanutencao

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.luanasilva.sewil.R
import com.luanasilva.sewil.database.model.DatabaseHelperChamados
import com.luanasilva.sewil.database.model.LimpezaDeQuartos
import com.luanasilva.sewil.database.model.LimpezasDAO
import com.luanasilva.sewil.databinding.ActivityNovoChamadoLimpezaBinding

class NovoChamadoLimpezaActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityNovoChamadoLimpezaBinding.inflate(layoutInflater)
    }


    private lateinit var autoCompleteNumeroQuarto: AutoCompleteTextView
    private lateinit var textInputObservacoesLimpeza: TextInputEditText
    private  var listaNumeroQuartos = mutableListOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        salvar()
        voltar()

        enableEdgeToEdge()
        setContentView(binding.root)

        exibirSpinner()
        autocompleteTextView ()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }




    private fun exibirSpinner() {
        val turnos = listOf("Manhã", "Tarde")
        binding.spinnerTurno.adapter = ArrayAdapter<String> (
            this,
            android.R.layout.simple_spinner_dropdown_item,
            turnos
        )


    }

    private fun autocompleteTextView () {

        binding.autoCompleteNumeroQuarto.threshold = 1

        binding.autoCompleteNumeroQuarto.setAdapter(
           ArrayAdapter(this,
               android.R.layout.simple_spinner_dropdown_item,
               listaNumeroQuartos)
        )

    }

    private fun salvarLimpezaNoBd() {
        val limpezasDAO = LimpezasDAO(this)
        autoCompleteNumeroQuarto = binding.autoCompleteNumeroQuarto
        textInputObservacoesLimpeza =  binding.textInputInformacoesExtrasLimpeza

        var numeroQuartoString = autoCompleteNumeroQuarto.text.toString()
        var numeroQuarto = numeroQuartoString.toInt()
        var observacoesLimpeza = textInputObservacoesLimpeza.text.toString()
        var turnoSelecionado = binding.spinnerTurno.selectedItem


        val limpezaChamado = LimpezaDeQuartos(numeroQuarto, "$turnoSelecionado", observacoesLimpeza)

        limpezasDAO.salvar(limpezaChamado)
    }



    private fun salvar() {
        with(binding) {
            btnSalvar.setOnClickListener {
                var numeroQuarto = autoCompleteNumeroQuarto.toString()


                if (numeroQuarto.isNotEmpty() && numeroQuarto.isNotBlank()) {
                    salvarLimpezaNoBd()
                    Log.i("${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "NovoChamadoLimpezaACTIVITY:: Sucesso ao salvar QUARTO no BD.")
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Nenhum número de quarto foi digitado.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun  voltar() {
        with(binding) {
            btnCancelar.setOnClickListener {
                Log.i("${DatabaseHelperChamados.INFO_SEWIL_MANUTENCAO_DB}", "CHAMADOMANUTENCAOActivity:: botão voltar clicado")
                finish()
            }
        }
    }
}