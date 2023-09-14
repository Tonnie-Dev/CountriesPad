package com.uxstate.util.use_cases

import com.uxstate.overview.use_cases.GetCountryDataUseCase

data class UseCaseContainer(val filterUseCase: FilterUseCase, val getCountryDataUseCase: GetCountryDataUseCase)
