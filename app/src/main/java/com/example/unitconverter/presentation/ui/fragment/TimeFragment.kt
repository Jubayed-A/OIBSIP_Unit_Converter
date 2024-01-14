package com.example.unitconverter.presentation.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.unitconverter.R
import com.example.unitconverter.databinding.FragmentTimeBinding


class TimeFragment : Fragment() {

    private lateinit var binding: FragmentTimeBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeBinding.inflate(layoutInflater, container, false)

        // time conversion section
        val fromMenu = resources.getStringArray(R.array.time_name)
        val toMenu = resources.getStringArray(R.array.time_name)

        // from menu drop down section
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, fromMenu)
        binding.fromSection.setAdapter(arrayAdapter)
        binding.fromSection.setOnItemClickListener { parent, view, position, id ->
            val selectedOption = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "You Selected $selectedOption", Toast.LENGTH_SHORT)
                .show()
            binding.fromSectionText.text = selectedOption
        }

        // to menu drop down section
        val arrayAdapter2 = ArrayAdapter(requireContext(), R.layout.drop_down_item, toMenu)
        binding.toSection.setAdapter(arrayAdapter2)
        binding.toSection.setOnItemClickListener { parent, view, position, id ->
            val selectedOption = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "You Selected $selectedOption", Toast.LENGTH_SHORT)
                .show()
            binding.toSectionText.text = selectedOption
        }

        // time calculate section code here
        binding.timeCalculate.setOnClickListener {
            // Get the input values
            val inputValue = binding.inputValue.text.toString().trim()

            // Check if the input is a valid number
            if (inputValue.isNotEmpty() && inputValue.toDoubleOrNull() != null) {
                val inputNumber = inputValue.toDouble()

                // Get the selected options from the menus
                val fromMenuOption = binding.fromSectionText.text.toString()
                val toMenuOption = binding.toSectionText.text.toString()

                // Perform the time conversion based on the selected options
                val result = performTimeConversion(inputNumber, fromMenuOption, toMenuOption)

                // Display the result or use it as needed
                Toast.makeText(requireContext(), "Result: $result", Toast.LENGTH_SHORT).show()
                binding.outputFrom.setText("$inputNumber  $fromMenuOption")
                binding.outputTo.setText("$result $toMenuOption")
                binding.welcomeOutput.setText("$fromMenuOption To $toMenuOption Conversion Result")
            } else {
                // Handle the case when the input is not a valid number
                Toast.makeText(requireContext(), "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    // Function to perform time conversion
    private fun performTimeConversion(inputNumber: Double, fromOption: String, toOption: String): Double {
        // Conversion factors
        val secondsInMinute = 60
        val minutesInHour = 60

        // Perform time conversion based on the selected options
        return when {
            // second to MileSecond
            fromOption == "Second" && toOption == "MileSecond" ->
                inputNumber * 1000
            // second to minute
            fromOption == "Second" && toOption == "Minute" ->
                inputNumber / secondsInMinute
            // second to hour
            fromOption == "Second" && toOption == "Hour" ->
                inputNumber / (secondsInMinute * minutesInHour)
            // minute to second
            fromOption == "Minute" && toOption == "Second" ->
                inputNumber * secondsInMinute
            // minute to hour
            fromOption == "Minute" && toOption == "Hour" ->
                inputNumber / minutesInHour
            // hour to second
            fromOption == "Hour" && toOption == "Second" ->
                inputNumber * (minutesInHour * secondsInMinute)
            // hour to minute
            fromOption == "Hour" && toOption == "Minute" ->
                inputNumber * minutesInHour

            else -> inputNumber // Default case: return the input as is
        }
    }

}
