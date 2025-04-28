package com.petraride.basketplayers.di

import androidx.room.Room
import com.petraride.basketplayers.PlayersViewModel
import com.petraride.data.local.database.MyFavDataBase
import com.petraride.data.remote.PlayersApis
import com.petraride.data.repository.PlayersRepositoryImpl
import com.petraride.domain.repository.PlayersRepository
import com.petraride.domain.usecase.AddPlayerToFavUseCase
import com.petraride.domain.usecase.GetPlayerByIdUseCase
import com.petraride.domain.usecase.GetPlayerFromFavUseCase
import com.petraride.domain.usecase.GetPlayersUseCase
import com.petraride.domain.usecase.PlayersUseCases
import com.petraride.domain.usecase.RemovePlayerToFavUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Provide Ktor API
    single { PlayersApis() }


//room database
    single {
        Room.databaseBuilder(
            get(),
            MyFavDataBase::class.java,
            "my_fav_player_database",

        ).build()
    }

    single { get<MyFavDataBase>().playerDao() }

    // Provide Repository
    single<PlayersRepository> { PlayersRepositoryImpl(get(), get()) }

    // Provide Use Case
    single { GetPlayersUseCase(get()) }
    single { GetPlayerByIdUseCase(get()) }
    single { AddPlayerToFavUseCase(get()) }
    single { RemovePlayerToFavUseCase(get()) }
    single { GetPlayerFromFavUseCase(get()) }
    single { PlayersUseCases(get(), get(), get(), get(), get()) }

    // Provide ViewModel
    viewModel { PlayersViewModel(get()) }
}
