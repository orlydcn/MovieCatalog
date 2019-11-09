package io.orly.movies.data.remote

import androidx.lifecycle.LiveData
import io.orly.movies.data.local.entities.CategoryEntity
import io.orly.movies.util.AppConstant.CATEGORIES
import retrofit2.http.GET

interface MovieService {

    @GET(CATEGORIES)
    fun getCategories(): LiveData<ApiResponse<ResultsResponse<CategoryEntity>>>
}