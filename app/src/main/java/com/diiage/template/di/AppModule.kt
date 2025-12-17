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
/**
 * Module d’injection de dépendances principal de l’application.
 * Organisation :
 * - Réseau : configuration du HttpClient Ktor et de l’API distante.
 * - Data : implémentation du repository basé sur l’API.
 * - Domain : use case exposant la logique métier.
 * - UI : ViewModel injecté avec ses dépendances.
 *
 * Les scopes sont choisis en fonction du cycle de vie :
 * - `single` pour les dépendances partagées et coûteuses (HttpClient, Api, Repository).
 * - `factory` pour les use cases (logique métier sans état).
 * - `viewModel` pour les ViewModels, gérés par Koin et le cycle de vie Android.
 *
 */
val appModule = module {
    single<HttpClient> { WaifuHttpClient.create() }
    single { WaifuApi(get()) }

    single<WaifuRepository> { WaifuRepositoryImpl(get()) }
    factory { GetPortraitWaifusUseCase(get()) }

    viewModel { HomeViewModel(get()) }
}
