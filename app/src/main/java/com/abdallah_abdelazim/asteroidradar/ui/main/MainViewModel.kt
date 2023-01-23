package com.abdallah_abdelazim.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdallah_abdelazim.asteroidradar.data.repository.nasa.NasaRepository
import com.abdallah_abdelazim.asteroidradar.ui.mapper.toAsteroidUiModel
import com.abdallah_abdelazim.asteroidradar.ui.mapper.toPictureOfDayUiModel
import com.abdallah_abdelazim.asteroidradar.ui.model.AsteroidUiModel
import com.abdallah_abdelazim.asteroidradar.ui.model.PictureOfDayUiModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val nasaRepository: NasaRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState>
        get() = _uiState.asStateFlow()

    val currentState
        get() = uiState.value

    private lateinit var getDataJob: Job

    init {
        getDataJob = viewModelScope.launch {
            combine(
                nasaRepository.getUpcomingAsteroids(),
                nasaRepository.getNasaPictureOfDay()
            ) { asteroidEntities, pictureOfDayEntity ->
                _uiState.value = MainUiState(
                    false,
                    toPictureOfDayUiModel(pictureOfDayEntity),
                    asteroidEntities.map { toAsteroidUiModel(it) }
                )
            }.collect()
        }
    }

    fun showUpcomingAsteroids() {
        viewModelScope.launch {
            _uiState.value = MainUiState(isLoading = true)

            nasaRepository.refreshAllWeekData()

            getDataJob.cancel()
            getDataJob = viewModelScope.launch {
                combine(
                    nasaRepository.getUpcomingAsteroids(),
                    nasaRepository.getNasaPictureOfDay()
                ) { asteroidEntities, pictureOfDayEntity ->
                    _uiState.value = MainUiState(
                        false,
                        toPictureOfDayUiModel(pictureOfDayEntity),
                        asteroidEntities.map { toAsteroidUiModel(it) }
                    )
                }.collect()
            }
        }
    }

    fun showTodayAsteroids() {
        viewModelScope.launch {
            _uiState.value = MainUiState(isLoading = true)

            nasaRepository.refreshAllWeekData()

            getDataJob.cancel()
            getDataJob = viewModelScope.launch {
                combine(
                    nasaRepository.getTodayAsteroids(),
                    nasaRepository.getNasaPictureOfDay()
                ) { asteroidEntities, pictureOfDayEntity ->
                    _uiState.value = MainUiState(
                        false,
                        toPictureOfDayUiModel(pictureOfDayEntity),
                        asteroidEntities.map { toAsteroidUiModel(it) }
                    )
                }.collect()
            }
        }
    }

    fun showSavedBeforeTodayAsteroids() {
        viewModelScope.launch {
            _uiState.value = MainUiState(isLoading = true)

            nasaRepository.refreshAllWeekData()

            getDataJob.cancel()
            getDataJob = viewModelScope.launch {
                combine(
                    nasaRepository.getSavedBeforeTodayAsteroids(),
                    nasaRepository.getNasaPictureOfDay()
                ) { asteroidEntities, pictureOfDayEntity ->
                    _uiState.value = MainUiState(
                        false,
                        toPictureOfDayUiModel(pictureOfDayEntity),
                        asteroidEntities.map { toAsteroidUiModel(it) }
                    )
                }.collect()
            }
        }
    }

}

data class MainUiState(
    val isLoading: Boolean = true,
    val pictureOfDay: PictureOfDayUiModel? = null,
    val asteroids: List<AsteroidUiModel?>? = null
)