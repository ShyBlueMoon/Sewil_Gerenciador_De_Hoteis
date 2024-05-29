package com.luanasilva.sewil.ui.bem_vindo

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luanasilva.sewil.R

class LandingPageFragment : Fragment() {

    companion object {
        fun newInstance() = LandingPageFragment()
    }

    private val viewModel: LandingPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_landing_page, container, false)
    }
}