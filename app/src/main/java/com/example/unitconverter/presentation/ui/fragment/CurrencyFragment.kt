package com.example.unitconverter.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
                    "EUR" to 0.85,
                    "BDT" to 84.96, // Example conversion rate, please replace with actual rates
                    "INR" to 73.05, // Example conversion rate, please replace with actual rates
                    "CAD" to 1.27    // Example conversion rate, please replace with actual rates
                ),
                "EUR" to mapOf(
                    "USD" to 1.18,  // Example conversion rate, please replace with actual rates
                    "BDT" to 99.75, // Example conversion rate, please replace with actual rates
                    "INR" to 86.10, // Example conversion rate, please replace with actual rates
                    "CAD" to 1.55    // Example conversion rate, please replace with actual rates
                ),
                "BDT" to mapOf(
                    "USD" to 0.012, // Example conversion rate, please replace with actual rates
                    "EUR" to 0.010, // Example conversion rate, please replace with actual rates
                    "INR" to 0.87,  // Example conversion rate, please replace with actual rates
                    "CAD" to 0.016  // Example conversion rate, please replace with actual rates
                ),
                "INR" to mapOf(
                    "USD" to 0.014, // Example conversion rate, please replace with actual rates
                    "EUR" to 0.012, // Example conversion rate, please replace with actual rates
                    "BDT" to 1.15,  // Example conversion rate, please replace with actual rates
                    "CAD" to 0.018  // Example conversion rate, please replace with actual rates
                ),
                "CAD" to mapOf(
                    "USD" to 0.79,  // Example conversion rate, please replace with actual rates
                    "EUR" to 0.64,  // Example conversion rate, please replace with actual rates
                    "BDT" to 62.75, // Example conversion rate, please replace with actual rates
                    "INR" to 55.96  // Example conversion rate, please replace with actual rates
                )
            )
            val conversionRate = conversionRates[fromCurrency]?.get(toCurrency)
            if (conversionRate != null) {
                val convertedAmount = inputValueStr * conversionRate
                binding.outputFrom.text = String.format("%.2f %s", inputValueStr, fromCurrency)
                binding.outputTo.text = String.format("%.2f %s", convertedAmount, toCurrency)
                binding.outputCardView.visibility = View.VISIBLE
                binding.outputFrom.text = "$inputValueStr $fromCurrency"
                binding.outputTo.text = "$convertedAmount $toCurrency"
            } else {
                // Handle unsupported conversion
                // You can display a message or take any other appropriate action
            }

        }
    }

}