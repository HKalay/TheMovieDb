package com.kalay.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kalay.data.response.dataclasses.Results


@Entity(tableName = "localmoviecard")
data class MovieCardDbDTO(

    @PrimaryKey(autoGenerate = true)
    val id:Int?,

    val results: Results?
)

