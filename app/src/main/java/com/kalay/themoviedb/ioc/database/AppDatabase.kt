package com.kalay.themoviedb.ioc.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kalay.data.database.ArrayConverter
import com.kalay.data.database.dao.MovieCardDBDao
import com.kalay.data.database.model.MovieCardDbDTO

@Database(
    entities = [MovieCardDbDTO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(*[ArrayConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieCardDbDao(): MovieCardDBDao

}