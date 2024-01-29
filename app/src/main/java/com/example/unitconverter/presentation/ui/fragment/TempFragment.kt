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

        // temp conversion section
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

        binding.tempConvert.setOnClickListener {
            convertTemperature()
        }

        return binding.root
    }

    private fun convertTemperature() {
        val inputTemperature = binding.inputValue.text.toString().toFloatOrNull()
        val fromUnit = binding.fromSection.text.toString()
        val toUnit = binding.toSection.text.toString()

        if (inputTemperature != null) {
            val convertedTemperature = when {
                fromUnit == "Celsius" && toUnit == "Fahrenheit" -> celsiusToFahrenheit(inputTemperature)
                fromUnit == "Fahrenheit" && toUnit == "Celsius" -> fahrenheitToCelsius(inputTemperature)
                fromUnit == "Celsius" && toUnit == "Kelvin" -> celsiusToKelvin(inputTemperature)
                fromUnit == "Kelvin" && toUnit == "Celsius" -> kelvinToCelsius(inputTemperature)
                fromUnit == "Fahrenheit" && toUnit == "Kelvin" -> fahrenheitToKelvin(inputTemperature)
                fromUnit == "Kelvin" && toUnit == "Fahrenheit" -> kelvinToFahrenheit(inputTemperature)

                else -> inputTemperature
            }

            binding.outputFrom.text = String.format("%.2f %s", inputTemperature, fromUnit)
            binding.outputTo.text = String.format("%.2f %s", convertedTemperature, toUnit)
            binding.welcomeOutput.text = "$fromUnit To $toUnit\nConversion Result"
            binding.outputCardView.visibility = View.VISIBLE
        }
    }

    private fun celsiusToFahrenheit(celsius: Float): Float {
        return celsius * 9 / 5 + 32
    }

    private fun celsiusToKelvin(celsius: Float): Float {
        return celsius + 273.15f
    }

    private fun fahrenheitToCelsius(fahrenheit: Float): Float {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun fahrenheitToKelvin(fahrenheit: Float): Float {
        return (fahrenheit - 32) * 5 / 9 + 273.15f
    }

    private fun kelvinToCelsius(kelvin: Float): Float {
        return kelvin - 273.15f
    }

    private fun kelvinToFahrenheit(kelvin: Float): Float {
        return kelvin * 9 / 5 - 459.67f
    }

}