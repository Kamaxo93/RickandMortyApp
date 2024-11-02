package com.camacho.rickandmortyapp.ui.home.viewmodel

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camacho.rickandmortyapp.core.async.AsyncResult
import com.camacho.rickandmortyapp.domain.usecase.AddCharactersToLocalUseCase
import com.camacho.rickandmortyapp.domain.usecase.GetAllCharactersUseCase
import com.camacho.rickandmortyapp.domain.usecase.GetTotalsCharacters
import com.camacho.rickandmortyapp.ui.home.uistate.RickAndMortyHomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val addCharactersToLocalUseCase: AddCharactersToLocalUseCase,
    private val getTotalsCharacters: GetTotalsCharacters
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

    fun reload() {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                state = state.copy(error = null, characters = null, isLoading = true)
            }
            getAllCharactersUseCase().collect { characters ->
                if (characters.isNotEmpty() &&
                    getTotalsCharacters() == characters.size
                ) {
                    state = state.copy(characters = characters, isLoading = false, error = null)

                } else {
                    addCharactersToLocalUseCase().collect {
                        if (it is AsyncResult.Error) {
                            state = state.copy(error = it.error.toString(), isLoading = false)
                        }
                    }
                }
            }
        }
    }
}