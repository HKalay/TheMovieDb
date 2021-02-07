package com.kalay.data.database.dao

import androidx.room.*
import com.kalay.data.database.model.MovieCardDbDTO
import io.reactivex.Single

@Dao
interface MovieCardDBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieCardDbDTO?)

    @Delete
    fun deleteMovie(movie: MovieCardDbDTO?)

    @Query("SELECT * FROM localmoviecard")
    fun getAllMovie(): Single<List<MovieCardDbDTO>>

    @Query("SELECT * FROM localmoviecard WHERE id =:movieCardId")
    fun getMovieCardWithId(movieCardId: Int?): Single<MovieCardDbDTO>
}
