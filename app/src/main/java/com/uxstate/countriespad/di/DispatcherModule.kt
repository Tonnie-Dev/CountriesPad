package com.uxstate.countriespad.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/*Inject qualified CoroutineDispatcher to differentiate IO dispatcher
 CoroutineDispatcher () from other instances of CoroutineDispatchers*/

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @IoDispatcher
    @Provides
    fun provideIoDispatcher():CoroutineDispatcher = Dispatchers.IO
}