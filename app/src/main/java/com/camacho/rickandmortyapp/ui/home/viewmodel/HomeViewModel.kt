package com.camacho.rickandmortyapp.ui.home.viewmodel

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camacho.rickandmortyapp.core.async.AsyncResult
import com.camacho.rickandmortyapp.domain.model.CharacterDomain
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
    private val characters = mutableListOf<CharacterDomain>()
    private val genders = mutableListOf("none")
    private val species = mutableListOf("none")

    @MainThread
    fun initializeDataState() {
        if (isInitialized) return
        isInitialized = true
        getAllCharacters()
    }

    fun reload() {
        getAllCharacters()
    }

    fun getGenders(): List<String> = genders.toList()

    fun getSpecies(): List<String> = species.toList()


    private fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                state = state.copy(error = null, characters = null, isLoading = true)
            }
            getAllCharactersUseCase().collect { characters ->
                if (characters.isNotEmpty() &&
                    getTotalsCharacters() == characters.size
                ) {
                    this@HomeViewModel.characters.addAll(characters)
                    fillGenders(characters)
                    fillSpecies(characters)
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

    private fun fillGenders(characters: List<CharacterDomain>) {
        genders.addAll(
            characters.map { it.gender }.distinct()
        )
    }

    private fun fillSpecies(characters: List<CharacterDomain>) {
        species.addAll(
            characters.map { it.species }.distinct()
        )
    }

    fun filterCharactersByGender(gender: String) {
        if (gender == "none" || gender.isEmpty()) {
            state = state.copy(isLoading = false, characters = characters, error = null)
            return
        }
        state = state.copy(isLoading = false, characters = characters.filter { it.gender == gender }, error = null)
    }

    fun filterCharactersBySpecies(species: String) {
        if (species == "none" || species.isEmpty()) {
            state = state.copy(isLoading = false, characters = characters, error = null)
            return
        }
        state = state.copy(isLoading = false, characters = characters.filter { it.species == species }, error = null)

    }
}