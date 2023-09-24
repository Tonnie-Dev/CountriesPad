package com.uxstate.countriespad.di

import android.app.Application
import androidx.room.Room
import com.uxstate.source.local.CountryDatabase
import com.uxstate.source.remote.CountryAPI
import com.uxstate.source.repository.CountryRepository
import com.uxstate.util.use_cases.FilterUseCase
import com.uxstate.overview.domain.use_cases.GetCountryDataUseCase
import com.uxstate.source.use_case.FetchCountryDataUseCase
import com.uxstate.util.use_cases.UseCaseContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideCountryDatabase(app: Application): CountryDatabase {

        //doesn't include TypeConverters as we leave Room to do the initialization
        return Room.databaseBuilder(app, CountryDatabase::class.java, "country_database")
                .build()
    }

 /*   //Use Case Container

    @Provides
    @Singleton
    fun provideUseCaseContainer(): UseCaseContainer {

        return UseCaseContainer(
                filterUseCase = FilterUseCase()
        )
    }


   @Provides
    @Singleton
    fun provideGetCountryDataUseCase(repository: CountryRepository):GetCountryDataUseCase {

        return GetCountryDataUseCase(repository)
    }
*/
    @Provides
    @Singleton
    fun provideFilterUseCase():FilterUseCase {

        return FilterUseCase()
    }


    @Provides
    @Singleton
    fun provideFetchCountryDataUseCase(repository: CountryRepository): FetchCountryDataUseCase {

        return  FetchCountryDataUseCase(repository)
    }

}