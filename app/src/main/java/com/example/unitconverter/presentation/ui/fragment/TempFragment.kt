package com.example.unitconverter.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.unitconverter.R
import com.example.unitconverter.databinding.FragmentTeampBinding

class TempFragment : Fragment() {

    private lateinit var binding: FragmentTeampBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeampBinding.inflate(layoutInflater, container, false)

        // time conversion section
        val fromMenu = resources.getStringArray(R.array.tempSection)
        val toMenu = resources.getStringArray(R.array.tempSection)

        // from menu drop down section
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, fromMenu)
        binding.fromSection.setAdapter(arrayAdapter)
        binding.fromSection.setOnItemClickListener { parent, view, position, id ->
            val selectedOption = parent.getItemAtPosition(position).toString()
            //Toast.makeText(requireContext(), "You Selected $selectedOption", Toast.LENGTH_SHORT).show()
            binding.fromSectionText.text = selectedOption
        }

        // to menu drop down section
        val arrayAdapter2 = ArrayAdapter(requireContext(), R.layout.drop_down_item, toMenu)
        binding.toSection.setAdapter(arrayAdapter2)
        binding.toSection.setOnItemClickListener { parent, view, position, id ->
            val selectedOption = parent.getItemAtPosition(position).toString()
            //Toast.makeText(requireContext(), "You Selected $selectedOption", Toast.LENGTH_SHORT).show()
            binding.toSectionText.text = selectedOption
        }

        return binding.root
    }

}