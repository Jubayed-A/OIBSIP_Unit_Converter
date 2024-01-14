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
//            Toast.makeText(requireContext(), "You Selected $selectedOption", Toast.LENGTH_SHORT).show()
            binding.fromSectionText.text = selectedOption
        }

        // to menu drop down section
        val arrayAdapter2 = ArrayAdapter(requireContext(), R.layout.drop_down_item, toMenu)
        binding.toSection.setAdapter(arrayAdapter2)
        binding.toSection.setOnItemClickListener { parent, view, position, id ->
            val selectedOption = parent.getItemAtPosition(position).toString()
//            Toast.makeText(requireContext(), "You Selected $selectedOption", Toast.LENGTH_SHORT).show()
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
//                Toast.makeText(requireContext(), "Result: $result", Toast.LENGTH_SHORT).show()
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
    private fun performTimeConversion(inputNumber: Double, fromOption: String, toOption: String): Any {
        // Conversion factors
        val secondsInMinute = 60
        val minutesInHour = 60

        // Perform time conversion based on the selected options
        return when {
            // second to MileSecond
            fromOption == "Second" && toOption == "MileSecond" ->
                inputNumber * 1000
            // second to MicroSecond
            fromOption == "Second" && toOption == "MicroSecond" ->
                inputNumber * 1000000
            // second to NanoSecond
            fromOption == "Second" && toOption == "NanoSecond" ->
                inputNumber * 1000000000
            // second to minute
            fromOption == "Second" && toOption == "Minute" ->
                inputNumber / secondsInMinute
            // second to hour
            fromOption == "Second" && toOption == "Hour" ->
                inputNumber / (secondsInMinute * minutesInHour)
            // second to Day
            fromOption == "Second" && toOption == "Day" -> {
                inputNumber / 86400
            }
            // second to Week
            fromOption == "Second" && toOption == "Week" -> {
                inputNumber / (86400 * 7)
            }
            // second to FortNight
            fromOption == "Second" && toOption == "FortNight" -> {
                inputNumber / (86400 * 14)
            }
            // second to Month
            fromOption == "Second" && toOption == "Month" -> {
                inputNumber / (86400 * 30)
            }
            // second to Year
            fromOption == "Second" && toOption == "Year" -> {
                inputNumber / (86400 * 365)
            }
            // second to LeapYear
            fromOption == "Second" && toOption == "LeapYear" -> {
                inputNumber / (86400 * 366)
            }
            // second to Decade
            fromOption == "Second" && toOption == "Decade" -> {
                inputNumber / (31536000 * 10)
            }
            // second to Century
            fromOption == "Second" && toOption == "Century" -> {
                if (inputNumber <= 10000000){
                    inputNumber * 0.0
                    Toast.makeText(requireContext(), "Input larger then 10,000,000", Toast.LENGTH_SHORT).show()
                }else{
                    inputNumber * (1.0 / (31536000 * 100))
                }
            }

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
            // hour to minute
            fromOption == "Hour" && toOption == "MileSecond" ->
                inputNumber * minutesInHour

            else -> inputNumber // Default case: return the input as is
        }
    }

}
