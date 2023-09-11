package com.uxstate.countriespad.di

import com.uxstate.countriespad.data.json.CountriesListParser
import com.uxstate.countriespad.data.json.JsonStringParser
import com.uxstate.util.model.Country
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



//We create a separate module to bind the interface to its implementation

@Module
@InstallIn(SingletonComponent::class)
abstract class JsonParserModule {

    //REPOSITORY
    @Binds //@Binds used for 1-to-1 interface-implementation mapping
    @Singleton


/*The abstract function takes only a single parameter which
    is the interface implementation and the return type is the
    interface implemented by the given parameter object.*/

    abstract fun provideJSONStringParser(countriesListParser: CountriesListParser)
    :JsonStringParser<com.uxstate.util.model.Country>
}
