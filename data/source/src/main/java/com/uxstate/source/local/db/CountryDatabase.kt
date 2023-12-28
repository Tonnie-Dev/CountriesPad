package com.uxstate.source.local.db




import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uxstate.source.local.entity.CountryEntity
import com.uxstate.source.local.converter.Converters
import com.uxstate.source.local.dao.CountryDAO

@Database(entities = [CountryEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CountryDatabase : RoomDatabase() {

    abstract val countryDAO: CountryDAO
}