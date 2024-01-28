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
        binding = FragmentCurrencyBinding.inflate(layoutInflater,container, false)

        // ArrayAdapter for the dropdown menus
        // time conversion section
        val fromMenu = resources.getStringArray(R.array.currencies)
        val toMenu = resources.getStringArray(R.array.currencies)

        // from menu drop down section
        val arrayAdapter = ArrayAdapter(requireContext(), com.example.unitconverter.R.layout.drop_down_item, fromMenu)
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

            val convertedValue = when {
                fromCurrency == getString(R.string.usd) && toCurrency == getString(R.string.eu) -> {
                    inputValueStr * usdToEuRate
                }
                fromCurrency == getString(R.string.eu) && toCurrency == getString(R.string.usd) -> {
                    inputValueStr * euToUsdRate
                }
                else -> {
                    // Handle other conversions or display error
                    0.0
                }
            }

            binding.outputFrom.text = "$inputValueStr $fromCurrency"
            binding.outputTo.text = "$convertedValue $toCurrency"
        }
    }

}