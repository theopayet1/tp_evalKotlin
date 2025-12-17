package com.diiage.template.di

import com.diiage.template.data.remote.WaifuApi
import com.diiage.template.data.remote.WaifuHttpClient
import com.diiage.template.data.repository.WaifuRepositoryImpl
import com.diiage.template.domain.repository.WaifuRepository
import com.diiage.template.domain.usecase.GetPortraitWaifusUseCase
import com.diiage.template.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<HttpClient> { WaifuHttpClient.create() }
    single { WaifuApi(get()) }

    single<WaifuRepository> { WaifuRepositoryImpl(get()) }
    factory { GetPortraitWaifusUseCase(get()) }

    viewModel { HomeViewModel(get()) }
}
