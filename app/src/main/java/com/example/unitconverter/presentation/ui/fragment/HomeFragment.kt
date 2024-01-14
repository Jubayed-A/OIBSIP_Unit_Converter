package com.example.unitconverter.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.unitconverter.R
import com.example.unitconverter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        // start time fragment
        binding.timeSection.setOnClickListener {
            // Toast.makeText(activity, "hello i am back", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_timeFragment)
        }
        // start currency fragment
        binding.currencySection.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_currencyFragment)
        }
        // start tempSection fragment
        binding.tempSection.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_tempFragment)
        }
        // start about fragment
        binding.aboutSection.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_aboutFragment)
        }



        return binding.root
    }

}