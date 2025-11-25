package com.diiage.template.di

import com.diiage.template.data.repository.*
import com.diiage.template.domain.repository.*
import org.koin.dsl.module

/**
 * Koin dependency injection module for the application.
 *
 * This module defines all the dependencies that will be available for injection
 * throughout the application. It configures how dependencies are created and
 * their lifecycle (singleton, factory, etc.).
 *
 * ## Usage
 * This module should be loaded when initializing the Koin container in the application.
 *
 * ## Dependencies Included
 * - [LoginRepository] as singleton using [LoginRepositoryImpl] implementation
 *
 * @see org.koin.dsl.module
 * @see single
 * @see factory
 *
 * @sample
 * // Initialize Koin with this module
 * startKoin {
 *     modules(appModule)
 * }
 */
val appModule = module {
    // Single instance (singleton) of LoginService
    single<LoginRepository> { LoginRepositoryImpl() }

    // Add other dependencies here as needed
    // single { YourRepository() }
    // factory { YourUseCase() } // new instance each time
}