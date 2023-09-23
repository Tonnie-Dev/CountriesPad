package com.uxstate.stats

sealed class StatsScreenEvent{

    data object AreaButtonToggle:StatsScreenEvent()
    data object PopulationButtonToggle:StatsScreenEvent()
}
