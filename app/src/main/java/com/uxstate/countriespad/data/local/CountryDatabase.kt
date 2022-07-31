package com.uxstate.countriespad.data.local




import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CountryEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CountryDatabase : RoomDatabase() {

    abstract val countryDAO: CountryDAO
}