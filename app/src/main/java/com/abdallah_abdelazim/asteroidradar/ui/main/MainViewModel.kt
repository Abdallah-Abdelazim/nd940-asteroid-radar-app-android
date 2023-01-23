package com.abdallah_abdelazim.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdallah_abdelazim.asteroidradar.data.repository.nasa.NasaRepository
import com.abdallah_abdelazim.asteroidradar.ui.mapper.toAsteroidUiModel
import com.abdallah_abdelazim.asteroidradar.ui.mapper.toPictureOfDayUiModel
import com.abdallah_abdelazim.asteroidradar.ui.model.AsteroidUiModel
import com.abdallah_abdelazim.asteroidradar.ui.model.PictureOfDayUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MainViewModel(private val nasaRepository: NasaRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState>
        get() = _uiState.asStateFlow()

    val currentState
        get() = uiState.value

    init {
        viewModelScope.launch {
            combine(
                nasaRepository.getCachedNearEarthObjects(),
                nasaRepository.getCachedNasaPictureOfDay()
            ) { asteroidEntities, pictureOfDayEntity ->
                _uiState.value = MainUiState(
                    false,
                    toPictureOfDayUiModel(pictureOfDayEntity),
                    asteroidEntities.map { toAsteroidUiModel(it) }
                )
            }
        }
    }

}

data class MainUiState(
    val isLoading: Boolean = true,
    val pictureOfDay: PictureOfDayUiModel? = null,
    val asteroids: List<AsteroidUiModel>? = null
)