package com.example.unitconverter.presentation.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
    ): View {
        binding = FragmentTimeBinding.inflate(layoutInflater, container, false)

        // time conversion section
        val fromMenu = resources.getStringArray(R.array.time_name)
        val toMenu = resources.getStringArray(R.array.time_name)

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

                // Set the outputCardView to visible
                binding.outputCardView.visibility = View.VISIBLE

                binding.outputFrom.text = "$inputNumber  $fromMenuOption"
                binding.outputTo.text = "$result $toMenuOption"
                binding.welcomeOutput.text = "$fromMenuOption To $toMenuOption \nConversion Result"
            } else {
                // Handle the case when the input is not a valid number
                Toast.makeText(
                    requireContext(),
                    "Invalid input. Please enter a valid number.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }

    // Function to perform time conversion
    private fun performTimeConversion(
        inputNumber: Double,
        fromOption: String,
        toOption: String
    ): Any {
        // Conversion factors
        val secondsInMinute = 60
        val minutesInHour = 60

        // Perform time conversion based on the selected options
        return when {
            // second section start here
            // second to Millisecond
            fromOption == "Second" && toOption == "Millisecond" ->
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
                if (inputNumber <= 10000000) {
                    inputNumber * 0.0
                    Toast.makeText(
                        requireContext(),
                        "Input larger then 10,000,000",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    inputNumber * (1.0 / (31536000 * 100))
                }
            }

            // Millisecond section start here
            // Millisecond to Second
            fromOption == "Millisecond" && toOption == "Second" ->
                inputNumber / 1000.0

            // Millisecond to Microsecond
            fromOption == "Millisecond" && toOption == "MicroSecond" ->
                inputNumber * 1000.0

            // Millisecond to NanoSecond
            fromOption == "Millisecond" && toOption == "NanoSecond" ->
                inputNumber * 1_000_000.0

            // Millisecond to Minute
            fromOption == "Millisecond" && toOption == "Minute" ->
                inputNumber / (1000.0 * 60)

            // Millisecond to Hour
            fromOption == "Millisecond" && toOption == "Hour" ->
                inputNumber / (1000.0 * 60 * 60)

            // Millisecond to Day
            fromOption == "Millisecond" && toOption == "Day" ->
                inputNumber / (1000.0 * 60 * 60 * 24)

            // Millisecond to Week
            fromOption == "Millisecond" && toOption == "Week" ->
                inputNumber / (1000.0 * 60 * 60 * 24 * 7)

            // Millisecond to Fortnight
            fromOption == "Millisecond" && toOption == "Fortnight" ->
                inputNumber / (1000.0 * 60 * 60 * 24 * 14)

            // Millisecond to Month
            fromOption == "Millisecond" && toOption == "Month" ->
                inputNumber / (1000.0 * 60 * 60 * 24 * 30)

            // Millisecond to Year
            fromOption == "Millisecond" && toOption == "Year" ->
                inputNumber / (1000.0 * 60 * 60 * 24 * 365)

            // Millisecond to LeapYear
            fromOption == "Millisecond" && toOption == "LeapYear" ->
                inputNumber / (1000.0 * 60 * 60 * 24 * 366)

            // Millisecond to Decade
            fromOption == "Millisecond" && toOption == "Decade" ->
                inputNumber / (1000.0 * 60 * 60 * 24 * 365 * 10)

            // Millisecond to Century
            fromOption == "Millisecond" && toOption == "Century" ->
                inputNumber / (1000.0 * 60 * 60 * 24 * 365 * 100)

            // Microsecond section start here
            // Microsecond to Second
            fromOption == "MicroSecond" && toOption == "Second" ->
                inputNumber / 1_000_000.0

            // Microsecond to Millisecond
            fromOption == "MicroSecond" && toOption == "Millisecond" ->
                inputNumber / 1_000.0

            // Microsecond to Nanosecond
            fromOption == "MicroSecond" && toOption == "NanoSecond" ->
                inputNumber * 1_000

            // Microsecond to Minute
            fromOption == "MicroSecond" && toOption == "Minute" ->
                inputNumber / (1_000_000.0 * 60)

            // Microsecond to Hour
            fromOption == "MicroSecond" && toOption == "Hour" ->
                inputNumber / (1_000_000.0 * 60 * 60)

            // Microsecond to Day
            fromOption == "MicroSecond" && toOption == "Day" ->
                inputNumber / (1_000_000.0 * 60 * 60 * 24)

            // Microsecond to Week
            fromOption == "MicroSecond" && toOption == "Week" ->
                inputNumber / (1_000_000.0 * 60 * 60 * 24 * 7)

            // Microsecond to Fortnight
            fromOption == "MicroSecond" && toOption == "Fortnight" ->
                inputNumber / (1_000_000.0 * 60 * 60 * 24 * 14)

            // Microsecond to Month
            fromOption == "MicroSecond" && toOption == "Month" ->
                inputNumber / (1_000_000.0 * 60 * 60 * 24 * 30)

            // Microsecond to Year
            fromOption == "MicroSecond" && toOption == "Year" ->
                inputNumber / (1_000_000.0 * 60 * 60 * 24 * 365)

            // Microsecond to LeapYear
            fromOption == "MicroSecond" && toOption == "LeapYear" ->
                inputNumber / (1_000_000.0 * 60 * 60 * 24 * 366)

            // Microsecond to Decade
            fromOption == "MicroSecond" && toOption == "Decade" ->
                inputNumber / (1_000_000.0 * 60 * 60 * 24 * 365 * 10)

            // Microsecond to Century
            fromOption == "MicroSecond" && toOption == "Century" ->
                inputNumber / (1_000_000.0 * 60 * 60 * 24 * 365 * 100)

            // nanosecond section start here
            // Nanosecond to Second
            fromOption == "NanoSecond" && toOption == "Second" ->
                inputNumber / 1_000_000_000.0

            // Nanosecond to Millisecond
            fromOption == "NanoSecond" && toOption == "Millisecond" ->
                inputNumber / 1_000_000.0

            // Nanosecond to Microsecond
            fromOption == "NanoSecond" && toOption == "MicroSecond" ->
                inputNumber / 1_000.0

            // Nanosecond to Minute
            fromOption == "NanoSecond" && toOption == "Minute" ->
                inputNumber / (1_000_000_000.0 * 60.0)

            // Nanosecond to Hour
            fromOption == "NanoSecond" && toOption == "Hour" ->
                inputNumber / (1_000_000_000.0 * 60.0 * 60.0)

            // Nanosecond to Day
            fromOption == "NanoSecond" && toOption == "Day" ->
                inputNumber / (1_000_000_000.0 * 60.0 * 60.0 * 24.0)

            // Nanosecond to Week
            fromOption == "NanoSecond" && toOption == "Week" ->
                inputNumber / (1_000_000_000.0 * 60.0 * 60.0 * 24.0 * 7.0)

            // Nanosecond to Fortnight
            fromOption == "NanoSecond" && toOption == "Fortnight" ->
                inputNumber / (1_000_000_000.0 * 60.0 * 60.0 * 24.0 * 14.0)

            // Nanosecond to Month
            fromOption == "NanoSecond" && toOption == "Month" ->
                inputNumber / (1_000_000_000.0 * 60.0 * 60.0 * 24.0 * 30.4375)  // Approximation based on average month length

            // Nanosecond to Year
            fromOption == "NanoSecond" && toOption == "Year" ->
                inputNumber / (1_000_000_000.0 * 60.0 * 60.0 * 24.0 * 365.0)

            // Nanosecond to LeapYear
            fromOption == "NanoSecond" && toOption == "LeapYear" ->
                inputNumber / (1_000_000_000.0 * 60.0 * 60.0 * 24.0 * 366.0)

            // Nanosecond to Decade
            fromOption == "NanoSecond" && toOption == "Decade" ->
                inputNumber / (1_000_000_000.0 * 60.0 * 60.0 * 24.0 * 365.0 * 10.0)

            // Nanosecond to Century
            fromOption == "NanoSecond" && toOption == "Century" ->
                inputNumber / (1_000_000_000.0 * 60.0 * 60.0 * 24.0 * 365.2425 * 100.0)  // Using the average number of days per year


            // Minute section start here
            // Minute to Second
            fromOption == "Minute" && toOption == "Second" ->
                inputNumber * 60.0

            // Minute to Millisecond
            fromOption == "Minute" && toOption == "Millisecond" ->
                inputNumber * (60.0 * 1000)

            // Minute to Microsecond
            fromOption == "Minute" && toOption == "MicroSecond" ->
                inputNumber * (60.0 * 1000 * 1000)

            // Minute to NanoSecond
            fromOption == "Minute" && toOption == "NanoSecond" ->
                inputNumber * (60.0 * 1000 * 1000 * 1000)

            // Minute to Hour
            fromOption == "Minute" && toOption == "Hour" ->
                inputNumber / 60.0

            // Minute to Day
            fromOption == "Minute" && toOption == "Day" ->
                inputNumber / (60.0 * 24)

            // Minute to Week
            fromOption == "Minute" && toOption == "Week" ->
                inputNumber / (60.0 * 24 * 7)

            // Minute to Fortnight
            fromOption == "Minute" && toOption == "Fortnight" ->
                inputNumber / (60.0 * 24 * 14)

            // Minute to Month
            fromOption == "Minute" && toOption == "Month" ->
                inputNumber / (60.0 * 24 * 30)

            // Minute to Year
            fromOption == "Minute" && toOption == "Year" ->
                inputNumber / (60.0 * 24 * 365)

            // Minute to LeapYear
            fromOption == "Minute" && toOption == "LeapYear" ->
                inputNumber / (60.0 * 24 * 366)

            // Minute to Decade
            fromOption == "Minute" && toOption == "Decade" ->
                inputNumber / (60.0 * 24 * 365 * 10)

            // Minute to Century
            fromOption == "Minute" && toOption == "Century" ->
                inputNumber / (60.0 * 24 * 365 * 100)

            // hour section start code here
            // Hour to Second
            fromOption == "Hour" && toOption == "Second" ->
                inputNumber * 60 * 60

            // Hour to Millisecond
            fromOption == "Hour" && toOption == "Millisecond" ->
                inputNumber * 60 * 60 * 1_000

            // Hour to Microsecond
            fromOption == "Hour" && toOption == "MicroSecond" ->
                inputNumber * 60 * 60 * 1_000_000

            // Hour to Minute
            fromOption == "Hour" && toOption == "Minute" ->
                inputNumber * 60

            // Hour to Nanosecond
            fromOption == "Hour" && toOption == "NanoSecond" ->
                inputNumber * 60 * 60 * 1_000_000_000

            // Hour to Day
            fromOption == "Hour" && toOption == "Day" ->
                inputNumber / 24.0

            // Hour to Week
            fromOption == "Hour" && toOption == "Week" ->
                inputNumber / (24.0 * 7)

            // Hour to Fortnight
            fromOption == "Hour" && toOption == "Fortnight" ->
                inputNumber / (24.0 * 14)

            // Hour to Month
            fromOption == "Hour" && toOption == "Month" ->
                inputNumber / (24.0 * 30)

            // Hour to Year
            fromOption == "Hour" && toOption == "Year" ->
                inputNumber / (24.0 * 365)

            // Hour to LeapYear
            fromOption == "Hour" && toOption == "LeapYear" ->
                inputNumber / (24.0 * 366)

            // Hour to Decade
            fromOption == "Hour" && toOption == "Decade" ->
                inputNumber / (24.0 * 365 * 10)

            // Hour to Century
            fromOption == "Hour" && toOption == "Century" ->
                inputNumber / (24.0 * 365 * 100)

            // day section code here
            // Day to Second
            fromOption == "Day" && toOption == "Second" ->
                inputNumber * 24 * 60 * 60

// Day to Millisecond
            fromOption == "Day" && toOption == "Millisecond" ->
                inputNumber * 24 * 60 * 60 * 1_000

// Day to Microsecond
            fromOption == "Day" && toOption == "MicroSecond" ->
                inputNumber * 24 * 60 * 60 * 1_000_000

// Day to Minute
            fromOption == "Day" && toOption == "Minute" ->
                inputNumber * 24 * 60

// Day to Hour
            fromOption == "Day" && toOption == "Hour" ->
                inputNumber * 24.0

// Day to Nanosecond
            fromOption == "Day" && toOption == "NanoSecond" ->
                inputNumber * 24 * 60 * 60 * 1_000_000_000

// Day to Week
            fromOption == "Day" && toOption == "Week" ->
                inputNumber / 7.0

// Day to Fortnight
            fromOption == "Day" && toOption == "Fortnight" ->
                inputNumber / 14.0

// Day to Month
            fromOption == "Day" && toOption == "Month" ->
                inputNumber / 30.0

// Day to Year
            fromOption == "Day" && toOption == "Year" ->
                inputNumber / 365.0

// Day to LeapYear
            fromOption == "Day" && toOption == "LeapYear" ->
                inputNumber / 366.0

// Day to Decade
            fromOption == "Day" && toOption == "Decade" ->
                inputNumber / (365.0 * 10)

// Day to Century
            fromOption == "Day" && toOption == "Century" ->
                inputNumber / (365.0 * 100)

            // week section code here
            // Week to Second
            fromOption == "Week" && toOption == "Second" ->
                inputNumber * 7 * 24 * 60 * 60

// Week to Millisecond
            fromOption == "Week" && toOption == "Millisecond" ->
                inputNumber * 7 * 24 * 60 * 60 * 1_000

// Week to Microsecond
            fromOption == "Week" && toOption == "MicroSecond" ->
                inputNumber * 7 * 24 * 60 * 60 * 1_000_000

// Week to Minute
            fromOption == "Week" && toOption == "Minute" ->
                inputNumber * 7 * 24 * 60

// Week to Hour
            fromOption == "Week" && toOption == "Hour" ->
                inputNumber * 7 * 24.0

// Week to Day
            fromOption == "Week" && toOption == "Day" ->
                inputNumber * 7.0

// Week to Nanosecond
            fromOption == "Week" && toOption == "NanoSecond" ->
                inputNumber * 7 * 24 * 60 * 60 * 1_000_000_000

// Week to Fortnight
            fromOption == "Week" && toOption == "Fortnight" ->
                inputNumber / 2.0

// Week to Month
            fromOption == "Week" && toOption == "Month" ->
                inputNumber / (30.0 / 7.0)  // Approximate conversion, as months vary in length

// Week to Year
            fromOption == "Week" && toOption == "Year" ->
                inputNumber / 52.1429  // Average number of weeks in a year

// Week to LeapYear
            fromOption == "Week" && toOption == "LeapYear" ->
                inputNumber / 52.2857  // Average number of weeks in a leap year

// Week to Decade
            fromOption == "Week" && toOption == "Decade" ->
                inputNumber / (52.1429 * 10)

// Week to Century
            fromOption == "Week" && toOption == "Century" ->
                inputNumber / (52.1429 * 100)

            // fortnight section code here
            // fortnight section start here

// Fortnight to Second
            fromOption == "Fortnight" && toOption == "Second" ->
                inputNumber * 14 * 24 * 60 * 60

// Fortnight to Millisecond
            fromOption == "Fortnight" && toOption == "Millisecond" ->
                inputNumber * 14 * 24 * 60 * 60 * 1_000

// Fortnight to Microsecond
            fromOption == "Fortnight" && toOption == "MicroSecond" ->
                inputNumber * 14 * 24 * 60 * 60 * 1_000_000

// Fortnight to Minute
            fromOption == "Fortnight" && toOption == "Minute" ->
                inputNumber * 14 * 24 * 60

// Fortnight to Hour
            fromOption == "Fortnight" && toOption == "Hour" ->
                inputNumber * 14 * 24.0

// Fortnight to Day
            fromOption == "Fortnight" && toOption == "Day" ->
                inputNumber * 14.0

// Fortnight to Week
            fromOption == "Fortnight" && toOption == "Week" ->
                inputNumber * 2.0

// Fortnight to Nanosecond
            fromOption == "Fortnight" && toOption == "NanoSecond" ->
                inputNumber * 14 * 24 * 60 * 60 * 1_000_000_000

// Fortnight to Month
            fromOption == "Fortnight" && toOption == "Month" ->
                inputNumber / (30.0 / 14.0)  // Approximate conversion, as months vary in length

// Fortnight to Year
            fromOption == "Fortnight" && toOption == "Year" ->
                inputNumber / (52.1429 / 2.0)  // Average number of fortnights in a year

// Fortnight to LeapYear
            fromOption == "Fortnight" && toOption == "LeapYear" ->
                inputNumber / (52.2857 / 2.0)  // Average number of fortnights in a leap year

// Fortnight to Decade
            fromOption == "Fortnight" && toOption == "Decade" ->
                inputNumber / ((52.1429 * 10) / 2.0)

// Fortnight to Century
            fromOption == "Fortnight" && toOption == "Century" ->
                inputNumber / ((52.1429 * 100) / 2.0)

            // month section start here
            // Month to Second
            fromOption == "Month" && toOption == "Second" ->
                inputNumber * 30.4375 * 24 * 60 * 60  // Approximation based on average month length

// Month to Millisecond
            fromOption == "Month" && toOption == "Millisecond" ->
                inputNumber * 30.4375 * 24 * 60 * 60 * 1_000

// Month to Microsecond
            fromOption == "Month" && toOption == "MicroSecond" ->
                inputNumber * 30.4375 * 24 * 60 * 60 * 1_000_000

// Month to Minute
            fromOption == "Month" && toOption == "Minute" ->
                inputNumber * 30.4375 * 24 * 60

// Month to Hour
            fromOption == "Month" && toOption == "Hour" ->
                inputNumber * 30.4375 * 24.0

// Month to Day
            fromOption == "Month" && toOption == "Day" ->
                inputNumber * 30.4375

// Month to Week
            fromOption == "Month" && toOption == "Week" ->
                inputNumber * (30.4375 / 7.0)

// Month to Fortnight
            fromOption == "Month" && toOption == "Fortnight" ->
                inputNumber * (30.4375 / 14.0)

// Month to Nanosecond
            fromOption == "Month" && toOption == "NanoSecond" ->
                inputNumber * 30.4375 * 24 * 60 * 60 * 1_000_000_000

// Month to Year
            fromOption == "Month" && toOption == "Year" ->
                inputNumber / 12.0

// Month to LeapYear
            fromOption == "Month" && toOption == "LeapYear" ->
                inputNumber / 12.0  // Same division, as leap years have the same number of months

// Month to Decade
            fromOption == "Month" && toOption == "Decade" ->
                inputNumber / 120.0

// Month to Century
            fromOption == "Month" && toOption == "Century" ->
                inputNumber / 1200.0

            // year section start here
            // Year to Second
            fromOption == "Year" && toOption == "Second" ->
                inputNumber * 365 * 24 * 60 * 60

// Year to Millisecond
            fromOption == "Year" && toOption == "Millisecond" ->
                inputNumber * 365 * 24 * 60 * 60 * 1_000

// Year to Microsecond
            fromOption == "Year" && toOption == "MicroSecond" ->
                inputNumber * 365 * 24 * 60 * 60 * 1_000_000

// Year to Minute
            fromOption == "Year" && toOption == "Minute" ->
                inputNumber * 365 * 24 * 60

// Year to Hour
            fromOption == "Year" && toOption == "Hour" ->
                inputNumber * 365 * 24.0

// Year to Day
            fromOption == "Year" && toOption == "Day" ->
                inputNumber * 365.0

// Year to Week
            fromOption == "Year" && toOption == "Week" ->
                inputNumber * 52.1429  // Average number of weeks in a year

// Year to Fortnight
            fromOption == "Year" && toOption == "Fortnight" ->
                inputNumber * (52.1429 / 2.0)  // Average number of fortnights in a year

// Year to Month
            fromOption == "Year" && toOption == "Month" ->
                inputNumber * 12.0

// Year to Nanosecond
            fromOption == "Year" && toOption == "NanoSecond" ->
                inputNumber * 365 * 24 * 60 * 60 * 1_000_000_000

// Year to LeapYear
            fromOption == "Year" && toOption == "LeapYear" ->
                inputNumber * 366 / 365.0  // Conversion based on the ratio of days in a leap year to a regular year

// Year to Decade
            fromOption == "Year" && toOption == "Decade" ->
                inputNumber / 10.0

// Year to Century
            fromOption == "Year" && toOption == "Century" ->
                inputNumber / 100.0

            // leap year section here
            // LeapYear to Second
            fromOption == "LeapYear" && toOption == "Second" ->
                inputNumber * 366 * 24 * 60 * 60

// LeapYear to Millisecond
            fromOption == "LeapYear" && toOption == "Millisecond" ->
                inputNumber * 366 * 24 * 60 * 60 * 1_000

// LeapYear to Microsecond
            fromOption == "LeapYear" && toOption == "MicroSecond" ->
                inputNumber * 366 * 24 * 60 * 60 * 1_000_000

// LeapYear to Minute
            fromOption == "LeapYear" && toOption == "Minute" ->
                inputNumber * 366 * 24 * 60

// LeapYear to Hour
            fromOption == "LeapYear" && toOption == "Hour" ->
                inputNumber * 366 * 24.0

// LeapYear to Day
            fromOption == "LeapYear" && toOption == "Day" ->
                inputNumber * 366.0

// LeapYear to Week
            fromOption == "LeapYear" && toOption == "Week" ->
                inputNumber * 52.2857  // Average number of weeks in a leap year

// LeapYear to Fortnight
            fromOption == "LeapYear" && toOption == "Fortnight" ->
                inputNumber * (52.2857 / 2.0)  // Average number of fortnights in a leap year

// LeapYear to Month
            fromOption == "LeapYear" && toOption == "Month" ->
                inputNumber * 12.0  // Same as regular years, as leap years have the same number of months

// LeapYear to Nanosecond
            fromOption == "LeapYear" && toOption == "NanoSecond" ->
                inputNumber * 366 * 24 * 60 * 60 * 1_000_000_000

// LeapYear to Year
            fromOption == "LeapYear" && toOption == "Year" ->
                inputNumber * 365 / 366.0  // Conversion based on the ratio of days in a regular year to a leap year

// LeapYear to Decade
            fromOption == "LeapYear" && toOption == "Decade" ->
                inputNumber / 10.0

// LeapYear to Century
            fromOption == "LeapYear" && toOption == "Century" ->
                inputNumber / 100.0

            // decade section here
            // Decade to Second
            fromOption == "Decade" && toOption == "Second" ->
                inputNumber * 10 * 365 * 24 * 60 * 60  // Assuming 10 regular years per decade

// Decade to Millisecond
            fromOption == "Decade" && toOption == "Millisecond" ->
                inputNumber * 10 * 365 * 24 * 60 * 60 * 1_000

// Decade to Microsecond
            fromOption == "Decade" && toOption == "MicroSecond" ->
                inputNumber * 10 * 365 * 24 * 60 * 60 * 1_000_000

// Decade to Minute
            fromOption == "Decade" && toOption == "Minute" ->
                inputNumber * 10 * 365 * 24 * 60

// Decade to Hour
            fromOption == "Decade" && toOption == "Hour" ->
                inputNumber * 10 * 365 * 24.0

// Decade to Day
            fromOption == "Decade" && toOption == "Day" ->
                inputNumber * 10 * 365.0

// Decade to Week
            fromOption == "Decade" && toOption == "Week" ->
                inputNumber * 10 * 52.1429  // Assuming an average of 52.1429 weeks per year

// Decade to Fortnight
            fromOption == "Decade" && toOption == "Fortnight" ->
                inputNumber * 10 * (52.1429 / 2.0)  // Assuming an average of 26.0715 fortnights per year

// Decade to Month
            fromOption == "Decade" && toOption == "Month" ->
                inputNumber * 10 * 12.0

// Decade to Nanosecond
            fromOption == "Decade" && toOption == "NanoSecond" ->
                inputNumber * 10 * 365 * 24 * 60 * 60 * 1_000_000_000

// Decade to Year
            fromOption == "Decade" && toOption == "Year" ->
                inputNumber * 10.0

// Decade to LeapYear
            fromOption == "Decade" && toOption == "LeapYear" ->
                inputNumber * 10 * 365 / 366.0  // Approximation, assuming an average of 2 leap years per decade

// Decade to Century
            fromOption == "Decade" && toOption == "Century" ->
                inputNumber / 10.0

            // century section code here
            // Century to Second
            fromOption == "Century" && toOption == "Second" ->
                inputNumber * 100 * 365.2425 * 24 * 60 * 60  // Using the average number of days per year (365.2425)

// Century to Millisecond
            fromOption == "Century" && toOption == "Millisecond" ->
                inputNumber * 100 * 365.2425 * 24 * 60 * 60 * 1_000

// Century to Microsecond
            fromOption == "Century" && toOption == "MicroSecond" ->
                inputNumber * 100 * 365.2425 * 24 * 60 * 60 * 1_000_000

// Century to Minute
            fromOption == "Century" && toOption == "Minute" ->
                inputNumber * 100 * 365.2425 * 24 * 60

// Century to Hour
            fromOption == "Century" && toOption == "Hour" ->
                inputNumber * 100 * 365.2425 * 24.0

// Century to Day
            fromOption == "Century" && toOption == "Day" ->
                inputNumber * 100 * 365.2425

// Century to Week
            fromOption == "Century" && toOption == "Week" ->
                inputNumber * 100 * 52.1775  // Using the average number of weeks per year (52.1775)

// Century to Fortnight
            fromOption == "Century" && toOption == "Fortnight" ->
                inputNumber * 100 * (52.1775 / 2.0)  // Using the average number of fortnights per year

// Century to Month
            fromOption == "Century" && toOption == "Month" ->
                inputNumber * 100 * 12.0

// Century to Nanosecond
            fromOption == "Century" && toOption == "NanoSecond" ->
                inputNumber * 100 * 365.2425 * 24 * 60 * 60 * 1_000_000_000

// Century to Year
            fromOption == "Century" && toOption == "Year" ->
                inputNumber * 100.0

// Century to LeapYear
            fromOption == "Century" && toOption == "LeapYear" ->
                inputNumber * 100 * 365.2425 / 366.0  // Approximation, assuming an average of 25 leap years per century

// Century to Decade
            fromOption == "Century" && toOption == "Decade" ->
                inputNumber * 10.0


            else -> inputNumber // Default case: return the input as is
        }
    }

}
