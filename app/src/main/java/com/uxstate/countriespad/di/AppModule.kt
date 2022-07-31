package com.uxstate.countriespad.di

import com.uxstate.countriespad.data.remote.CountryAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCountryAPI(): CountryAPI {
        //no converter as Retrofit is just returning raw JSON String
        return Retrofit.Builder()
                .baseUrl(CountryAPI.BASE_URL)
                .build()
                .create()
    }

}