package com.petraride.basketplayers.di

import com.petraride.basketplayers.PlayersViewModel
import com.petraride.data.remote.PlayersApis
import com.petraride.data.repository.PlayersRepositoryImpl
import com.petraride.domain.repository.PlayersRepository
import com.petraride.domain.usecase.GetPlayerByIdUseCase
import com.petraride.domain.usecase.GetPlayersUseCase
import com.petraride.domain.usecase.PlayersUseCases
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Provide Ktor API
    single { PlayersApis() }



    // Provide Repository
    single<PlayersRepository> { PlayersRepositoryImpl(get()) }

    // Provide Use Case
    single { GetPlayersUseCase(get()) }
    single { GetPlayerByIdUseCase(get()) }
    single { PlayersUseCases(get(), get()) }

    // Provide ViewModel
    viewModel { PlayersViewModel(get()) }
}
