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
import com.camacho.rickandmortyapp.ui.home.model.CharacterHomeVO
import com.camacho.rickandmortyapp.ui.home.model.toVO
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

    companion object {
        private const val NONE = "none"
        private const val ERROR_FILTER = "No se encontraron resultados"
    }

    var state by mutableStateOf(RickAndMortyHomeState())
        private set

    private var isInitialized = false
    private val charactersViewModel = mutableListOf<CharacterHomeVO>()
    private val genders = mutableListOf(NONE)
    private val species = mutableListOf(NONE)

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
                    charactersViewModel.addAll(characters.toVO())
                    fillGenders(characters)
                    fillSpecies(characters)
                    state = state.copy(characters = charactersViewModel, isLoading = false, error = null)

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

    fun filterCharacters(gender: String, species: String) {
        val listFilter = when {
            (gender != "none" && gender.isNotEmpty()) &&
                    (species != "none" && species.isNotEmpty()) -> {
                charactersViewModel.filter { it.gender == gender && it.species == species }
            }

            gender != "none" &&
                    gender.isNotEmpty() -> {
                charactersViewModel.filter { it.gender == gender }
            }

            species != "none" &&
                    species.isNotEmpty() -> {
                charactersViewModel.filter { it.species == species }
            }

            else -> {
                charactersViewModel
            }
        }
        state = if (listFilter.isNotEmpty()) {
            state.copy(isLoading = false, characters = listFilter, error = null)

        } else {
            state.copy(isLoading = false, characters = null, error = "No se encontraron resultados")
        }
    }
}