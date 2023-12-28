package com.uxstate.source.remote.remote_source

import com.uxstate.util.Resource
import com.uxstate.util.model.Country

interface RemoteDataSource {

    suspend fun fetchCountriesFromNetwork(): Resource<List<Country>>
}