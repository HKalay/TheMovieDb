package com.kalay.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.kalay.data.database.model.Color
import io.reactivex.Flowable

@Dao
interface ColorsDao : BaseDao<Color> {

    @Query("SELECT * FROM color")
    fun getColors(): Flowable<List<Color>>
}
