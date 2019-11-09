package io.orly.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.orly.movies.data.local.dao.CategoryDao
import io.orly.movies.data.local.dao.MovieDao
import io.orly.movies.data.local.entities.CategoryEntity
import io.orly.movies.data.local.entities.MovieEntity
import io.orly.movies.util.AppConstant.DATABASE_VERSION

@Database(
    entities = [
        CategoryEntity::class,
        MovieEntity::class
    ], version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    abstract fun movieDao(): MovieDao
}