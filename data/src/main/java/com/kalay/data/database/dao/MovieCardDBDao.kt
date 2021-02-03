package com.kalay.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kalay.data.database.model.MovieCardDbDTO
import io.reactivex.Flowable

@Dao
interface MovieCardDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieCardDbDTO?)

    @Delete
    fun deleteMovie(movie: MovieCardDbDTO?)

    @Query("SELECT * FROM localmoviecard")
    fun getAllMovie(): LiveData<List<MovieCardDbDTO>>
}
