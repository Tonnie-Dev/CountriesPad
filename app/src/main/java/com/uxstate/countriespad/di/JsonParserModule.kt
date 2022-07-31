package com.uxstate.countriespad.di

import com.uxstate.countriespad.data.json.CountriesListParser
import com.uxstate.countriespad.data.json.JsonStringParser
import com.uxstate.countriespad.domain.model.Country
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
abstract class JsonParserModule {

    //REPOSITORY
    @Binds //@Binds used for 1-to-1 interface-implementation mapping
    @Singleton

    /*The abstract function takes only a single parameter which
    is the interface implementation and the return type is the
    interface implemented by the given parameter object.*/
    abstract fun provideJSONStringParser(countriesListParser: CountriesListParser):JsonStringParser<Country>
}