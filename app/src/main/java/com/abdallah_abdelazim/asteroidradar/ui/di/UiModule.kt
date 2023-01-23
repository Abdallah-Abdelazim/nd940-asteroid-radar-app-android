package com.abdallah_abdelazim.asteroidradar.ui.di

import com.abdallah_abdelazim.asteroidradar.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {

    viewModelOf(::MainViewModel)

}