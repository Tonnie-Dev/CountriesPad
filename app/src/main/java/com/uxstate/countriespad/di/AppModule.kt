package com.uxstate.countriespad.di

import android.app.Application
import androidx.room.Room
import com.uxstate.source.local.CountryDatabase
import com.uxstate.source.remote.api.CountryAPI
import com.uxstate.source.repository.CountryRepository
import com.uxstate.util.use_cases.FilterUseCase
import com.uxstate.source.use_case.FetchCountryDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val READ_TIMEOUT_IN_SECONDS = 15L
    private const val CONNECT_TIMEOUT_IN_SECONDS = 15L


    // log feature integrated to show request and response info.

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


    //Connect Timeout - Time period for client to establish connection with the target host
    // Read Timeout - Max latency time for waiting server's response


    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .build()

    //API
    @Provides
    @Singleton
    fun provideCountryAPI(okHttpClient: OkHttpClient): CountryAPI {
        //no converter as Retrofit is just returning raw JSON String
        return Retrofit.Builder()
                .baseUrl(com.uxstate.source.BuildConfig.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
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