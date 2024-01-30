package com.example.unitconverter.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.unitconverter.R
import com.example.unitconverter.databinding.FragmentCurrencyBinding


class CurrencyFragment : Fragment() {

    private val usdToEuRate = 0.85
    private val euToUsdRate = 1.18
    private lateinit var binding: FragmentCurrencyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyBinding.inflate(layoutInflater, container, false)

        // ArrayAdapter for the dropdown menus
        // time conversion section
        val fromMenu = resources.getStringArray(R.array.currencies)
        val toMenu = resources.getStringArray(R.array.currencies)

        // from menu drop down section
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            com.example.unitconverter.R.layout.drop_down_item,
            fromMenu
        )
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


        binding.currencyConvert.setOnClickListener {
            convertCurrency()
        }


        return binding.root
    }

    private fun convertCurrency() {
        val inputValueStr = binding.inputValue.text.toString().toDoubleOrNull()
        if (inputValueStr != null) {
            val fromCurrency = binding.fromSection.text.toString()
            val toCurrency = binding.toSection.text.toString()

            val conversionRates = mapOf(
                "USD" to mapOf(
                    "EUR" to 0.92,
                    "BDT" to 109.69,
                    "INR" to 83.14,
                    "CAD" to 1.34
                ),
                "EUR" to mapOf(
                    "USD" to 1.08,
                    "BDT" to 118.88,
                    "INR" to 90.11,
                    "CAD" to 1.45
                ),
                "BDT" to mapOf(
                    "USD" to 0.0091,
                    "EUR" to 0.0084,
                    "INR" to 0.76,
                    "CAD" to 0.012
                ),
                "INR" to mapOf(
                    "USD" to 0.012,
                    "EUR" to 0.011,
                    "BDT" to 1.32,
                    "CAD" to 0.016
                ),
                "CAD" to mapOf(
                    "USD" to 0.75,
                    "EUR" to 0.69,
                    "BDT" to 81.81,
                    "INR" to 62.01
                )
            )

            val conversionRate = conversionRates[fromCurrency]?.get(toCurrency)
            if (conversionRate != null) {
                val convertedAmount = inputValueStr * conversionRate
                binding.outputFrom.text = String.format("%.2f %s", inputValueStr, fromCurrency)
                binding.outputTo.text = String.format("%.2f %s", convertedAmount, toCurrency)
                binding.outputCardView.visibility = View.VISIBLE
                binding.welcomeOutput.text = "$fromCurrency To $toCurrency \nConversion Result"
                Toast.makeText(
                    requireContext(),
                    "This is not the Updated Data!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Handle unsupported conversion
                Toast.makeText(
                    requireContext(),
                    "Invalid input. Please enter a valid Currency, OR Chose Different Country",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}