package com.example.unitconverter.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.unitconverter.R
import com.example.unitconverter.databinding.FragmentLengthBinding

class LengthFragment : Fragment() {

    private lateinit var binding: FragmentLengthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLengthBinding.inflate(layoutInflater, container, false)

        // time conversion section
        val fromMenu = resources.getStringArray(R.array.lengthSection)
        val toMenu = resources.getStringArray(R.array.lengthSection)

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

        binding.lengthConvert.setOnClickListener {
            convertLength()
        }

        return binding.root
    }

    private fun convertLength() {
        val inputLength = binding.inputValue.text.toString().toFloatOrNull()
        val fromUnit = binding.fromSection.text.toString()
        val toUnit = binding.toSection.text.toString()

        if (inputLength != null) {
            val convertedLength = when {
                fromUnit == "Meter" && toUnit == "Centimeter" -> meterToCentimeter(inputLength)
                fromUnit == "Centimeter" && toUnit == "Meter" -> centimeterToMeter(inputLength)
                // Add more conversion cases here
                else -> inputLength
            }

            binding.outputFrom.text = String.format("%.2f %s", inputLength, fromUnit)
            binding.outputTo.text = String.format("%.2f %s", convertedLength, toUnit)
            binding.outputCardView.visibility = View.VISIBLE
        }
    }

    private fun meterToCentimeter(meter: Float): Float {
        return meter * 100
    }

    private fun centimeterToMeter(centimeter: Float): Float {
        return centimeter / 100
    }

}