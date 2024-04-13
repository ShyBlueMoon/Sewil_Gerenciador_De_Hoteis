package com.luanasilva.sewil.ui.agendamentoatividades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.luanasilva.sewil.databinding.FragmentAgendamentoAtividadesBinding


class AgendamentoAtividadesFragment : Fragment() {

    private lateinit var binding: FragmentAgendamentoAtividadesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgendamentoAtividadesBinding.inflate(inflater, container, false)
        return binding.root
    }

}