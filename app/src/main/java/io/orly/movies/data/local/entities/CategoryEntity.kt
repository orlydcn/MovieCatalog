package io.orly.movies.data.local.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "category",
    indices = [
        Index("id")
    ]
)
data class CategoryEntity(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("category")
    val category: String = "",
    @field:SerializedName("color")
    val color: String = "",
    @field:SerializedName("status")
    val status: Int = 0
){
    @Ignore
    @SerializedName("movies")
    lateinit var movies: List<MovieEntity>
}