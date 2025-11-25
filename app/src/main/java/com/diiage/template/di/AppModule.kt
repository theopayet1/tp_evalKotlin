package com.diiage.template.di

import com.diiage.template.data.repository.*
import com.diiage.template.domain.repository.*
import org.koin.dsl.module

val appModule = module {
    // Single instance (singleton) of LoginService

    // Add other dependencies here as needed
    // single { YourRepository() }
    // factory { YourUseCase() } // new instance each time
}