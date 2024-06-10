package com.luanasilva.sewil

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.luanasilva.sewil.databinding.ActivityAddItemEstoqueBinding
import com.luanasilva.sewil.databinding.ActivityMainBinding
import com.luanasilva.sewil.ui.agendamentomanutencao.ChamadoManutencaoActivity
import com.luanasilva.sewil.ui.controleestoque.AddItemEstoqueActivity
import com.luanasilva.sewil.ui.quartoslimpezamanutencao.NovoChamadoLimpezaActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            with(binding) {
                val fabChamadoManutencao = appBarMain.fabMaisManutencao
                val fabItemEstoque = appBarMain.fabMaisEstoque
                val fabLimpezaQuartos = appBarMain.fabLMaisLimpeza

                if(fabChamadoManutencao.isVisible && fabItemEstoque.isVisible && fabLimpezaQuartos.isVisible) {
                    fabItemEstoque.visibility = View.INVISIBLE
                    fabChamadoManutencao.visibility = View.INVISIBLE
                    fabLimpezaQuartos.visibility = View.INVISIBLE


                } else {
                    fabItemEstoque.visibility = View.VISIBLE
                    fabChamadoManutencao.visibility = View.VISIBLE
                    fabLimpezaQuartos.visibility = View.VISIBLE

                    fabChamadoManutencao.setOnClickListener{
                        startActivity(Intent(this@MainActivity,ChamadoManutencaoActivity::class.java))
                    }

                    fabItemEstoque.setOnClickListener {
                        val intent = Intent(this@MainActivity,AddItemEstoqueActivity::class.java)
                        startActivity(intent)
                    }

                    fabLimpezaQuartos.setOnClickListener {
                        val intent = Intent(this@MainActivity,NovoChamadoLimpezaActivity::class.java)
                        startActivity(intent)
                    }

                }


            }
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_bem_vindo, R.id.nav_agendamento_manutencao, R.id.nav_controle_estoque, R.id.nav_quartos_limpeza_manutencao
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        with(binding) {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}