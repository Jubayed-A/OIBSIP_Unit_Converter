package com.example.unitconverter.data.repository

import com.example.unitconverter.data.models.CurrencyResponse
import com.example.unitconverter.util.Resource

interface MainRepository {

    suspend fun getRates(base: String): Resource<CurrencyResponse>


}