package com.camacho.rickandmortyapp.ui.home.viewmodel

import android.content.Context
import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camacho.rickandmortyapp.data.constan.Constant.MAX_NUMBER_CHARACTER
import com.camacho.rickandmortyapp.domain.usecase.AddCharactersToLocalUseCase
import com.camacho.rickandmortyapp.domain.usecase.GetAllCharactersUseCase
import com.camacho.rickandmortyapp.ui.home.uistate.RickAndMortyHomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val addCharactersToLocalUseCase: AddCharactersToLocalUseCase
) : ViewModel() {

    var state by mutableStateOf(RickAndMortyHomeState())
        private set

    private var isInitialized = false

    @MainThread
    fun initializeDataState() {
        if (isInitialized) return
        isInitialized = true
        getAllCharacters()
    }
    
    private fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                state = state.copy(error = null, characters = null, isLoading = true)
            }
            getAllCharactersUseCase().collect { characters ->
                if (characters.isNotEmpty() && characters.size == MAX_NUMBER_CHARACTER) {
                    state = state.copy(characters = characters, isLoading = false, error = null)

                } else {
                    addCharactersToLocalUseCase()
                }
            }
        }
    }
}