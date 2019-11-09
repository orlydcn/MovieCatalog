package io.orly.movies.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.orly.movies.data.local.entities.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE idCategory=:idCategory")
    fun getMoviesByCategory(idCategory: Int): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Delete
    fun deleteMovie(movieEntity: MovieEntity)
}