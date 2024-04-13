package com.luanasilva.sewil.ui.gestaofuncionarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.luanasilva.sewil.databinding.FragmentGestaoFuncionariosBinding

class GestaoFuncionariosFragment : Fragment() {

    private lateinit var binding: FragmentGestaoFuncionariosBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGestaoFuncionariosBinding.inflate(inflater, container, false)
        return binding.root
    }

}