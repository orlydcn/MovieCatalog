package io.orly.movies.ui.category

import androidx.lifecycle.ViewModel
import io.orly.movies.data.repository.MovieRepository
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {

    val categories = movieRepository.loadCategories()
}