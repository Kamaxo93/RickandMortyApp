package com.camacho.rickandmortyapp.domain.usecase

import android.content.SharedPreferences
import com.camacho.rickandmortyapp.core.di.MySharedPrefs
import com.camacho.rickandmortyapp.data.constan.Constant.MAX_NUMBER_CHARACTER

interface GetTotalsCharacters {
    suspend operator fun invoke(): Int
}

class GetTotalsCharactersImpl(@MySharedPrefs private val sharedPreferences: SharedPreferences) :
    GetTotalsCharacters {

    override suspend fun invoke() = sharedPreferences.getInt(MAX_NUMBER_CHARACTER, 0)

}