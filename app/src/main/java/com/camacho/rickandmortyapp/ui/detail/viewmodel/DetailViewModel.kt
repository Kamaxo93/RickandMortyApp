package com.camacho.rickandmortyapp.ui.detail.viewmodel

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camacho.rickandmortyapp.domain.usecase.GetCharacterUseCase
import com.camacho.rickandmortyapp.ui.detail.model.toVO
import com.camacho.rickandmortyapp.ui.detail.uistate.RickAndMortyDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
) : ViewModel() {

    var state by mutableStateOf(RickAndMortyDetailState())
        private set

    private var isInitialized = false

    @MainThread
    fun initializeDataState(id: String) {
        if (isInitialized) return
        isInitialized = true
        getCharacter(id)
    }

    private fun getCharacter(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterUseCase(id).collect { response ->
                state = state.copy(character = response.toVO(), isLoading = false)
            }
        }
    }
}