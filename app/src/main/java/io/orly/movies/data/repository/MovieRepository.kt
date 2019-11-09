package io.orly.movies.data.repository

import androidx.lifecycle.LiveData
import io.orly.movies.data.DataSourceManager
import io.orly.movies.data.Resource
import io.orly.movies.data.local.AppDatabase
import io.orly.movies.data.local.dao.CategoryDao
import io.orly.movies.data.local.dao.MovieDao
import io.orly.movies.data.local.entities.CategoryEntity
import io.orly.movies.data.local.entities.MovieEntity
import io.orly.movies.data.remote.ApiResponse
import io.orly.movies.data.remote.MovieService
import io.orly.movies.data.remote.ResultsResponse
import io.orly.movies.util.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val categoryDao: CategoryDao,
    private val movieDao: MovieDao,
    private val db: AppDatabase,
    private val movieApi: MovieService
) {
    fun loadCategories(): LiveData<Resource<List<CategoryEntity>>> =
        object :
            DataSourceManager<List<CategoryEntity>, ResultsResponse<CategoryEntity>>(
                appExecutors
            ) {
            override fun shouldFetch(data: List<CategoryEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<CategoryEntity>> =
                categoryDao.getCategories()

            override fun saveCallResult(item: ResultsResponse<CategoryEntity>) {
                db.runInTransaction {
                    item.data.forEach { category ->
                        categoryDao.insert(category)
                        movieDao.insert(category.movies)
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<ResultsResponse<CategoryEntity>>> =
                movieApi.getCategories()

        }.asLiveData()

    fun loadMovieByCategory(id: Int): LiveData<Resource<List<MovieEntity>>> =
        object :
            DataSourceManager<List<MovieEntity>, ResultsResponse<CategoryEntity>>(appExecutors) {
            override fun saveCallResult(item: ResultsResponse<CategoryEntity>) {
                db.runInTransaction {
                    item.data.forEach { category ->
                        categoryDao.insert(category)
                        movieDao.insert(category.movies)
                    }
                }
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<MovieEntity>> =
                movieDao.getMoviesByCategory(id)

            override fun createCall(): LiveData<ApiResponse<ResultsResponse<CategoryEntity>>> =
                movieApi.getCategories()

        }.asLiveData()

    fun loadMovieById(id: Int): LiveData<Resource<MovieEntity>> =
        object : DataSourceManager<MovieEntity, ResultsResponse<CategoryEntity>>(appExecutors) {
            override fun saveCallResult(item: ResultsResponse<CategoryEntity>) {
                db.runInTransaction {
                    item.data.forEach { category ->
                        categoryDao.insert(category)
                        movieDao.insert(category.movies)
                    }
                }
            }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun loadFromDb(): LiveData<MovieEntity> =
                movieDao.getMovieById(id)

            override fun createCall(): LiveData<ApiResponse<ResultsResponse<CategoryEntity>>> =
                movieApi.getCategories()

        }.asLiveData()
}