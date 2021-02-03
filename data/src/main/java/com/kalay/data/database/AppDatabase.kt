package com.kalay.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kalay.data.database.dao.MovieCardDBDao

@Database(
    entities = [MovieCardDBDao::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(*[ArrayConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieCardDbDao(): MovieCardDBDao

}