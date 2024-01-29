package com.example.unitconverter.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unitconverter.databinding.FragmentTeampBinding

class TempFragment : Fragment() {

    private lateinit var binding: FragmentTeampBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeampBinding.inflate(layoutInflater, container, false)



        return binding.root
    }

}