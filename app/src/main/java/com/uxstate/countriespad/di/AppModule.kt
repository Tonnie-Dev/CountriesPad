package com.uxstate.countriespad.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.uxstate.countriespad.data.local.CountryDatabase
import com.uxstate.countriespad.data.remote.CountryAPI
import com.uxstate.countriespad.domain.repository.CountryRepository
import com.uxstate.countriespad.domain.use_cases.GetCountryDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    //API
    @Provides
    @Singleton
    fun provideCountryAPI(): CountryAPI {
        //no converter as Retrofit is just returning raw JSON String
        return Retrofit.Builder()
                .baseUrl(CountryAPI.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create()
    }


    //Database
    @Provides
    @Singleton
    fun provideCountryDatabase(app:Application): CountryDatabase {

        //doesn't include TypeConverters as we leave Room to do the initialization
        return Room.databaseBuilder(app, CountryDatabase::class.java, "country_database")
                .build()
    }

    //Use Case Container
   

}