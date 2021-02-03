package com.kalay.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.kalay.data.response.dataclasses.Results

@Entity(tableName = "localmoviecard")
data class MovieCardDbDTO(

	@ColumnInfo(name = "movieCardDbId")
	val localDbId: Int?,

	var results: Results? = null

)

