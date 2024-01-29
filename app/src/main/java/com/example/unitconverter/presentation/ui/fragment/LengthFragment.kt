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
                fromUnit == "Meter" && toUnit == "Millimeter" -> meterToMillimeter(inputLength)
                fromUnit == "Meter" && toUnit == "Micrometer" -> meterToMicrometer(inputLength)
                fromUnit == "Meter" && toUnit == "Nanometer" -> meterToNanometer(inputLength)
                fromUnit == "Meter" && toUnit == "Kilometer" -> meterToKilometer(inputLength)

                fromUnit == "Centimeter" && toUnit == "Meter" -> centimeterToMeter(inputLength)
                fromUnit == "Centimeter" && toUnit == "Millimeter" -> centimeterToMillimeter(
                    inputLength
                )

                fromUnit == "Centimeter" && toUnit == "Micrometer" -> centimeterToMicrometer(
                    inputLength
                )

                fromUnit == "Centimeter" && toUnit == "Nanometer" -> centimeterToNanometer(
                    inputLength
                )

                fromUnit == "Centimeter" && toUnit == "Kilometer" -> centimeterToKilometer(
                    inputLength
                )

                fromUnit == "Millimeter" && toUnit == "Meter" -> millimeterToMeter(inputLength)
                fromUnit == "Millimeter" && toUnit == "Centimeter" -> millimeterToCentimeter(
                    inputLength
                )

                fromUnit == "Millimeter" && toUnit == "Micrometer" -> millimeterToMicrometer(
                    inputLength
                )

                fromUnit == "Millimeter" && toUnit == "Nanometer" -> millimeterToNanometer(
                    inputLength
                )

                fromUnit == "Millimeter" && toUnit == "Kilometer" -> millimeterToKilometer(
                    inputLength
                )

                fromUnit == "Micrometer" && toUnit == "Meter" -> micrometerToMeter(inputLength)
                fromUnit == "Micrometer" && toUnit == "Centimeter" -> micrometerToCentimeter(
                    inputLength
                )

                fromUnit == "Micrometer" && toUnit == "Millimeter" -> micrometerToMillimeter(
                    inputLength
                )

                fromUnit == "Micrometer" && toUnit == "Nanometer" -> micrometerToNanometer(
                    inputLength
                )

                fromUnit == "Micrometer" && toUnit == "Kilometer" -> micrometerToKilometer(
                    inputLength
                )

                fromUnit == "Nanometer" && toUnit == "Meter" -> nanometerToMeter(inputLength)
                fromUnit == "Nanometer" && toUnit == "Centimeter" -> nanometerToCentimeter(
                    inputLength
                )

                fromUnit == "Nanometer" && toUnit == "Millimeter" -> nanometerToMillimeter(
                    inputLength
                )

                fromUnit == "Nanometer" && toUnit == "Micrometer" -> nanometerToMicrometer(
                    inputLength
                )

                fromUnit == "Nanometer" && toUnit == "Kilometer" -> nanometerToKilometer(inputLength)

                fromUnit == "Kilometer" && toUnit == "Meter" -> kilometerToMeter(inputLength)
                fromUnit == "Kilometer" && toUnit == "Centimeter" -> kilometerToCentimeter(
                    inputLength
                )

                fromUnit == "Kilometer" && toUnit == "Millimeter" -> kilometerToMillimeter(
                    inputLength
                )

                fromUnit == "Kilometer" && toUnit == "Micrometer" -> kilometerToMicrometer(
                    inputLength
                )

                fromUnit == "Kilometer" && toUnit == "Nanometer" -> kilometerToNanometer(inputLength)

                // Add more conversion cases here
                else -> inputLength
            }

            binding.outputFrom.text = String.format("%.2f %s", inputLength, fromUnit)
            binding.outputTo.text = String.format("%.2f %s", convertedLength, toUnit)
            binding.welcomeOutput.text = "$fromUnit To $toUnit\nConversion Result"
            binding.outputCardView.visibility = View.VISIBLE
        }
    }

    // meter section start here
    private fun meterToCentimeter(meter: Float): Float {
        return meter * 100
    }

    private fun meterToMillimeter(meter: Float): Float {
        return meter * 1000
    }

    private fun meterToMicrometer(meter: Float): Float {
        return meter * 1_000_000
    }

    private fun meterToNanometer(meter: Float): Float {
        return meter * 1_000_000_000
    }

    private fun meterToKilometer(meter: Float): Float {
        return meter / 1000
    }
    // meter section end here

    // centimeter section start here
    private fun centimeterToMeter(centimeter: Float): Float {
        return centimeter / 100
    }

    private fun centimeterToMillimeter(centimeter: Float): Float {
        return centimeter * 10
    }

    private fun centimeterToMicrometer(centimeter: Float): Float {
        return centimeter * 10000
    }

    private fun centimeterToNanometer(centimeter: Float): Float {
        return centimeter * 10000000
    }

    private fun centimeterToKilometer(centimeter: Float): Float {
        return centimeter / 100000
    }
    // centimeter section end here

    // millimeter section start here
    private fun millimeterToMeter(millimeter: Float): Float {
        return millimeter / 1000
    }

    private fun millimeterToCentimeter(millimeter: Float): Float {
        return millimeter / 10
    }

    private fun millimeterToMicrometer(millimeter: Float): Float {
        return millimeter * 1000
    }

    private fun millimeterToNanometer(millimeter: Float): Float {
        return millimeter * 1000000
    }

    private fun millimeterToKilometer(millimeter: Float): Float {
        return millimeter / 1000000
    }
    // millimeter section end here

    // micrometer section start here
    private fun micrometerToMeter(micrometer: Float): Float {
        return micrometer / 1000000
    }

    private fun micrometerToCentimeter(micrometer: Float): Float {
        return micrometer / 10000
    }

    private fun micrometerToMillimeter(micrometer: Float): Float {
        return micrometer / 1000
    }

    private fun micrometerToNanometer(micrometer: Float): Float {
        return micrometer * 1000
    }

    private fun micrometerToKilometer(micrometer: Float): Float {
        return micrometer / 1000000000
    }
    // micrometer section end here

    //    for nano meter section start here
    private fun nanometerToMeter(nanometer: Float): Float {
        return nanometer / 1000000000
    }

    private fun nanometerToCentimeter(nanometer: Float): Float {
        return nanometer / 10000000
    }

    private fun nanometerToMillimeter(nanometer: Float): Float {
        return nanometer / 1000000
    }

    private fun nanometerToMicrometer(nanometer: Float): Float {
        return nanometer / 1000
    }

    private fun nanometerToKilometer(nanometer: Float): Float {
        return nanometer / 1000000000000
    }
    // nanometer section end here

    // kilometer section start here
    private fun kilometerToMeter(kilometer: Float): Float {
        return kilometer * 1000
    }

    private fun kilometerToCentimeter(kilometer: Float): Float {
        return kilometer * 100000
    }

    private fun kilometerToMillimeter(kilometer: Float): Float {
        return kilometer * 1000000
    }

    private fun kilometerToMicrometer(kilometer: Float): Float {
        return kilometer * 1000000000
    }

    private fun kilometerToNanometer(kilometer: Float): Float {
        return kilometer * 1000000000000
    }
    // kilometer section end here

}