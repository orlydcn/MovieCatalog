package io.orly.movies.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "movie", foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["idCategory"],
        onDelete = CASCADE
    )],
    indices = [
        Index("id"),
        Index("idCategory")
    ]
)
data class MovieEntity(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("subtitle")
    val subtitle: String,
    @field:SerializedName("year")
    val year: Int,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("idCategory")
    val idCategory: Int
)