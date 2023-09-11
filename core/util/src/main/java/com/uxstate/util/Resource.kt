package com.uxstate.util


sealed class Resource<T>(val data: T? = null, val errorMessage: String? = null) {

    /*We attach a nullable data to the Error case as we can
     still return some data e.g. database cache*/
    class Error<T>(data: T?= null, errorMessage: String) : Resource<T>(data, errorMessage)

    class Success<T>(data: T?) : Resource<T>(data)

    //it has a property which will be called from ViewModel
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}



