package io.orly.movies.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.orly.movies.data.local.entities.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category ORDER BY category ASC")
    fun getCategories(): LiveData<List<CategoryEntity>>

    @Query("SELECT * FROM category WHERE status=:status ORDER BY category ASC")
    fun getCategoriesByStatus(status: Int): LiveData<CategoryEntity>

    @Delete
    fun deleteCategory(categoryEntity: CategoryEntity)
}