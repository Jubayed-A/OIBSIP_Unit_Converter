package com.example.unitconverter.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.unitconverter.R
import com.example.unitconverter.databinding.FragmentCurrencyBinding
import com.example.unitconverter.presentation.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CurrencyFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyBinding.inflate(layoutInflater,container, false)

        binding.currencyConvert.setOnClickListener {
            viewModel.convert(
                binding.inputValue.text.toString(),
                binding.fromSection.selectionStart.toString(),
                binding.toSection.selectionStart.toString()
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect{ event ->
                when(event){
                    is MainViewModel.CurrencyEvent.Success -> {
                        binding.outputCardView.isVisible = true
                        binding.outputTo.text = event.resultText
                    }
                    is MainViewModel.CurrencyEvent.Failure -> {
                        binding.outputCardView.isVisible = false
                        Toast.makeText(requireContext(), event.errorText, Toast.LENGTH_SHORT).show()
                    }
                    is MainViewModel.CurrencyEvent.Loading ->{

                    }
                    else -> Unit
                }
            }
        }

        return binding.root
    }

}